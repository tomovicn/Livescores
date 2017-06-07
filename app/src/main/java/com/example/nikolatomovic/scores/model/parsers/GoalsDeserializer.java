package com.example.nikolatomovic.scores.model.parsers;

import com.example.nikolatomovic.scores.model.Goal;
import com.example.nikolatomovic.scores.model.Goals;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by nikolatomovic on 6/6/17.
 */

public class GoalsDeserializer implements JsonDeserializer<Goals> {
    @Override
    public Goals deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();
            ArrayList<Goal> homeTeam = context.deserialize(jsonObject.get("home_team"), new TypeToken<ArrayList<Goal>>() {
            }.getType());
            ArrayList<Goal> guestTeam = context.deserialize(jsonObject.get("guest_team"), new TypeToken<ArrayList<Goal>>() {
            }.getType());
            ArrayList<Goal> byTime = context.deserialize(jsonObject.get("by_time"), new TypeToken<ArrayList<Goal>>() {
            }.getType());
            return new Goals(homeTeam, guestTeam, byTime);

        } else return null;
    }
}
