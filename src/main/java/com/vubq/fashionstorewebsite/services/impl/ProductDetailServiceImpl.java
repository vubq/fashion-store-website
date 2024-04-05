package com.vubq.fashionstorewebsite.services.impl;

import com.vubq.fashionstorewebsite.dtos.ProductFilterDto;
import com.vubq.fashionstorewebsite.entities.*;
import com.vubq.fashionstorewebsite.payloads.DataTableRequest;
import com.vubq.fashionstorewebsite.repositories.ProductDetailRepository;
import com.vubq.fashionstorewebsite.services.ProductDetailService;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Override
    public Page<ProductDetail> getAll(DataTableRequest dataTableRequest, ProductFilterDto productFilterDto) {
        PageRequest pageable = dataTableRequest.toPageable();
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                query.distinct(true);
                query.groupBy(root.get("product"));
                List<Predicate> predicates = new ArrayList<>();
                Join<ProductDetail, Product> productDetailProductJoin = root.join("product");
                Join<ProductDetail, Size> productDetailSizeJoin = root.join("size");
                Join<ProductDetail, Color> productDetailColorJoin = root.join("color");
                Join<ProductDetail, Material> productDetailMaterialJoin = root.join("material");
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(productDetailProductJoin.get("name")), "%" + dataTableRequest.getFilter().trim().toUpperCase() + "%"));
                if (productFilterDto.getCategories().size() > 0) {
                    predicates.add(criteriaBuilder.and(productDetailProductJoin.get("category").get("id").in(productFilterDto.getCategories())));
                }
                if (productFilterDto.getBrands().size() > 0) {
                    predicates.add(criteriaBuilder.and(productDetailProductJoin.get("brand").get("id").in(productFilterDto.getBrands())));
                }
                if (productFilterDto.getSizes().size() > 0) {
                    predicates.add(criteriaBuilder.and(productDetailSizeJoin.get("id").in(productFilterDto.getSizes())));
                }
                if (productFilterDto.getColors().size() > 0) {
                    predicates.add(criteriaBuilder.and(productDetailColorJoin.get("id").in(productFilterDto.getColors())));
                }
                if (productFilterDto.getMaterials().size() > 0) {
                    predicates.add(criteriaBuilder.and(productDetailMaterialJoin.get("id").in(productFilterDto.getMaterials())));
                }
                if (productFilterDto.getPriceApprox().size() == 2) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(productDetailProductJoin.get("price"), productFilterDto.getPriceApprox().get(0))));
                    predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(productDetailProductJoin.get("price"), productFilterDto.getPriceApprox().get(1))));
                }
                query.where(predicates.toArray(new Predicate[]{}));
                return null;
            }
        };
        return this.productDetailRepository.findAll(specification, pageable);
    }
}
