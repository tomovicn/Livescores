package com.example.nikolatomovic.scores.model;

import java.util.ArrayList;

/**
 * Created by nikolatomovic on 6/6/17.
 */

public class Goals {
    private ArrayList<Goal> homeTeam;
    private ArrayList<Goal> guestTeam;
    private ArrayList<Goal> byTime;

    public Goals(ArrayList<Goal> homeTeam, ArrayList<Goal> guestTeam, ArrayList<Goal> byTime) {
        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
        this.byTime = byTime;
    }
}
