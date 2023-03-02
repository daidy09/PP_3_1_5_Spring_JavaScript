package com.dementiev.demo.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "role")
    private String userRole;


    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String userRole) {
        this.id = id;
        this.userRole = userRole;
    }

    public Role(String userRole) {
        this.userRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserRole() {
        return userRole.replace("ROLE_", "");
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String getAuthority() {
        return userRole;
    }

    @Override
    public String toString() {
        return userRole;
    }

}