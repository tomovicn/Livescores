package com.example.nikolatomovic.scores.UI;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nikolatomovic.scores.R;
import com.example.nikolatomovic.scores.model.Match;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nikolatomovic on 6/6/17.
 */

public class MatchsAdapter extends RecyclerView.Adapter<MatchsAdapter.ViewHolder> {

    private ArrayList<Match> mMatchs;

    public MatchsAdapter(ArrayList<Match> matchs) {
        mMatchs = new ArrayList<>();
        for (Match match : matchs) {
            mMatchs.add(new Match(match));
        }
    }

    public void updateData(ArrayList<Match> matchs) {
        mMatchs.clear();
        for (Match match : matchs) {
            mMatchs.add(new Match(match));
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.scores_favoritesButton)
        ImageButton mFavorites;
        @BindView(R.id.scores_league)
        TextView mLeague;
        @BindView(R.id.scores_home_team)
        TextView mHomeTeam;
        @BindView(R.id.scores_result)
        TextView mResult;
        @BindView(R.id.scores_guest_team)
        TextView mGuestTeam;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }

    @Override
    public MatchsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.scores_item_row, parent, false);
        return new ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(MatchsAdapter.ViewHolder holder, int position) {
        Match match = mMatchs.get(position);
        holder.mLeague.setText(match.getTournamentName());
        holder.mHomeTeam.setText(match.getHomeTeam().getName());
        holder.mGuestTeam.setText(match.getGuestTeam().getName());
        if (match.getScore() != null)
            holder.mResult.setText(match.getScore().getCurrent().toString());
        else
            holder.mResult.setText("- : -");
    }

    @Override
    public int getItemCount() {
        return mMatchs.size();
    }
}
