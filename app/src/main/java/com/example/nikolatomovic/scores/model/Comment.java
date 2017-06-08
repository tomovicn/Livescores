package com.example.nikolatomovic.scores.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikolatomovic on 6/8/17.
 */

public class Comment {
    @SerializedName("event_id")
    private Integer eventId;
    @SerializedName("event_type_id")
    private Integer eventTypeId;
    private String time;
    private Integer type;
    private String text;
    private String funfact;

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }
}
