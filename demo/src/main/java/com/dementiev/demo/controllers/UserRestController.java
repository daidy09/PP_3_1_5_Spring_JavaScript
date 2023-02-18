package com.dementiev.demo.controllers;

import com.dementiev.demo.model.User;
import com.dementiev.demo.service.RoleServiceImpl;
import com.dementiev.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserRestController {
    private UserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleService;

    @Autowired
    public UserRestController(UserServiceImpl userServiceImpl, RoleServiceImpl roleService) {
        this.userServiceImpl = userServiceImpl;
        this.roleService = roleService;
    }

    @GetMapping("/user")
    public ResponseEntity<User> showAuthUser() {
        return new ResponseEntity<> (userServiceImpl.getCurrentUser(), HttpStatus.OK);
    }
}
