package com.vubq.fashionstorewebsite.repositories;

import com.vubq.fashionstorewebsite.entities.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, String> {
}
