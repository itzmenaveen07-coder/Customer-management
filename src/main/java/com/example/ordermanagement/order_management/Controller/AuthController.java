package com.example.ordermanagement.order_management.Controller;

import com.example.ordermanagement.order_management.Dto.LoginRequest;
import com.example.ordermanagement.order_management.Dto.RegisterRequest;
import com.example.ordermanagement.order_management.Service.CustomUserDetailsService;
import com.example.ordermanagement.order_management.Service.UserService;
import com.example.ordermanagement.order_management.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final CustomUserDetailsService userDetailsService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        userService.registerUser(request.getName(), request.getEmail(), request.getPassword());
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        final String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(Map.of("token", token));
    }
}
