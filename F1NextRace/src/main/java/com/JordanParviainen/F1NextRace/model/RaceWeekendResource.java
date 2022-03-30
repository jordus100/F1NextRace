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


    public RaceWeekendResource() {
        RaceWeekend.initRaceWeekend();
    }

    @GetMapping("/get")
    public ResponseEntity<RaceWeekend> fetchRaceWeekend() {
        return new ResponseEntity<RaceWeekend>(RaceWeekend.getRaceWeekend(), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<RaceWeekend> updateRaceWeekend() {
        try {
            RaceWeekend.getRaceWeekend().fetchData();
            return new ResponseEntity<RaceWeekend>(RaceWeekend.getRaceWeekend(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<RaceWeekend>(RaceWeekend.getRaceWeekend(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
