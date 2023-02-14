package com.dementiev.demo.service;

import com.dementiev.demo.model.Role;
import com.dementiev.demo.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }


    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);

    }

    @Override
    public void editRole(Role role) {
        role.setId(role.getId());
        roleRepository.save(role);
    }
}