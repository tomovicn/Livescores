package com.example.nikolatomovic.scores.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikolatomovic on 6/8/17.
 */

public class MatchCastResponse {
    @SerializedName("matchcast")
    private MatchCast matchCast;

    public MatchCast getMatchCast() {
        return matchCast;
    }
}
