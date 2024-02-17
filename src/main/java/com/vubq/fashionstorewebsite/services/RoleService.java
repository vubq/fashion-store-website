package com.vubq.fashionstorewebsite.services;

import com.vubq.fashionstorewebsite.entities.Role;
import com.vubq.fashionstorewebsite.enums.ERole;

import java.util.List;

public interface RoleService {

    List<Role> getListRoleByListRoleName(List<ERole> roles);
}
