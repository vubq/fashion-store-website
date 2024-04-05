package com.vubq.fashionstorewebsite.services.impl;

import com.vubq.fashionstorewebsite.entities.BaseEntity;
import com.vubq.fashionstorewebsite.entities.Size;
import com.vubq.fashionstorewebsite.enums.EStatus;
import com.vubq.fashionstorewebsite.payloads.DataTableRequest;
import com.vubq.fashionstorewebsite.repositories.SizeRepository;
import com.vubq.fashionstorewebsite.services.SizeService;
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
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public List<Size> getAll() {
        return this.sizeRepository.findAll();
    }

    @Override
    public Page<Size> getAll(DataTableRequest request, List<EStatus> statusList) {
        PageRequest pageable = request.toPageable();
        BaseSpecification<Size> specNameContains = new BaseSpecification<>(
                SearchCriteria.builder()
                        .keys(new String[]{Size.Fields.name})
                        .operation(SearchOperation.CONTAINS)
                        .value(StringUtils.isEmpty(request.getFilter()) ? "" : request.getFilter().trim().toUpperCase())
                        .build());
        BaseSpecification<Size> specStatusIn = new BaseSpecification<>(
                SearchCriteria.builder()
                        .keys(new String[]{BaseEntity.Fields.status})
                        .operation(SearchOperation.IN)
                        .listValue(statusList)
                        .build());
        return this.sizeRepository.findAll(Specification.where(specNameContains).and(specStatusIn), pageable);
    }

    @Override
    public Optional<Size> findById(String id) {
        return this.sizeRepository.findById(id);
    }

    @Override
    public Size save(Size size) {
        return this.sizeRepository.save(size);
    }
}
