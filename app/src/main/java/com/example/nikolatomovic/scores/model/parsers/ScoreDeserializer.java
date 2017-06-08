package com.example.nikolatomovic.scores.model.parsers;

import com.example.nikolatomovic.scores.model.Score;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by nikolatomovic on 6/7/17.
 */

public class ScoreDeserializer implements JsonDeserializer<Score> {
    @Override
    public Score deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();
            if (jsonObject.get("home_team").isJsonPrimitive() && jsonObject.get("guest_team").isJsonPrimitive()) {
                int home;
                int guest;
                try {
                    home = jsonObject.get("home_team").getAsInt();
                    guest = jsonObject.get("guest_team").getAsInt();
                } catch (NumberFormatException e) {
                    home = 0;
                    guest = 0;
                }
                return new Score(home, guest);
            } else return null;
        } else  return null;
    }
}
