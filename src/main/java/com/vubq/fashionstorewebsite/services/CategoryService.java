package com.vubq.fashionstorewebsite.services;

import com.vubq.fashionstorewebsite.entities.Category;
import com.vubq.fashionstorewebsite.enums.EStatus;
import com.vubq.fashionstorewebsite.payloads.DataTableRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> getAll();

    Page<Category> getAll(DataTableRequest request, List<EStatus> statusList);

    Optional<Category> findById(String id);

    Category save(Category category);

    List<Category> getAllByStatus(EStatus status);
}

