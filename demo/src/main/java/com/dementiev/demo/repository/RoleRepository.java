package com.dementiev.demo.repository;

import com.dementiev.demo.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Set<Role> findAll();
    Role findRoleById(Long id);
    Role findRoleByRole(String name);
}
