package com.example.nikolatomovic.scores.model;

/**
 * Created by nikolatomovic on 6/6/17.
 */

public class CardsGroup {
    private Cards homeTeam;
    private Cards guestTeam;

    public CardsGroup(Cards homeTeam, Cards guestTeam) {
        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
    }
}
