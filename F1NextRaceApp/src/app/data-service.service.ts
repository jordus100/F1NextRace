import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {RaceWeekend} from "./race-weekend";
import {HttpClient} from "@angular/common/http";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class DataServiceService {

  private apiUrl: string;
  constructor(private http: HttpClient) {
    this.apiUrl = environment.apiUrl;
  }
  public getRaceWeekendData(): Observable<RaceWeekend> {
    return this.http.get<RaceWeekend>(this.apiUrl+'/get');
  }
}
