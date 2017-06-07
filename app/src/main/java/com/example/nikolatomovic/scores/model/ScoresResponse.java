package com.example.nikolatomovic.scores.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikolatomovic on 6/6/17.
 */

public class ScoresResponse {
    @SerializedName("livescores")
    private Match[] matchs;
    @SerializedName("delta")
    private String delta;

    public Match[] getMatchs() {
        return matchs;
    }

    public String getDelta() {
        return delta;
    }
}
