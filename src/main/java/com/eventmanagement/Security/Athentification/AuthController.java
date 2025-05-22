package com.eventmanagement.Security.Athentification;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")

public class AuthController {



    private final com.eventmanagement.Security.Authentication.AuthService authService;

    @Autowired
    public AuthController(com.eventmanagement.Security.Authentication.AuthService authService){
        this.authService=authService;

    }

   @PostMapping("/register")
   public ResponseEntity<AutheResponse>register(@RequestBody RegisterRequest request){
       AutheResponse response = authService.register(request);
       return ResponseEntity.ok(response);
   }
    @PostMapping("/login")
    public ResponseEntity<AutheResponse> login(@RequestBody AuthRequest request) {
        AutheResponse response = authService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
