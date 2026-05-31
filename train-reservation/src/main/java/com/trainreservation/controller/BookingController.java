package com.trainreservation.controller;

import com.trainreservation.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // POST book a ticket
    @PostMapping("/book")
    public ResponseEntity<Map<String, Object>> bookTicket(@RequestBody Map<String, Object> request) {
        Long trainId = Long.valueOf(request.get("trainId").toString());
        String passengerName = request.get("passengerName").toString();
        int age = Integer.parseInt(request.get("age").toString());
        String gender = request.get("gender").toString();
        String seatClass = request.get("seatClass").toString();

        Map<String, Object> result = bookingService.bookTicket(trainId, passengerName, age, gender, seatClass);
        return ResponseEntity.ok(result);
    }

    // GET PNR status
    @GetMapping("/status/{pnr}")
    public ResponseEntity<Map<String, Object>> getPnrStatus(@PathVariable String pnr) {
        return ResponseEntity.ok(bookingService.getPnrStatus(pnr));
    }

    // DELETE cancel ticket
    @PutMapping("/cancel/{pnr}")
    public ResponseEntity<Map<String, Object>> cancelTicket(@PathVariable String pnr) {
        return ResponseEntity.ok(bookingService.cancelTicket(pnr));
    }

    // GET all bookings
    @GetMapping
    public ResponseEntity<?> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }
}
