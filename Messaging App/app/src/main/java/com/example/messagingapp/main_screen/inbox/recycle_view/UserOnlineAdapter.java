package com.example.messagingapp.main_screen.inbox.recycle_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messagingapp.R;

import java.util.List;

public class UserOnlineAdapter extends RecyclerView.Adapter<UserOnlineAdapter.UserOnlineViewHolder>{

    Context context;
    List<UserOnline> userOnlineList;

    public UserOnlineAdapter(Context context) {
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<UserOnline> userOnlineList){
        this.userOnlineList= userOnlineList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserOnlineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_online_item,parent,false);
        return new UserOnlineAdapter.UserOnlineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserOnlineViewHolder holder, int position) {
        UserOnline userOnline = userOnlineList.get(position);
        if(userOnline==null){
            return;
        }
        holder.imageView.setImageURI(Uri.parse(userOnline.imageUri));
    }

    @Override
    public int getItemCount() {
        if(userOnlineList!= null){
            return userOnlineList.size();
        }
        return 0;
    }

    public static class UserOnlineViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public UserOnlineViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imv_user);
        }
    }
}
