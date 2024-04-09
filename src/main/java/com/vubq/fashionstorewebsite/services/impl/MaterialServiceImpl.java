package com.vubq.fashionstorewebsite.services.impl;

import com.vubq.fashionstorewebsite.entities.BaseEntity;
import com.vubq.fashionstorewebsite.entities.Material;
import com.vubq.fashionstorewebsite.enums.EStatus;
import com.vubq.fashionstorewebsite.payloads.DataTableRequest;
import com.vubq.fashionstorewebsite.repositories.MaterialRepository;
import com.vubq.fashionstorewebsite.services.MaterialService;
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
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public List<Material> getAll() {
        return this.materialRepository.findAll();
    }

    @Override
    public Page<Material> getAll(DataTableRequest request, List<EStatus> statusList) {
        PageRequest pageable = request.toPageable();
        BaseSpecification<Material> specNameContains = new BaseSpecification<>(
                SearchCriteria.builder()
                        .keys(new String[]{Material.Fields.name})
                        .operation(SearchOperation.CONTAINS)
                        .value(StringUtils.isEmpty(request.getFilter()) ? "" : request.getFilter().trim().toUpperCase())
                        .build());
        BaseSpecification<Material> specStatusIn = new BaseSpecification<>(
                SearchCriteria.builder()
                        .keys(new String[]{BaseEntity.Fields.status})
                        .operation(SearchOperation.IN)
                        .listValue(statusList)
                        .build());
        return this.materialRepository.findAll(Specification.where(specNameContains).and(specStatusIn), pageable);
    }

    @Override
    public Optional<Material> findById(String id) {
        return this.materialRepository.findById(id);
    }

    @Override
    public Material save(Material material) {
        return this.materialRepository.save(material);
    }

    @Override
    public List<Material> getAllByStatus(EStatus status) {
        BaseSpecification<Material> specStatusIn = new BaseSpecification<>(
                SearchCriteria.builder()
                        .keys(new String[]{BaseEntity.Fields.status})
                        .operation(SearchOperation.EQUALITY)
                        .value(status)
                        .build());
        return this.materialRepository.findAll(Specification.where(specStatusIn));
    }
}
