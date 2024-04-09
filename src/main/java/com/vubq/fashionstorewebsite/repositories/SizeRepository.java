package com.vubq.fashionstorewebsite.repositories;

import com.vubq.fashionstorewebsite.entities.Size;
import com.vubq.fashionstorewebsite.enums.EStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size, String> {

    Page<Size> findAll(Specification<Size> spec, Pageable pageable);

    List<Size> findAll(Specification<Size> spec);

    List<Size> findAllByStatus(EStatus status);
}
