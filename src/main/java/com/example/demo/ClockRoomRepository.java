package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;

import java.util.List;

public interface ClockRoomRepository extends JpaRepository<ClockRoom, Long> {
    List<ClockRoom> findByLocation(String location);

    @Query("SELECT cr FROM ClockRoom cr WHERE cr.location = :location AND NOT EXISTS (SELECT b FROM Booking b WHERE b.clockRoom.id = cr.id AND (:checkin < b.checkoutDate AND :checkout > b.checkInDate))")
    List<ClockRoom> findByLocationAndAvailability(String location, LocalDate checkin, LocalDate checkout);
}