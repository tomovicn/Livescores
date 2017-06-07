package com.example.nikolatomovic.scores.model.parsers;

import com.example.nikolatomovic.scores.model.Cards;
import com.example.nikolatomovic.scores.model.CardsGroup;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by nikolatomovic on 6/6/17.
 */

public class CardsDeserializer implements JsonDeserializer<CardsGroup> {
    @Override
    public CardsGroup deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();
            Cards homeTeam = context.deserialize(jsonObject.get("home_team"), Cards.class);
            Cards guestTeam = context.deserialize(jsonObject.get("guest_team"), Cards.class);
            return new CardsGroup(homeTeam, guestTeam);

        } else return null;
    }
}
