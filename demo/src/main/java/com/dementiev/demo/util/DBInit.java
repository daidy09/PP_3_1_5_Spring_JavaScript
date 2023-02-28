package com.dementiev.demo.util;

import com.dementiev.demo.model.Role;
import com.dementiev.demo.model.User;
import com.dementiev.demo.service.RoleServiceImpl;
import com.dementiev.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DBInit {
    private final UserService userService;
    private final RoleServiceImpl roleService;

    @Autowired
    public DBInit(UserService userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void dataBaseInit() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        Set<Role> adminSet = new HashSet<>();
        Set<Role> userSet = new HashSet<>();

        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);

        adminSet.add(roleAdmin);
        adminSet.add(roleUser);
        userSet.add(roleUser);

        User user1 = new User("Ivan", "Ivanov", 25,"ivanov@mail.com",
                "200", userSet);
        User user2 = new User("Petr", "Petrov", 32, "petrov@gmail.com",
                "100", adminSet);

        userService.saveUser(user1);
        userService.saveUser(user2);
    }
}