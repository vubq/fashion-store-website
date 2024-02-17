package com.vubq.fashionstorewebsite.services.impl;

import com.vubq.fashionstorewebsite.entities.Role;
import com.vubq.fashionstorewebsite.enums.ERole;
import com.vubq.fashionstorewebsite.repositories.RoleRepository;
import com.vubq.fashionstorewebsite.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getListRoleByListRoleName(List<ERole> roles) {
        return this.roleRepository.findByNameIn(roles);
    }
}

