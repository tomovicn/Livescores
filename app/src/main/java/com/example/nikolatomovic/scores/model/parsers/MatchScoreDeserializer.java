package com.example.nikolatomovic.scores.model.parsers;

import com.example.nikolatomovic.scores.model.MatchScore;
import com.example.nikolatomovic.scores.model.Score;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by nikolatomovic on 6/6/17.
 */

public class MatchScoreDeserializer implements JsonDeserializer<MatchScore> {

    @Override
    public MatchScore deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();
            Score current = context.deserialize(jsonObject.get("current"), Score.class);
            Score halfTime = context.deserialize(jsonObject.get("half_time"), Score.class);
            Score normalTime = context.deserialize(jsonObject.get("normal_time"), Score.class);
            return new MatchScore(current, halfTime, normalTime);
        } else return null;
    }

}
