package com.cskku.werockstar.retrofitdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cskku.werockstar.retrofitdemo.R;
import com.cskku.werockstar.retrofitdemo.model.Github;

import java.util.List;

/**
 * Created by Kotchaphan on 24/10/2558.
 */
public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.GitViewHolder> {

    List<Github> githubList;

    public GithubAdapter(List<Github> list) {
        this.githubList = list;
    }

    @Override
    public GitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new GitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GitViewHolder holder, int position) {
        Github github = githubList.get(position);
        Glide.with(holder.mImageUser.getContext()).load(github.getAvatarUrl()).into(holder.mImageUser);
        holder.mTextLocation.setText(github.getLocation());
        holder.mTextUsername.setText(github.getName());
    }

    @Override
    public int getItemCount() {
        return githubList.size();
    }

    public class GitViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageUser;
        TextView mTextUsername;
        TextView mTextLocation;

        public GitViewHolder(View itemView) {
            super(itemView);

            mImageUser = (ImageView) itemView.findViewById(R.id.imgUser);
            mTextUsername = (TextView) itemView.findViewById(R.id.txtUser);
            mTextLocation = (TextView) itemView.findViewById(R.id.txtLocation);
        }
    }
}
