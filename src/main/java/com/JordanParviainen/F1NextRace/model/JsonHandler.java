package com.JordanParviainen.F1NextRace.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.Iterator;
import java.util.Map;

public class JsonHandler {
    public static String searchJsonTree(JsonElement jsonTree, String key){
        if(jsonTree.isJsonObject()){
            if(((JsonObject) jsonTree).has(key))
                return ((JsonObject) jsonTree).get(key).getAsString();
            else{
                for(Map.Entry<String, JsonElement> entry: ((JsonObject) jsonTree).entrySet()) {
                    String value = searchJsonTree(entry.getValue(), key);
                    if (value != null) return value;
                }
                return null;
            }
        }
        else if(jsonTree.isJsonArray()){
            Iterator<JsonElement> jsonElements = ((JsonArray) jsonTree).iterator();
            while(jsonElements.hasNext()){
                String value = searchJsonTree(jsonElements.next(), key);
                if(value != null) return value;
            }
            return null;
        }
        else if(jsonTree.isJsonPrimitive()){
            if(((JsonPrimitive)jsonTree).getAsString().equals(key)) return ((JsonPrimitive)jsonTree).getAsString();
            else return null;
        }
        else return null;
    }
}
