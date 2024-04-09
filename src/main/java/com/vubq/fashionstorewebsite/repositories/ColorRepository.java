package com.vubq.fashionstorewebsite.repositories;

import com.vubq.fashionstorewebsite.entities.Color;
import com.vubq.fashionstorewebsite.enums.EStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepository extends JpaRepository<Color, String> {

    Page<Color> findAll(Specification<Color> spec, Pageable pageable);

    List<Color> findAll(Specification<Color> spec);

    List<Color> findAllByStatus(EStatus status);
}
