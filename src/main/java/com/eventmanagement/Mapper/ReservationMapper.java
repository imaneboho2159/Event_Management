package com.eventmanagement.Mapper;

import com.eventmanagement.DTO.ReservationDto;
import com.eventmanagement.Model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "event.id", target = "eventId")
    ReservationDto toDto(Reservation reservation);


    @Mapping(target = "user", ignore = true)
    @Mapping(target = "event", ignore = true)
    Reservation fromDto(ReservationDto reservationDto);}
