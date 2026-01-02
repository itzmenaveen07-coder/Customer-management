package com.example.ordermanagement.order_management.Service;

import com.example.ordermanagement.order_management.entity.Customer;
import com.example.ordermanagement.order_management.entity.User;
import com.example.ordermanagement.order_management.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String password, String role, Customer customer) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // 🔐 ENCRYPT
        user.setRole(role);
        user.setEnabled(true);
        user.setCustomer(customer);

        return userRepository.save(user);
    }
}
