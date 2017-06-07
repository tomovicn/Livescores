package com.example.nikolatomovic.scores.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikolatomovic on 6/6/17.
 */

public class Team {
    @SerializedName("team_id")
    private String id;
    @SerializedName("name")
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
