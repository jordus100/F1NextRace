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
            JsonElement tree = JsonParser.parseString(httpResponse.body());
            System.out.println(searchJsonTree(tree, "limit"));
            return true;
        } catch(Exception e){
            return false;
        }
    }

    String searchJsonTree(JsonElement tree, String key){
        if(tree.isJsonObject()){
            if(((JsonObject) tree).has(key))
                return ((JsonObject) tree).get(key).getAsString();
            else{
                for(Map.Entry<String, JsonElement> entry: ((JsonObject) tree).entrySet()) {
                    String value = searchJsonTree(entry.getValue(), key);
                    if (value != null) return value;
                    else return null;
                }
            }
        }
        else if(tree.isJsonArray()){
            Iterator<JsonElement> jsonElements = ((JsonArray) tree).iterator();
            while(jsonElements.hasNext()){
                String value = searchJsonTree(jsonElements.next(), key);
                if(value != null) return value;
                else return null;
            }
        }
        else if(tree.isJsonPrimitive()) return null;
        else return null;
        return key;
    }
}
