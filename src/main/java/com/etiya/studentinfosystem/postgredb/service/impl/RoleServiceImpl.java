package com.etiya.studentinfosystem.postgredb.service.impl;

import com.etiya.studentinfosystem.postgredb.model.Role;
import com.etiya.studentinfosystem.postgredb.repository.RoleRepository;
import com.etiya.studentinfosystem.postgredb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }
    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }
    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }
    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

}
