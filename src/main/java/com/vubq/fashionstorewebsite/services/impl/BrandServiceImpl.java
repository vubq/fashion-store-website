package com.vubq.fashionstorewebsite.services.impl;

import com.vubq.fashionstorewebsite.entities.BaseEntity;
import com.vubq.fashionstorewebsite.entities.Brand;
import com.vubq.fashionstorewebsite.enums.EStatus;
import com.vubq.fashionstorewebsite.payloads.DataTableRequest;
import com.vubq.fashionstorewebsite.repositories.BrandRepository;
import com.vubq.fashionstorewebsite.services.BrandService;
import com.vubq.fashionstorewebsite.utils.BaseSpecification;
import com.vubq.fashionstorewebsite.utils.SearchCriteria;
import com.vubq.fashionstorewebsite.utils.SearchOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brand> getAll() {
        return this.brandRepository.findAll();
    }

    @Override
    public Page<Brand> getAll(DataTableRequest request, List<EStatus> statusList) {
        PageRequest pageable = request.toPageable();
        BaseSpecification<Brand> specNameContains = new BaseSpecification<>(
                SearchCriteria.builder()
                        .keys(new String[]{Brand.Fields.name})
                        .operation(SearchOperation.CONTAINS)
                        .value(StringUtils.isEmpty(request.getFilter()) ? "" : request.getFilter().trim().toUpperCase())
                        .build());
        BaseSpecification<Brand> specStatusIn = new BaseSpecification<>(
                SearchCriteria.builder()
                        .keys(new String[]{BaseEntity.Fields.status})
                        .operation(SearchOperation.IN)
                        .listValue(statusList)
                        .build());
        return this.brandRepository.findAll(Specification.where(specNameContains).and(specStatusIn), pageable);
    }

    @Override
    public Optional<Brand> findById(String id) {
        return this.brandRepository.findById(id);
    }

    @Override
    public Brand save(Brand brand) {
        return this.brandRepository.save(brand);
    }
}
