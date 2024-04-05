package com.vubq.fashionstorewebsite.services.impl;

import com.vubq.fashionstorewebsite.dtos.ProductFilterDto;
import com.vubq.fashionstorewebsite.entities.BaseEntity;
import com.vubq.fashionstorewebsite.entities.Brand;
import com.vubq.fashionstorewebsite.entities.Category;
import com.vubq.fashionstorewebsite.entities.Product;
import com.vubq.fashionstorewebsite.payloads.DataTableRequest;
import com.vubq.fashionstorewebsite.repositories.ProductRepository;
import com.vubq.fashionstorewebsite.services.ProductService;
import com.vubq.fashionstorewebsite.utils.BaseSpecification;
import com.vubq.fashionstorewebsite.utils.SearchCriteria;
import com.vubq.fashionstorewebsite.utils.SearchOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> getAll(DataTableRequest request, ProductFilterDto productFilterDto) {
        PageRequest pageable = request.toPageable();
        BaseSpecification<Product> specNameContains = new BaseSpecification<>(
                SearchCriteria.builder()
                        .keys(new String[]{Product.Fields.name})
                        .operation(SearchOperation.CONTAINS)
                        .value(StringUtils.isEmpty(request.getFilter()) ? "" : request.getFilter().trim().toUpperCase())
                        .build());
        BaseSpecification<Product> specCategoryIn = new BaseSpecification<>(
                SearchCriteria.builder()
                        .keys(new String[]{"category", Category.Fields.id})
                        .operation(SearchOperation.IN)
                        .listValue(productFilterDto.getCategories())
                        .build());
        BaseSpecification<Product> specBrandIn = new BaseSpecification<>(
                SearchCriteria.builder()
                        .keys(new String[]{"brand", Brand.Fields.id})
                        .operation(SearchOperation.IN)
                        .listValue(productFilterDto.getBrands())
                        .build());
        BaseSpecification<Product> specSizeIn = new BaseSpecification<>(
                SearchCriteria.builder()
                        .keys(new String[]{"productDetails", "size"})
                        .operation(SearchOperation.IN)
                        .listValue(productFilterDto.getSizes())
                        .build());
        BaseSpecification<Product> specStatusIn = new BaseSpecification<>(
                SearchCriteria.builder()
                        .keys(new String[]{BaseEntity.Fields.status})
                        .operation(SearchOperation.IN)
                        .listValue(productFilterDto.getStatus())
                        .build());
        return this.productRepository.findAll(
                Specification.where(specNameContains)
                        .and(specStatusIn)
                        .and(specBrandIn)
                        .and(specCategoryIn)
                        .and(specSizeIn)
                , pageable);
    }
}
