package com.dementiev.demo.controllers;

import com.dementiev.demo.model.User;
import com.dementiev.demo.service.RoleServiceImpl;
import com.dementiev.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userServiceImpl;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleServiceImpl roleService;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, BCryptPasswordEncoder passwordEncoder, RoleServiceImpl roleService) {
        this.userServiceImpl = userServiceImpl;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @GetMapping()
    public String userList(Model model, Principal principal) {
        model.addAttribute("allUsers", userServiceImpl.getAllUsers());
        model.addAttribute("thisUser", userServiceImpl.getByUsername(principal.getName()));
        return "users";
    }

    @GetMapping(value = "/add")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", user);
        return "addUser";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("user") User user) {
        userServiceImpl.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userServiceImpl.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        User user = userServiceImpl.getUserById(id);
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", user);
        return "editUser";
    }

    @PatchMapping(value = "/edit/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userServiceImpl.editUser(id, user);
        return "redirect:/admin";
    }

    @GetMapping("/{userId}")
    public String getUser(@PathVariable("userId") Long userId, Model model) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute("OneUser", userServiceImpl.getUserById(userId));
        return "oneUserPage";
    }
}