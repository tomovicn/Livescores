package com.example.nikolatomovic.scores.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikolatomovic on 6/6/17.
 */

public class Match {
    @SerializedName("match_id")
    private String id;
    @SerializedName("category_id")
    private String categoryId;
    @SerializedName("tournament_id")
    private String tournamentId;
    @SerializedName("home_team")
    private Team homeTeam;
    @SerializedName("guest_team")
    private Team guestTeam;
    @SerializedName("match_current_time")
    private String matchCurrentTime;
    @SerializedName("tournament_name")
    private String tournamentName;
    @SerializedName("category_name")
    private String categoryName;
    //@SerializedName("winner")
    //private Integer winner;
    @SerializedName("match_time")
    private String matchTime;
    @SerializedName("status")
    private String status;
    @SerializedName("status_code")
    private Integer statusCode;
    @SerializedName("score")
    private MatchScore score;
    @SerializedName("goals")
    private Goals goals;

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getGuestTeam() {
        return guestTeam;
    }

    public String getMatchCurrentTime() {
        return matchCurrentTime;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getTournamentName() { return tournamentName; }

    public String getStatus() {
        return status;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public MatchScore getScore() {
        return score;
    }
}
