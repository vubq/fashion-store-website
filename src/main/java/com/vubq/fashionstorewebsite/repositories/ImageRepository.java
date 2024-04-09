package com.vubq.fashionstorewebsite.repositories;

import com.vubq.fashionstorewebsite.entities.Image;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findAll(Specification<Image> spec);
}
