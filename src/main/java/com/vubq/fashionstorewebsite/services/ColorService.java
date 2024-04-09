package com.vubq.fashionstorewebsite.services;

import com.vubq.fashionstorewebsite.entities.Color;
import com.vubq.fashionstorewebsite.enums.EStatus;
import com.vubq.fashionstorewebsite.payloads.DataTableRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ColorService {

    List<Color> getAll();

    Page<Color> getAll(DataTableRequest request, List<EStatus> statusList);

    Optional<Color> findById(String id);

    Color save(Color color);

    List<Color> getAllByStatus(EStatus status);
}
