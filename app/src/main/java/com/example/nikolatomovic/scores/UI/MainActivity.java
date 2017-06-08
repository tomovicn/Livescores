package com.example.nikolatomovic.scores.UI;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;

import com.example.nikolatomovic.scores.R;
import com.example.nikolatomovic.scores.model.CardsGroup;
import com.example.nikolatomovic.scores.model.Goals;
import com.example.nikolatomovic.scores.model.Match;
import com.example.nikolatomovic.scores.model.MatchScore;
import com.example.nikolatomovic.scores.model.Score;
import com.example.nikolatomovic.scores.model.ScoresResponse;
import com.example.nikolatomovic.scores.model.parsers.CardsDeserializer;
import com.example.nikolatomovic.scores.model.parsers.GoalsDeserializer;
import com.example.nikolatomovic.scores.model.parsers.MatchScoreDeserializer;
import com.example.nikolatomovic.scores.model.parsers.ScoreDeserializer;
import com.example.nikolatomovic.scores.network.interfaces.MatchsServiceInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private ArrayList<Match> matchs;
    private ArrayList<Match> liveMatchs;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    private LinearLayoutManager mLinearLayoutManager;
    private MatchsAdapter mAdapter;
    private DatePickerDialog mDatePickerDialog;

    private Retrofit retrofit;
    private int year;
    private int month;
    private int day;
    static final int DATE_DIALOG_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_all_scores:
                        mAdapter.updateData(matchs);
                        break;
                    case R.id.menu_livescores:
                        loadLivescores();
                        break;
                    case R.id.menu_finished:
                        ArrayList<Match> finished = new ArrayList<Match>();
                        for (Match match : matchs) {
                            if (match.getStatusCode() == 100) {
                                finished.add(match);
                            }
                        }
                        mAdapter.updateData(finished);
                        break;
                }
                return true;
            }
        });

        setBusinessLogic();
        loadData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_date_picker, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_date_picker:
                mDatePickerDialog = new DatePickerDialog(this, this, year, month, day);
                mDatePickerDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setBusinessLogic() {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

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
        Call<ScoresResponse> call = request.getScores(fromTime(), untilTime());
        call.enqueue(new Callback<ScoresResponse>() {
            @Override
            public void onResponse(Call<ScoresResponse> call, Response<ScoresResponse> response) {
                ScoresResponse scoresResponse = response.body();
                matchs = new ArrayList<>(Arrays.asList(scoresResponse.getMatchs()));
                mAdapter = new MatchsAdapter(matchs);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<ScoresResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

    private void loadLivescores() {
        MatchsServiceInterface request = retrofit.create(MatchsServiceInterface.class);
        Call<ScoresResponse> call = request.getLiveScores();
        call.enqueue(new Callback<ScoresResponse>() {
            @Override
            public void onResponse(Call<ScoresResponse> call, Response<ScoresResponse> response) {
                ScoresResponse scoresResponse = response.body();
                liveMatchs = new ArrayList<Match>(Arrays.asList(scoresResponse.getMatchs()));
                mAdapter.updateData(liveMatchs);
            }

            @Override
            public void onFailure(Call<ScoresResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

    private String fromTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Long tsLong = cal.getTimeInMillis()/1000;
        return tsLong.toString();
    }

    private String untilTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Long tsLong = cal.getTimeInMillis()/1000;
        return tsLong.toString();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        this.day = dayOfMonth;
        loadData();
    }
}
