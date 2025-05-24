package com.eventmanagement.Repository;

import com.eventmanagement.Model.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends CrudRepository<Event, Integer> {


    Optional<Event> findById(Long id);
    boolean existsById(Long id);

    void deleteById(Long id);
}
