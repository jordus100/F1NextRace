package com.JordanParviainen.F1NextRace.model;

import com.google.gson.*;

import java.io.FileReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class RaceWeekend {
    private Date raceStartDate;
    private Date qualiStartDate;
    private String GPName;
    private String countryFlagImageURL;

    public RaceWeekend() {
        try{fetchLatestData();}
        catch (Exception e){;}
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

    public Boolean fetchLatestData() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://ergast.com/api/f1/current/next.json"))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonElement jsonTree = JsonParser.parseString(httpResponse.body());
        //GPName = JsonHandler.searchJsonTree(jsonTree, "raceName", 1);
        //String dateDay = JsonHandler.searchJsonTree(jsonTree, "date", 2);
        String dateTime = JsonHandler.searchJsonTree(jsonTree, "time",3);
        /*
        StringBuffer sb= new StringBuffer(dateTime);
        sb.deleteCharAt(sb.length()-1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        raceStartDate = dateFormat.parse(dateDay + " " + dateTime, new ParsePosition(0));
        */
        System.out.println(dateTime);
        return true;
    }


}
