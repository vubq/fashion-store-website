package com.vubq.fashionstorewebsite.repositories;

import com.vubq.fashionstorewebsite.entities.Category;
import com.vubq.fashionstorewebsite.enums.EStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Page<Category> findAll(Specification<Category> spec, Pageable pageable);

    List<Category> findAll(Specification<Category> spec);

    List<Category> findAllByStatus(EStatus status);
}