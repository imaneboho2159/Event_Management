package com.eventmanagement.Security.Athentication;

import com.eventmanagement.Model.Role;
import com.eventmanagement.Model.User;
import com.eventmanagement.Repository.UserRepository;
import com.eventmanagement.Security.JWT.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AdminSetupController {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.setup.secret}")
    private String adminSetupSecret;

    @PostMapping("/setup")
    public ResponseEntity<AutheResponse> setupAdmin(@RequestBody AdminRegisterRequest request) {
        System.out.println("Request received: " + request);
        System.out.println("Received secretKey: " + request.secretKey());
        System.out.println("Expected secretKey: " + adminSetupSecret);
        if (!adminSetupSecret.equals(request.secretKey())) {
            System.out.println("Secret key mismatch");
            return ResponseEntity.status(403).build();
        }
        try {
            User user = User.builder()
                    .email(request.email())
                    .password(passwordEncoder.encode(request.password()))
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(user);
            String token = jwtService.generateToken(user);
            System.out.println("Admin created successfully: " + user.getEmail());
            return ResponseEntity.ok(new AutheResponse(token));
        } catch (Exception e) {
            System.out.println("Error creating admin: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }}

record AdminRegisterRequest(String email, String password, String secretKey) {}