package com.trainreservation.service;

import com.trainreservation.model.Booking;
import com.trainreservation.model.Train;
import com.trainreservation.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TrainService trainService;

    public Map<String, Object> bookTicket(Long trainId, String passengerName,
                                           int age, String gender, String seatClass) {
        Map<String, Object> response = new HashMap<>();

        Optional<Train> trainOpt = trainService.getTrainById(trainId);
        if (trainOpt.isEmpty()) {
            response.put("success", false);
            response.put("message", "Train not found.");
            return response;
        }

        Train train = trainOpt.get();
        if (train.getAvailableSeats() <= 0) {
            response.put("success", false);
            response.put("message", "No seats available on this train.");
            return response;
        }

        // Generate PNR
        String pnr = "PNR" + System.currentTimeMillis() % 1000000;

        // Assign seat
        int seatNum = train.getTotalSeats() - train.getAvailableSeats() + 1;
        String seatNumber = seatClass.substring(0, 2).toUpperCase() + seatNum;

        // Calculate fare multiplier by class
        double fareMultiplier = switch (seatClass) {
            case "1AC" -> 2.5;
            case "2AC" -> 1.8;
            case "3AC" -> 1.3;
            default   -> 1.0; // SL
        };

        Booking booking = new Booking();
        booking.setPnr(pnr);
        booking.setPassengerName(passengerName);
        booking.setPassengerAge(age);
        booking.setPassengerGender(gender);
        booking.setSeatClass(seatClass);
        booking.setTrain(train);
        booking.setStatus("CONFIRMED");
        booking.setTotalFare(Math.round(train.getFare() * fareMultiplier));
        booking.setBookingTime(LocalDateTime.now());
        booking.setSeatNumber(seatNumber);

        bookingRepository.save(booking);

        // Reduce available seats
        train.setAvailableSeats(train.getAvailableSeats() - 1);
        trainService.save(train);

        response.put("success", true);
        response.put("message", "Ticket booked successfully!");
        response.put("booking", booking);
        return response;
    }

    public Map<String, Object> cancelTicket(String pnr) {
        Map<String, Object> response = new HashMap<>();

        Optional<Booking> bookingOpt = bookingRepository.findByPnr(pnr);
        if (bookingOpt.isEmpty()) {
            response.put("success", false);
            response.put("message", "PNR not found.");
            return response;
        }

        Booking booking = bookingOpt.get();
        if ("CANCELLED".equals(booking.getStatus())) {
            response.put("success", false);
            response.put("message", "Ticket is already cancelled.");
            return response;
        }

        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);

        // Restore seat
        Train train = booking.getTrain();
        train.setAvailableSeats(train.getAvailableSeats() + 1);
        trainService.save(train);

        response.put("success", true);
        response.put("message", "Ticket cancelled successfully.");
        response.put("booking", booking);
        return response;
    }

    public Map<String, Object> getPnrStatus(String pnr) {
        Map<String, Object> response = new HashMap<>();

        Optional<Booking> bookingOpt = bookingRepository.findByPnr(pnr);
        if (bookingOpt.isEmpty()) {
            response.put("success", false);
            response.put("message", "PNR not found.");
            return response;
        }

        response.put("success", true);
        response.put("booking", bookingOpt.get());
        return response;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
