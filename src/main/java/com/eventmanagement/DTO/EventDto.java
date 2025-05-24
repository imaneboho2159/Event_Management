package com.eventmanagement.DTO;

import java.time.LocalDateTime;

public record EventDto(
        Long id,
        String title,
        String description,
        LocalDateTime date,
         String location,
        long availableSeats
){}

