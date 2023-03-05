package com.dementiev.demo.util;
import com.dementiev.demo.model.Role;
import com.dementiev.demo.model.User;
import com.dementiev.demo.service.RoleService;
import com.dementiev.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class DBInit {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DBInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void dataBaseInit() {
        User user = new User("user", "111", 20, "user@mail.ru", "user");
        User admin = new User("admin", "222", 30,"admin@mail.ru", "admin");
        User userAdmin = new User("userAdmin", "333", 45, "userAdmin@mail.ru", "userAdmin");
        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");

        roleService.addRole(userRole);
        roleService.addRole(adminRole);
        userService.add(user, new String[]{"ROLE_USER"});
        userService.add(admin, new String[]{"ROLE_ADMIN"});
        userService.add(userAdmin, new String[]{"ROLE_ADMIN", "ROLE_USER"});
    }
}

