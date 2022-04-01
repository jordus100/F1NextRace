import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {RaceWeekend} from "./race-weekend";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class DataServiceService {

  private apiUrl: string;
  constructor(private http: HttpClient) {
    this.apiUrl = "http://localhost:8080/raceweekend";
  }
  public getRaceWeekendData(): Observable<RaceWeekend> {
    return this.http.get<RaceWeekend>(this.apiUrl+'/get');
  }
}
