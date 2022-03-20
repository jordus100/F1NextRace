package com.JordanParviainen.F1NextRace.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
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
        fetchLatestData();
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

    public Boolean fetchLatestData() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://ergast.com/api/f1/current/next.json"))
                .build();
        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(httpResponse.body());
            Object obj = new JSONParser().parse(httpResponse.body());
            JSONObject jo = (JSONObject) obj;
            for(Object o : jo.values()){
                System.out.println(o.toString());
            }
            return true;
        } catch(Exception e){
            return false;
        }
    }
}
