package com.eventmanagement.DTO;

import java.time.LocalDate;

public record ReservationDto(Long id,Long userId,Long eventId,LocalDate reservationDate,int numberOfSeats
) {


    }
