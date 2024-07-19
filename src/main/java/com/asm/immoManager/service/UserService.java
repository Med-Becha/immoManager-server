package com.asm.immoManager.service;

import java.util.List;
import com.asm.immoManager.entity.User;

public interface UserService {

    // create user
    User saveUser(User user);

    // get user
    User getUserById(Long id);

    // update user profile
    User updateUser(Long id, User user);

    // User loginUser(User user);
    User loginUser(String email, String password);

    // delete User
    void deleteUser(Long id);

    // get all users
    List<User> getUsers();
}