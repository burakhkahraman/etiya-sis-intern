package com.etiya.studentinfosystem.postgredb.controller;

import com.etiya.studentinfosystem.postgredb.model.Role;
import com.etiya.studentinfosystem.postgredb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {



    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }
    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }
    @PostMapping
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        Role addedRole = roleService.addRole(role);
        return new ResponseEntity<>(addedRole, HttpStatus.CREATED);
    }
    @PutMapping
    public Role updateRole(@PathVariable Long id, @RequestBody Role role) {
        role.setId(id);
        return roleService.updateRole(role);
    }
    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
    }


}
