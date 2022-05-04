import { Component, OnInit } from '@angular/core';
import {DataServiceService} from "../data-service.service";
import {RaceWeekend} from "../race-weekend";
import {Data} from "@angular/router";

@Component({
  selector: 'app-timer',
  templateUrl: './timer.component.html',
  styleUrls: ['./timer.component.css']
})
export class TimerComponent implements OnInit {

  days!: number;
  hours!: number;
  minutes!: number;
  seconds!: number;

  raceWeekend! : RaceWeekend;
  constructor(private dataService: DataServiceService) { }

  ngOnInit(): void {
    this.dataService.getRaceWeekendData().subscribe(data => {
      this.raceWeekend = data;
      this.calcTimerValues(new Date(this.raceWeekend.raceStartDate));
      setInterval(()=> { this.calcTimerValues(new Date(this.raceWeekend.raceStartDate)) }, 500);
    });
  }

  private calcTimerValues(date: Date){
    let now: Date = new Date();
    let timeDifference: number = date.getTime() - now.getTime();
    if(timeDifference<=0){
      this.days = 0;
      this.hours = 0;
      this.minutes = 0;
      this.seconds = 0;
      return;
    }
    timeDifference /= 1000;
    this.days = Math.floor(timeDifference / (60*60*24));
    timeDifference -= this.days * (60*60*24);
    this.hours = Math.floor(timeDifference / (60*60));
    timeDifference -= this.hours * (60*60);
    this.minutes = Math.floor(timeDifference / 60);
    timeDifference -= this.minutes * 60;
    this.seconds = Math.floor(timeDifference);
}
}
