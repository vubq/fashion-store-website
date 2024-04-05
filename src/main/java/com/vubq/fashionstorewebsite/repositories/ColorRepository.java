package com.vubq.fashionstorewebsite.repositories;

import com.vubq.fashionstorewebsite.entities.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, String> {

    Page<Color> findAll(Specification<Color> spec, Pageable pageable);
}
