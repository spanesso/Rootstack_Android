package com.rootstack.businessnearapp.view.viewAdapters;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rootstack.businessnearapp.R;
import com.rootstack.businessnearapp.databinding.BusinessListItemBinding;
import com.rootstack.businessnearapp.model.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UsersRecyclerViewAdapter extends RecyclerView.Adapter<UsersRecyclerViewAdapter.ViewHolder> {

    public List<Result> list = new ArrayList<>();
    public List<Result> mFilteredList = new ArrayList<>();
    public Context mContext;

    public UsersRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    public void setUserList(List<Result> list) {
        this.list = list;
        this.mFilteredList = list;
    }

    @Override
    public UsersRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View statusContainer = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_list_item, parent, false);
        return new UsersRecyclerViewAdapter.ViewHolder(statusContainer);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result user = mFilteredList.get(position);
        holder.bindUser(user);
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private BusinessListItemBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bindUser(Result user) {
            binding.setUser(user);
            Picasso.with(mContext)
                    .load(user.getPicture().getMedium())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(binding.imgLogo);

        }
    }
}

