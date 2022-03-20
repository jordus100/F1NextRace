package com.JordanParviainen.F1NextRace.model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;

public class RaceWeekend {
    private Date raceStartDate;
    private Date qualiStartDate;
    private String GPName;
    private String countryFlagImageURL;

    public RaceWeekend() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://ergast.com/api/f1/current/next.json"))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch(Exception e){
            System.out.println("Cos nie dziala");
        }
    }

    public Date getRaceStartDate() {
        return raceStartDate;
    }

    public Date getQualiStartDate() {
        return qualiStartDate;
    }

    public String getGPName() {
        return GPName;
    }

    public String getCountryFlagImageURL() {
        return countryFlagImageURL;
    }
}
