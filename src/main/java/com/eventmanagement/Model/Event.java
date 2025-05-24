package com.eventmanagement.Model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder

@AllArgsConstructor
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String location;
    @Column
    private LocalDate date;
    @Column
    private long availableSeats;


    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public Event() {
    }

    public Event(String title, String description, String location, LocalDate date, long availableSeats, List<Reservation> reservations) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.date = date;
        this.availableSeats = availableSeats;
        this.reservations = reservations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(long availableSeats) {
        this.availableSeats = availableSeats;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
