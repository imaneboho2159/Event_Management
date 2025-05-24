package com.eventmanagement.Security.Athentication;

import com.eventmanagement.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record RegisterRequest (
    String email,
     String password,
    Role role

){}