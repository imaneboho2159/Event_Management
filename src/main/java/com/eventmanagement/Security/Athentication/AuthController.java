package com.eventmanagement.Security.Athentication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AutheResponse> register(@RequestBody RegisterRequest request) {
        AutheResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AutheResponse> login(@RequestBody AuthRequest request) {
        AutheResponse response = authService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
