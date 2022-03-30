package com.JordanParviainen.F1NextRace.model;

import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

public class RaceWeekend {
    private static RaceWeekend raceWeekend;
    private Date raceStartDate;
    private Date qualiStartDate;
    private String gPName;
    private String countryFlagImageURL;
    private int imageSize = 640; /* a limited amount of values is possible, check Flagpedia API docs at
                                    "https://flagpedia.net/download/api" */

    public static Boolean initRaceWeekend(){
        if(raceWeekend == null){
            raceWeekend = new RaceWeekend();
            return true;
        }
        else return false;
    }

    public static RaceWeekend getRaceWeekend(){
        return raceWeekend;
    }

    private RaceWeekend() {
        try{ fetchData(); }
        catch (Exception e) { System.out.println(e.toString()); }
    }

    public static Boolean isInitialized() {
        if (raceWeekend != null) return true;
        else return false;
    }

    public Date getRaceStartDate() {
        return raceStartDate;
    }

    public Date getQualiStartDate() {
        return qualiStartDate;
    }

    public String getgPName() {
        return gPName;
    }

    public String getCountryFlagImageURL() {
        return countryFlagImageURL;
    }

    public void fetchData() throws Exception {
        // downloading data from a public API containing up-to-date statistical data about Formula 1
        JsonElement jsonTree = getJsonFromApi("http://ergast.com/api/f1/current/next.json");

        gPName = JsonHandler.searchJsonTree(jsonTree, "raceName", 1);

        String raceDateDay = JsonHandler.searchJsonTree(jsonTree, "date", 1);
        String raceDateTime = JsonHandler.searchJsonTree(jsonTree, "time",1);
        String qualiDateDay = JsonHandler.searchJsonTree(jsonTree, "date", 5);
        String qualiDateTime = JsonHandler.searchJsonTree(jsonTree, "time",5);

        String countryName = JsonHandler.searchJsonTree(jsonTree, "country", 1);

        raceStartDate = convertToDate(raceDateTime, raceDateDay);
        qualiStartDate = convertToDate(qualiDateTime, qualiDateDay);

        String flagCode = getFlagCode(getJsonFromApi("https://flagcdn.com/en/codes.json"), countryName);
        countryFlagImageURL = "https://flagcdn.com/w" + imageSize + "/" + flagCode + ".png";

    }

    private JsonElement getJsonFromApi(String url) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(10))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        return JsonParser.parseString(httpResponse.body());
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

    private String getFlagCode(JsonElement jsonTree, String countryName){
        for(Map.Entry<String, JsonElement> entry: ((JsonObject) jsonTree).entrySet()){
            if(entry.getValue().getAsString().equals(countryName))
                return entry.getKey().toString();
        }
        return null;
    }

}
