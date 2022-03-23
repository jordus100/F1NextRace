package com.JordanParviainen.F1NextRace.model;

import com.google.gson.*;

import java.io.FileReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

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
            JsonElement jsonTree = JsonParser.parseString(httpResponse.body());
            GPName = JsonHandler.searchJsonTree(jsonTree, "raceName");
            String dateDay = JsonHandler.searchJsonTree(jsonTree, "date");
            String dateTime = JsonHandler.searchJsonTree(jsonTree, "time");
            return true;
        } catch(Exception e){
            return false;
        }
    }


}
