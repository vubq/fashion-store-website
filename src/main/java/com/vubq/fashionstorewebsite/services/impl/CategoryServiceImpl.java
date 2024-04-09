package com.vubq.fashionstorewebsite.services.impl;

import com.vubq.fashionstorewebsite.entities.BaseEntity;
import com.vubq.fashionstorewebsite.entities.Category;
import com.vubq.fashionstorewebsite.enums.EStatus;
import com.vubq.fashionstorewebsite.payloads.DataTableRequest;
import com.vubq.fashionstorewebsite.repositories.CategoryRepository;
import com.vubq.fashionstorewebsite.services.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Page<Category> getAll(DataTableRequest request, List<EStatus> statusList) {
        PageRequest pageable = request.toPageable();
        BaseSpecification<Category> specNameContains = new BaseSpecification<>(
                SearchCriteria.builder()
                        .keys(new String[]{Category.Fields.name})
                        .operation(SearchOperation.CONTAINS)
                        .value(StringUtils.isEmpty(request.getFilter()) ? "" : request.getFilter().trim().toUpperCase())
                        .build());
        BaseSpecification<Category> specStatusIn = new BaseSpecification<>(
                SearchCriteria.builder()
                        .keys(new String[]{BaseEntity.Fields.status})
                        .operation(SearchOperation.IN)
                        .listValue(statusList)
                        .build());
        return this.categoryRepository.findAll(Specification.where(specNameContains).and(specStatusIn), pageable);
    }

    @Override
    public Optional<Category> findById(String id) {
        return this.categoryRepository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllByStatus(EStatus status) {
        BaseSpecification<Category> specStatusIn = new BaseSpecification<>(
                SearchCriteria.builder()
                        .keys(new String[]{BaseEntity.Fields.status})
                        .operation(SearchOperation.EQUALITY)
                        .value(status)
                        .build());
        return this.categoryRepository.findAll(Specification.where(specStatusIn));
    }
}
