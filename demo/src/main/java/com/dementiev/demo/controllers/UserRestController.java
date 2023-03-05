package com.dementiev.demo.controllers;
import com.dementiev.demo.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user/logIn")
public class UserRestController {

    @GetMapping
    public User userPage(@AuthenticationPrincipal User user) {
       return user;
    }
}
