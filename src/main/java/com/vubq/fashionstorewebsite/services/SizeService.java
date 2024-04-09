package com.vubq.fashionstorewebsite.services;

import com.vubq.fashionstorewebsite.entities.Size;
import com.vubq.fashionstorewebsite.enums.EStatus;
import com.vubq.fashionstorewebsite.payloads.DataTableRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface SizeService {

    List<Size> getAll();

    Page<Size> getAll(DataTableRequest request, List<EStatus> statusList);

    Optional<Size> findById(String id);

    Size save(Size size);

    List<Size> getAllByStatus(EStatus status);
}
