package com.example.nikolatomovic.scores.network.interfaces;

import com.example.nikolatomovic.scores.model.ScoresResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by nikolatomovic on 6/6/17.
 */

public interface MatchsServiceInterface {
    @POST("index.php/livescores.json?sport_id=1")
    Call<ScoresResponse> getScores(@Query("from_time") String fromTime, @Query("until_time") String untilTime);
}
