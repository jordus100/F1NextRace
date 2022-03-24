package com.JordanParviainen.F1NextRace.model;

import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        HttpResponse<String> httpResponse;
        httpResponse = callApi("http://ergast.com/api/f1/current/next.json");
        JsonElement jsonTree;
        jsonTree = JsonParser.parseString(httpResponse.body());

        GPName = JsonHandler.searchJsonTree(jsonTree, "raceName", 1);

        String raceDateDay = JsonHandler.searchJsonTree(jsonTree, "date", 1);
        String raceDateTime = JsonHandler.searchJsonTree(jsonTree, "time",1);
        String qualiDateDay = JsonHandler.searchJsonTree(jsonTree, "date", 5);
        String qualiDateTime = JsonHandler.searchJsonTree(jsonTree, "time",5);

        raceStartDate = convertToDate(raceDateTime, raceDateDay);
        qualiStartDate = convertToDate(qualiDateTime, qualiDateDay);

        httpResponse = callApi("https://serpapi.com/search.json?q=SaudiArabiaFlag&tbm=isch&ijn=0&api_key=eae31813841545b64c7a8828e6c820e34fc741ea56701bd9e727a49b6c8bb75f");
        jsonTree = JsonParser.parseString(httpResponse.body());
        countryFlagImageURL = JsonHandler.searchJsonTree(jsonTree, "link", 2);

        System.out.println(countryFlagImageURL);
        return true;
    }

    private HttpResponse<String> callApi(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        return httpResponse;
    }

    // this is not a very universal method, it just works for the date strings from the Ergast API
    private Date convertToDate(String time, String day){
        Date date;
        StringBuffer stringBuf = new StringBuffer(time);
        stringBuf.replace(stringBuf.length()-1, stringBuf.length(), "+0000");
        time = stringBuf.toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
        date = dateFormat.parse(day + " " + time, new ParsePosition(0));
        return date;
    }

}
