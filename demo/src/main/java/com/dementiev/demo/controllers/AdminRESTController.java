package com.dementiev.demo.controllers;

import com.dementiev.demo.model.Role;
import com.dementiev.demo.model.User;
import com.dementiev.demo.service.RoleServiceImpl;
import com.dementiev.demo.service.UserServiceImpl;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("rest/")
public class AdminRESTController {
    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AdminRESTController(UserServiceImpl userService, RoleServiceImpl roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> showUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> showUser() {
        return new ResponseEntity<> (userService.getCurrentUser(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> showUser(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<> (user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<HttpStatus> saveNewUser( @RequestBody User user) {
       // user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles();
        userService.saveUser(user);
        return new ResponseEntity<> (HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<> (HttpStatus.OK);
    }


    @PatchMapping("/users/{id}")
    public ResponseEntity<HttpStatus> userSaveEdit(@RequestBody @NotNull User user, @PathVariable Long id) {
        user.setId(id);
//        System.out.println(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.editUser(id, user);

        return new ResponseEntity<> (HttpStatus.OK);
    }

//    @GetMapping("/roles")
//    public ResponseEntity<List<Role>> getAllRoles() {
//        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
//    }
}
