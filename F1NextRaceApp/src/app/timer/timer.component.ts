import { Component, OnInit } from '@angular/core';
import {DataServiceService} from "../data-service.service";
import {RaceWeekend} from "../race-weekend";

@Component({
  selector: 'app-timer',
  templateUrl: './timer.component.html',
  styleUrls: ['./timer.component.css']
})
export class TimerComponent implements OnInit {

  days!: Number;
  hours!: Number;
  minutes!: Number;
  seconds!: Number;

  raceWeekend! : RaceWeekend;
  constructor(private dataService: DataServiceService) { }

  ngOnInit(): void {
    this.dataService.getRaceWeekendData().subscribe(data => {
      this.raceWeekend = data;
    });
  }

}
