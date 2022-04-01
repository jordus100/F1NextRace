import { Component, OnInit } from '@angular/core';
import {RaceWeekend} from "../race-weekend";
import {DataServiceService} from "../data-service.service";

@Component({
  selector: 'app-grand-prix',
  templateUrl: './grand-prix.component.html',
  styleUrls: ['./grand-prix.component.css']
})
export class GrandPrixComponent implements OnInit {

  raceWeekend!: RaceWeekend;
  constructor(private dataService: DataServiceService) { }

  ngOnInit(): void {
    this.dataService.getRaceWeekendData().subscribe(data => {
      this.raceWeekend = data;
    });
  }

}
