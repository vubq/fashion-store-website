package com.vubq.fashionstorewebsite.repositories;

import com.vubq.fashionstorewebsite.entities.Material;
import com.vubq.fashionstorewebsite.enums.EStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, String> {

    Page<Material> findAll(Specification<Material> spec, Pageable pageable);

    List<Material> findAll(Specification<Material> spec);

    List<Material> findAllByStatus(EStatus status);
}
