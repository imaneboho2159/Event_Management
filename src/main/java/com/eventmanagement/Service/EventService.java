package com.eventmanagement.Service;


import com.eventmanagement.DTO.EventDto;
import com.eventmanagement.Mapper.EventMapper;
import com.eventmanagement.Mapper.UserMapper;
import com.eventmanagement.Model.Event;
import com.eventmanagement.Repository.EventRepository;
import com.eventmanagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final  EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Autowired
    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    public EventDto addEvent(EventDto eventDto){
        Event event=eventMapper.toEvent(eventDto);
        Event savedEvent=eventRepository.save(event);
       return eventMapper.toDto(savedEvent);


    }
    public List<EventDto> getAllEvents() {
        List<EventDto> dtos = new ArrayList<>();
        for (Event event : eventRepository.findAll()) {
            dtos.add(eventMapper.toDto(event));
        }
        return dtos;
    }
    public EventDto updateEvent(Long id, EventDto eventDto) throws ResourceNotFoundException {
        Event event = eventRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Event not found with this id :" + id));
        event.setTitle(eventDto.title());
        event.setDescription(eventDto.description());
        event.setDate(LocalDate.from(eventDto.date()));
        event.setLocation(eventDto.location());
        event.setAvailableSeats(eventDto.availableSeats());
        return eventMapper.toDto(eventRepository.save(event));
    }

    public void deleteEvent(Long id) throws ResourceNotFoundException {
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event not found with id: " + id);
        }
        eventRepository.deleteById(id);
    }
    public EventDto getEventById(Long id) throws ResourceNotFoundException {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
        return eventMapper.toDto(event);
    }
}
