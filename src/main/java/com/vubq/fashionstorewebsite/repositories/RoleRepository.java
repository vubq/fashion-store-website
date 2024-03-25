package com.vubq.fashionstorewebsite.repositories;

import com.vubq.fashionstorewebsite.entities.Role;
import com.vubq.fashionstorewebsite.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    List<Role> findByNameIn(List<ERole> roles);
}
