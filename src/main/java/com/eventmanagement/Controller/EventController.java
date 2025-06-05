package com.eventmanagement.Controller;

import com.eventmanagement.DTO.EventDto;
import com.eventmanagement.Repository.EventRepository;
import com.eventmanagement.Service.EventService;
import com.eventmanagement.Service.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:4200")
public class EventController {



    private final EventService eventService;
    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<EventDto> AddEvents(@RequestBody EventDto eventDto) {
        return ResponseEntity.ok(eventService.addEvent( eventDto));

    }

    @GetMapping
    public ResponseEntity<List<EventDto>> getEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

   @PutMapping("/{id}")
    public ResponseEntity<EventDto> updateEvent(@PathVariable Long id, @RequestBody EventDto eventDto) throws ResourceNotFoundException {
        return ResponseEntity.ok(eventService.updateEvent(id,eventDto ));
   }
   @GetMapping("/{id}")
    public ResponseEntity<EventDto> getEventById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(eventService.getEventById(id));
   }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) throws ResourceNotFoundException {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
