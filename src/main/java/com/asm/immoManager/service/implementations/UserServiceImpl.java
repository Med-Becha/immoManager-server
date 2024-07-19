package com.asm.immoManager.service.implementations;

import java.util.List;

// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.asm.immoManager.entity.User;
import com.asm.immoManager.repository.UserRepository;
import com.asm.immoManager.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    // BCryptPasswordEncoder bCryptPasswordEncoder;

    // save new user
    @Override
    public User saveUser(User user) {
        // Encode the password
        // user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        // Check if the admin field is set to false by default
        if (!user.isAdmin()) {
            user.setAdmin(false); // Ensure default value
        }
        return userRepository.save(user);
    }

    // login user
    @Override
    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null || (password == user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        return user;
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
