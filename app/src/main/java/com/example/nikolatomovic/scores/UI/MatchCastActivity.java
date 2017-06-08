package com.example.nikolatomovic.scores.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.nikolatomovic.scores.R;
import com.example.nikolatomovic.scores.model.CardsGroup;
import com.example.nikolatomovic.scores.model.Comment;
import com.example.nikolatomovic.scores.model.Goals;
import com.example.nikolatomovic.scores.model.MatchCast;
import com.example.nikolatomovic.scores.model.MatchCastResponse;
import com.example.nikolatomovic.scores.model.MatchScore;
import com.example.nikolatomovic.scores.model.Score;
import com.example.nikolatomovic.scores.model.parsers.CardsDeserializer;
import com.example.nikolatomovic.scores.model.parsers.GoalsDeserializer;
import com.example.nikolatomovic.scores.model.parsers.MatchScoreDeserializer;
import com.example.nikolatomovic.scores.model.parsers.ScoreDeserializer;
import com.example.nikolatomovic.scores.network.interfaces.MatchsServiceInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MatchCastActivity extends AppCompatActivity {

    @BindView(R.id.matchcast_home_team)
    TextView mHomeTeam;
    @BindView(R.id.matchcast_guest_team)
    TextView mGuestTeam;
    @BindView(R.id.matchcast_result)
    TextView mResult;
    @BindView(R.id.matchcastRecyclerView)
    RecyclerView mRecyclerView;

    private String mMatchId;
    private MatchCast mMatchCast;
    private Retrofit retrofit;
    private CommentsAdapter mAdapter;

    private static final String MATCH_ID_KEY = "MATCH_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_cast);
        ButterKnife.bind(this);

        mMatchId = (String) getIntent().getSerializableExtra(MATCH_ID_KEY);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        setBusinessLogic();
        loadData();
    }

    private void setBusinessLogic() {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(MatchScore.class, new MatchScoreDeserializer())
                .registerTypeAdapter(Goals.class, new GoalsDeserializer())
                .registerTypeAdapter(CardsGroup.class, new CardsDeserializer())
                .registerTypeAdapter(Score.class, new ScoreDeserializer())
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://ws.mozzartsport.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private void loadData() {
        MatchsServiceInterface request = retrofit.create(MatchsServiceInterface.class);
        Call<MatchCastResponse> call = request.getMatchCast(mMatchId);
        call.enqueue(new Callback<MatchCastResponse>() {
            @Override
            public void onResponse(Call<MatchCastResponse> call, Response<MatchCastResponse> response) {
                mMatchCast = response.body().getMatchCast();
                mHomeTeam.setText(mMatchCast.getHomeTeam().getName());
                mGuestTeam.setText(mMatchCast.getGuestTeam().getName());
                mResult.setText(mMatchCast.getScore().getCurrent().toString());
                if (mMatchCast.getCommentMap() != null) {
                    mAdapter = new CommentsAdapter((ArrayList<Comment>) mMatchCast.getCommentMap().values());
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<MatchCastResponse> call, Throwable t) {
                Log.d("Error:", t.getLocalizedMessage());
            }
        });
    }

}
