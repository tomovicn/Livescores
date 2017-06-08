package com.example.nikolatomovic.scores.UI;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nikolatomovic.scores.R;
import com.example.nikolatomovic.scores.model.Comment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nikolatomovic on 6/8/17.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder>{

    private ArrayList<Comment> mComments;

    public CommentsAdapter(ArrayList<Comment> comments) { this.mComments = comments; }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.comment_description)
        TextView mDexcription;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        @Override
        public void onClick(View v) {

        }
    }

    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item_row, parent, false);
        return new ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(CommentsAdapter.ViewHolder holder, int position) {
        holder.mDexcription.setText(mComments.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }
}
