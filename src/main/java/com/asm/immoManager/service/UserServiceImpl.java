package com.asm.immoManager.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.asm.immoManager.entity.User;
import com.asm.immoManager.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    // save new user
    @Override
    public User saveUser(User user) {
        // Check if the admin field is set to false by default
        if (!user.isAdmin()) {
            user.setAdmin(false); // Ensure default value
        }
        return userRepository.save(user);
    }

    // get user by id
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    // delete user by id
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            if (userDetails.getName() != null) {
                user.setName(userDetails.getName());
            }
            if (userDetails.getEmail() != null) {
                user.setEmail(userDetails.getEmail());
            }
            if (userDetails.getPassword() != null) {
                user.setPassword(userDetails.getPassword());
            }
            if (userDetails.getPhone() != null) {
                user.setPhone(userDetails.getPhone());
            }
            user.setAdmin(userDetails.isAdmin()); // Assuming admin has a default value of false
            return userRepository.save(user);
        }).orElse(null); // Return null if user not found
    }

    // get all users
    @Override
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

}
