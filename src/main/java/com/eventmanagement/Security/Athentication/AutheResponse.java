package com.eventmanagement.Security.Athentication;


import lombok.Data;

@Data

public class AutheResponse {
    private String token;

    // Constructor that accepts a String parameter
    public AutheResponse(String token) {
        this.token = token;
    }

    // Optional: Default constructor if needed
    public AutheResponse() {
    }
}
