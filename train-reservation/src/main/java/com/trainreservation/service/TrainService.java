package com.trainreservation.service;

import com.trainreservation.model.Train;
import com.trainreservation.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainService implements CommandLineRunner {

    @Autowired
    private TrainRepository trainRepository;

    // Seed sample trains on startup
    @Override
    public void run(String... args) {
        trainRepository.save(new Train("12301", "Rajdhani Express",    "Mumbai",    "Delhi",     "16:00", "08:35", 120, 1450.0, "Superfast"));
        trainRepository.save(new Train("12302", "Shatabdi Express",    "Delhi",     "Mumbai",    "06:00", "22:30", 100, 1350.0, "Superfast"));
        trainRepository.save(new Train("12303", "Duronto Express",     "Mumbai",    "Kolkata",   "11:05", "14:45", 150, 1200.0, "Express"));
        trainRepository.save(new Train("12304", "Garib Rath",          "Delhi",     "Chennai",   "22:30", "06:15", 200, 750.0,  "Express"));
        trainRepository.save(new Train("12305", "Deccan Queen",        "Mumbai",    "Pune",      "07:15", "10:25", 80,  250.0,  "Express"));
        trainRepository.save(new Train("12306", "Chennai Express",     "Mumbai",    "Chennai",   "13:00", "15:30", 180, 1100.0, "Superfast"));
        trainRepository.save(new Train("12307", "Mysore Express",      "Bangalore", "Chennai",   "06:30", "11:00", 120, 400.0,  "Express"));
        trainRepository.save(new Train("12308", "Coromandel Express",  "Kolkata",   "Chennai",   "14:55", "17:40", 160, 1300.0, "Superfast"));
        trainRepository.save(new Train("12309", "Jan Shatabdi",        "Delhi",     "Bangalore", "06:00", "06:30", 90,  1600.0, "Superfast"));
        trainRepository.save(new Train("12310", "Pune Intercity",      "Pune",      "Mumbai",    "07:00", "09:50", 100, 200.0,  "Local"));
        trainRepository.save(new Train("12311", "Kolkata Mail",        "Delhi",     "Kolkata",   "19:30", "10:05", 140, 950.0,  "Express"));
        trainRepository.save(new Train("12312", "Bangalore Express",   "Chennai",   "Bangalore", "23:00", "05:30", 130, 380.0,  "Express"));
    }

    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    public Optional<Train> getTrainById(Long id) {
        return trainRepository.findById(id);
    }

    public List<Train> searchTrains(String source, String destination) {
        return trainRepository.findBySourceIgnoreCaseAndDestinationIgnoreCase(source, destination);
    }

    public Train save(Train train) {
        return trainRepository.save(train);
    }
}
