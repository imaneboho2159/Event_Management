package com.eventmanagement.Service;


import com.eventmanagement.DTO.ReservationDto;
import com.eventmanagement.Mapper.ReservationMapper;
import com.eventmanagement.Model.Event;
import com.eventmanagement.Model.Reservation;
import com.eventmanagement.Model.User;
import com.eventmanagement.Repository.EventRepository;
import com.eventmanagement.Repository.ReservationRepository;
import com.eventmanagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class ReservationService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper, UserRepository userRepository, EventRepository eventRepository) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }
    public ReservationDto createReservation(ReservationDto reservationDto) {
        if (reservationDto.userId() == null || reservationDto.eventId() == null) {
            throw new IllegalArgumentException("User ID and Event ID must not be null");
        }

        Event event = eventRepository.findById(reservationDto.eventId())
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        if (reservationDto.numberOfSeats()> event.getAvailableSeats()) {
            throw new IllegalArgumentException("Not enough seats available");
        }

        User user = userRepository.findById(reservationDto.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Reservation reservation = reservationMapper.fromDto(reservationDto);
        reservation.setUser(user);
        reservation.setEvent(event);
        reservation.setReservationDate(LocalDate.now());


        event.setAvailableSeats(event.getAvailableSeats() - reservationDto.numberOfSeats());
        eventRepository.save(event);

        Reservation savedReservation = reservationRepository.save(reservation);
        return reservationMapper.toDto(savedReservation);
    }

}
