package com.eventmanagement.DTO;

import com.eventmanagement.Model.Role;

public record UserDto(
     Long id,
     String email,
     Role role
){}
