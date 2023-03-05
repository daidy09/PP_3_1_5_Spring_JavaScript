package com.dementiev.demo.service;


import com.dementiev.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();



    void add(User user, String [] newRoles);

    void delete(Long id);

    User getUserById(Long id);

    void update(User user, String [] editRoles);

    User findUserByFirstName(String name);

}

