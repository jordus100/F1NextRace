package com.JordanParviainen.F1NextRace.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.Iterator;
import java.util.Map;

public class JsonHandler {

    private static int occurrenceAux;

    /*
        searches for a simple string in a json file by its key and the number of
        occurrence from the beginning of the file in case of the same key being used
        multiple times in the file
     */
    public static String searchJsonTree(JsonElement jsonTree, String key, int occurrence){
        occurrenceAux = occurrence;
        if(jsonTree.isJsonObject()){
            for(Map.Entry<String, JsonElement> entry: ((JsonObject) jsonTree).entrySet()) {
                String value;
                if(entry.getKey().equals(key))
                    if(entry.getValue().isJsonPrimitive()) // our method only searches for simple strings
                        value = ((JsonPrimitive)entry.getValue()).getAsString();
                    else
                        value = null;
                else
                    if( !entry.getValue().isJsonPrimitive())
                        value = searchJsonTree(entry.getValue(), key, occurrenceAux);
                    else
                        value = null;
                if (value != null){
                    if(occurrenceAux == 1)
                        return value;
                    else{
                        occurrenceAux--;
                        continue;
                    }
                }
            }
            return null;
        }
        else if(jsonTree.isJsonArray()){
            Iterator<JsonElement> jsonElements = ((JsonArray) jsonTree).iterator();
            while(jsonElements.hasNext()){
                //JsonElement arrayElement = jsonElements.next();
                String value = searchJsonTree(jsonElements.next(), key, occurrenceAux);
                if(value != null){
                    if(occurrenceAux == 1)
                        return value;
                    else{
                        occurrenceAux--;
                        continue;
                    }
                }
            }
            return null;
        }
        else return null;
    }
}
