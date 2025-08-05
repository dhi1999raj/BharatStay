package com.example.demo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "clock_room_id")
    private ClockRoom clockRoom;

    private LocalDate checkInDate;
    private LocalDate checkoutDate;

    public Booking() {
    }

    public Booking(ClockRoom clockRoom, LocalDate checkInDate, LocalDate checkoutDate) {
        this.clockRoom = clockRoom;
        this.checkInDate = checkInDate;
        this.checkoutDate = checkoutDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClockRoom getClockRoom() {
        return clockRoom;
    }

    public void setClockRoom(ClockRoom clockRoom) {
        this.clockRoom = clockRoom;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }
}