package com.vubq.fashionstorewebsite.repositories;

import com.vubq.fashionstorewebsite.entities.Brand;
import com.vubq.fashionstorewebsite.enums.EStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {

    Page<Brand> findAll(Specification<Brand> spec, Pageable pageable);

    List<Brand> findAll(Specification<Brand> spec);

    List<Brand> findByStatus(EStatus status);
}
