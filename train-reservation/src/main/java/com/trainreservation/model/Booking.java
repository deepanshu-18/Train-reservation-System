package com.trainreservation.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pnr;
    private String passengerName;
    private int passengerAge;
    private String passengerGender;
    private String seatClass; // SL, 3AC, 2AC, 1AC

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    private String status; // CONFIRMED, CANCELLED
    private double totalFare;
    private LocalDateTime bookingTime;
    private String seatNumber;

    public Booking() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPnr() { return pnr; }
    public void setPnr(String pnr) { this.pnr = pnr; }

    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }

    public int getPassengerAge() { return passengerAge; }
    public void setPassengerAge(int passengerAge) { this.passengerAge = passengerAge; }

    public String getPassengerGender() { return passengerGender; }
    public void setPassengerGender(String passengerGender) { this.passengerGender = passengerGender; }

    public String getSeatClass() { return seatClass; }
    public void setSeatClass(String seatClass) { this.seatClass = seatClass; }

    public Train getTrain() { return train; }
    public void setTrain(Train train) { this.train = train; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getTotalFare() { return totalFare; }
    public void setTotalFare(double totalFare) { this.totalFare = totalFare; }

    public LocalDateTime getBookingTime() { return bookingTime; }
    public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
}
