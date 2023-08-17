package com.etiya.studentinfosystem.postgredb.service;

import com.etiya.studentinfosystem.postgredb.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(Long id);
    Role createRole(Role role);
    Role updateRole(Role role);
    void deleteRole(Long id);

    Role addRole(Role role);

}
