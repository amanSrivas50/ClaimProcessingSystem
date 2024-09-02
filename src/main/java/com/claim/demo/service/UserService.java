package com.claim.demo.service;

import com.claim.demo.dto.UserDTO;
import com.claim.demo.entity.User;
import com.claim.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO registerUser(User user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        user.setCreatedAt(new Date());
        user.setStatus("active");
        return convertToDTO(userRepository.save(user));
    }

    public UserDTO loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPasswordHash())) {
            user.setLastLogin(new Date());
            userRepository.save(user);
            return convertToDTO(user);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
    public UserDTO updateUser(Long userId, User updatedUser) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(updatedUser.getEmail());
        user.setRole(updatedUser.getRole());
        user.setStatus(updatedUser.getStatus());
        user.setUpdatedAt(new Date());
        userRepository.save(user);
        return convertToDTO(user);
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    public UserDTO findUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(user);
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(
            user.getUserId(),
            user.getUsername(),
            user.getEmail(),
            user.getRole(),
            user.getStatus()
        );
    }

    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    
}

   