package com.trainreservation.controller;

import com.trainreservation.model.Train;
import com.trainreservation.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trains")
@CrossOrigin(origins = "*")
public class TrainController {

    @Autowired
    private TrainService trainService;

    // GET all trains
    @GetMapping
    public ResponseEntity<List<Train>> getAllTrains() {
        return ResponseEntity.ok(trainService.getAllTrains());
    }

    // GET search trains by source and destination
    @GetMapping("/search")
    public ResponseEntity<List<Train>> searchTrains(
            @RequestParam String source,
            @RequestParam String destination) {
        return ResponseEntity.ok(trainService.searchTrains(source, destination));
    }

    // GET train by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getTrainById(@PathVariable Long id) {
        return trainService.getTrainById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
