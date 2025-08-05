package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
public class ClockRoomController {

 @Autowired
 private ClockRoomRepository clockRoomRepository;

 @GetMapping(\"/search\")
    public List<ClockRoom> searchClockRooms(
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "checkin", required = false) String checkin,
            @RequestParam(value = "checkout", required = false) String checkout) {

 if (location != null && !location.isEmpty() && checkin != null && !checkin.isEmpty() && checkout != null && !checkout.isEmpty()) {
 try {
 LocalDate checkInDate = LocalDate.parse(checkin);
 LocalDate checkOutDate = LocalDate.parse(checkout);
 return clockRoomRepository.findByLocationAndAvailability(location, checkInDate, checkOutDate);
            } catch (DateTimeParseException e) {
 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format. Please use YYYY-MM-DD.");
            }
        } else if (location != null && !location.isEmpty()) {
 return clockRoomRepository.findByLocation(location);
        }
 return clockRoomRepository.findAll();
    }
}