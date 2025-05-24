package com.eventmanagement.Mapper;

import com.eventmanagement.DTO.ReservationDto;
import com.eventmanagement.Model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface reservationMapper {
    reservationMapper INSTANCE = Mappers.getMapper(reservationMapper.class);
    ReservationDto toDto(Reservation reservation);
    Reservation fromDto(ReservationDto reservationDto);
}
