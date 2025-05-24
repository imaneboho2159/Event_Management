package com.eventmanagement.Mapper;


import com.eventmanagement.DTO.EventDto;
import com.eventmanagement.Model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    EventDto toDto(Event event);
    Event toEvent(EventDto eventDto);

}
