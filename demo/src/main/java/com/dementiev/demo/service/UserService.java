package com.dementiev.demo.service;

import com.dementiev.demo.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void saveUser(User user);

    void editUser(Long id, User user);

    void deleteUser(Long id);

    List<User> getAllUsers();

    User getByUsername(String login);

    User getUserById(Long id);
    User getCurrentUser();
}
