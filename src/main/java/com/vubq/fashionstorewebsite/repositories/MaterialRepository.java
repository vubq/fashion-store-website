package com.vubq.fashionstorewebsite.repositories;

import com.vubq.fashionstorewebsite.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, String> {
}
