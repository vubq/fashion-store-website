package com.vubq.fashionstorewebsite.services.impl;

import com.vubq.fashionstorewebsite.entities.BaseEntity;
import com.vubq.fashionstorewebsite.entities.Color;
import com.vubq.fashionstorewebsite.enums.EStatus;
import com.vubq.fashionstorewebsite.payloads.DataTableRequest;
import com.vubq.fashionstorewebsite.repositories.ColorRepository;
import com.vubq.fashionstorewebsite.services.ColorService;
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
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public List<Color> getAll() {
        return this.colorRepository.findAll();
    }

    @Override
    public Page<Color> getAll(DataTableRequest request, List<EStatus> statusList) {
        PageRequest pageable = request.toPageable();
        BaseSpecification<Color> specNameContains = new BaseSpecification<>(
                SearchCriteria.builder()
                        .keys(new String[]{Color.Fields.name})
                        .operation(SearchOperation.CONTAINS)
                        .value(StringUtils.isEmpty(request.getFilter()) ? "" : request.getFilter().trim().toUpperCase())
                        .build());
        BaseSpecification<Color> specStatusIn = new BaseSpecification<>(
                SearchCriteria.builder()
                        .keys(new String[]{BaseEntity.Fields.status})
                        .operation(SearchOperation.IN)
                        .listValue(statusList)
                        .build());
        return this.colorRepository.findAll(Specification.where(specNameContains).and(specStatusIn), pageable);
    }

    @Override
    public Optional<Color> findById(String id) {
        return this.colorRepository.findById(id);
    }

    @Override
    public Color save(Color color) {
        return this.colorRepository.save(color);
    }

    @Override
    public List<Color> getAllByStatus(EStatus status) {
        BaseSpecification<Color> specStatusIn = new BaseSpecification<>(
                SearchCriteria.builder()
                        .keys(new String[]{BaseEntity.Fields.status})
                        .operation(SearchOperation.EQUALITY)
                        .value(status)
                        .build());
        return this.colorRepository.findAll(Specification.where(specStatusIn));
    }
}
