package com.JordanParviainen.F1NextRace.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
