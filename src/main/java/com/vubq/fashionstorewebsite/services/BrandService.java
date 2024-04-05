package com.vubq.fashionstorewebsite.services;

import com.vubq.fashionstorewebsite.entities.Brand;
import com.vubq.fashionstorewebsite.enums.EStatus;
import com.vubq.fashionstorewebsite.payloads.DataTableRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BrandService {

    List<Brand> getAll();

    Page<Brand> getAll(DataTableRequest request, List<EStatus> statusList);

    Optional<Brand> findById(String id);

    Brand save(Brand brand);
}
