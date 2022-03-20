package com.JordanParviainen.F1NextRace.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RaceWeekendResource {

    private final RaceWeekend raceWeekend;

    public RaceWeekendResource() {
        this.raceWeekend = new RaceWeekend();
    }

    @GetMapping
    public ResponseEntity<RaceWeekend> getRaceWeekend() {
        return new ResponseEntity<RaceWeekend>(raceWeekend, HttpStatus.OK);
    }
}
