package com.example.nikolatomovic.scores.model;

import java.util.Map;

/**
 * Created by nikolatomovic on 6/8/17.
 */

public class MatchCast extends Match {
    private Map<String, Comment> commentMap;

    public MatchCast(Match match) {
        super(match);
    }

    public Map<String, Comment> getCommentMap() {
        return commentMap;
    }
}
