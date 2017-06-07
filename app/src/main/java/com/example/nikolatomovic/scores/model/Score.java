package com.example.nikolatomovic.scores.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikolatomovic on 6/6/17.
 */

public class Score {
    @SerializedName("home_team")
    private Integer home;
    @SerializedName("guest_team")
    private Integer guest;

    public Score(Integer home, Integer guest) {
        this.home = home;
        this.guest = guest;
    }

    public Integer getHome() {
        return home;
    }

    public Integer getGuest() {
        return guest;
    }

    @Override
    public String toString() {
        return home.toString() + " : " + guest.toString();
    }
}
