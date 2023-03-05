package com.dementiev.demo.controllers;

import com.dementiev.demo.model.User;
import com.dementiev.demo.service.RoleService;
import com.dementiev.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsersList() {
        return new ResponseEntity<>(userService.allUsers(), HttpStatus.OK);

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(value = "id") Long id) {
        return userService.getUserById(id) != null
                ? new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody User user, @RequestParam(name = "editRoles") String[] editRoles) {
        userService.update(user, editRoles);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/users/add")
    public User saveUser(@RequestBody User user, @RequestParam(name = "roles") String[] newRoles) {
        userService.add(user, newRoles);
        return user;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable(value = "id") Long id) {
        userService.delete(id);
    }

}
