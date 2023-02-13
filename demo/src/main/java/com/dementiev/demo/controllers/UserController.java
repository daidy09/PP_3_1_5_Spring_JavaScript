package com.dementiev.demo.controllers;

import com.dementiev.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {
    private UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("user")
    public String oneUser(Model model, Principal principal) {
        model.addAttribute("oneUser", userServiceImpl.getByUsername(principal.getName()));
        return "oneUserPage";
    }
}