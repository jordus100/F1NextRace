package com.JordanParviainen.F1NextRace.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/raceweekend")
public class RaceWeekendResource {

    private final RaceWeekend raceWeekend;

    public RaceWeekendResource() {
        this.raceWeekend = new RaceWeekend();
    }

    @GetMapping("/get")
    public ResponseEntity<RaceWeekend> getRaceWeekend() {
        return new ResponseEntity<RaceWeekend>(raceWeekend, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<RaceWeekend> updateRaceWeekend() {
        try {
            raceWeekend.fetchData();
            return new ResponseEntity<RaceWeekend>(raceWeekend, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<RaceWeekend>(raceWeekend, HttpStatus.EXPECTATION_FAILED);
        }
    }
}
