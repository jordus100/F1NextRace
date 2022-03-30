package com.JordanParviainen.F1NextRace.model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledDataUpdater {

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.SECONDS)
    public void updateRaceWeekend() {
        if(RaceWeekend.isInitialized()) {
            Date now = new Date();
            if (now.after(RaceWeekend.getRaceWeekend().getRaceStartDate())) {
                try {
                    RaceWeekend.getRaceWeekend().fetchData();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        }
    }
}
