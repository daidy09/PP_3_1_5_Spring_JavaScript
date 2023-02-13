package com.dementiev.demo.service;

import com.dementiev.demo.model.Role;

import java.util.List;

public interface RoleService {
    public void addRole(Role role);

    Role findById(Long role_id);

    List<Role> getAllRoles();

    public void deleteRole(Long id);

    public void editRole(Role role);
}