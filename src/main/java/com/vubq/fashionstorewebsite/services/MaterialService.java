package com.vubq.fashionstorewebsite.services;

import com.vubq.fashionstorewebsite.entities.Material;
import com.vubq.fashionstorewebsite.enums.EStatus;
import com.vubq.fashionstorewebsite.payloads.DataTableRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface MaterialService {

    List<Material> getAll();

    Page<Material> getAll(DataTableRequest request, List<EStatus> statusList);

    Optional<Material> findById(String id);

    Material save(Material material);

    List<Material> getAllByStatus(EStatus status);
}
