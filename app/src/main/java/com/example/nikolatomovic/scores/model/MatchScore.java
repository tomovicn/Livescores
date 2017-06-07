package com.example.nikolatomovic.scores.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikolatomovic on 6/6/17.
 */

public class MatchScore {
    @SerializedName("current")
    private Score current;
    @SerializedName("half_time")
    private Score halfTime;
    @SerializedName("normal_time")
    private Score normalTime;

    public MatchScore(Score current, Score halfTime, Score normalTime) {
        this.current = current;
        this.halfTime = halfTime;
        this.normalTime = normalTime;
    }

    public Score getCurrent() {
        return current;
    }

    public Score getHalfTime() {
        return halfTime;
    }

    public Score getNormalTime() {
        return normalTime;
    }
}

