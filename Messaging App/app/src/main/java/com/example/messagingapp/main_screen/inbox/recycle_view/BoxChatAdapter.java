package com.example.messagingapp.main_screen.inbox.recycle_view;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messagingapp.R;

import java.util.List;

public class BoxChatAdapter extends  RecyclerView.Adapter<BoxChatAdapter.BoxChatViewHolder> {
    Context context;
    List<BoxChat> boxChatList;

    public BoxChatAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<BoxChat> boxChatList){
        this.boxChatList = boxChatList;
    }

    @NonNull
    @Override
    public BoxChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.boxchat_item,parent,false);
        return new BoxChatAdapter.BoxChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoxChatViewHolder holder, int position) {
        BoxChat boxChat = boxChatList.get(position);
        if(boxChat== null){
            return;
        }
        holder.imvAvatar.setImageURI(Uri.parse(boxChat.getImageUrl()));
        holder.carStatus.setCardBackgroundColor((boxChat.getStatus().equals("true")? Color.parseColor("#4CAF50"):Color.parseColor("#F2C94C")));
        holder.tvNickName.setText(boxChat.getNickName());
        holder.tvLastMessage.setText(boxChat.getLastMessage());
        holder.tvLastTime.setText(boxChat.getLastTime());
        holder.carStatus.setVisibility(boxChat.getNumberUnSeenMessage()==0?View.GONE:View.VISIBLE);
        holder.tvNumberUnSeen.setText(boxChat.getNumberUnSeenMessage() );
    }

    @Override
    public int getItemCount() {
        if(boxChatList != null){
            return boxChatList.size();
        }
        return 0;
    }

    public static class BoxChatViewHolder extends RecyclerView.ViewHolder{

        ImageView imvAvatar;
        CardView carStatus;
        TextView tvNickName;
        TextView tvLastMessage;
        TextView tvLastTime;
        TextView tvNumberUnSeen;
        CardView getCarStatusSeen;

        public BoxChatViewHolder(@NonNull View itemView) {
            super(itemView);

             imvAvatar          = itemView.findViewById(R.id.imv_avatar);
             carStatus          = itemView.findViewById(R.id.car_status);
             tvNickName         = itemView.findViewById(R.id.tv_nickName);
             tvLastMessage      = itemView.findViewById(R.id.tv_last_mesage);
             tvLastTime         = itemView.findViewById(R.id.tv_last_time);
             tvNumberUnSeen     = itemView.findViewById(R.id.tv_number_unseen);
             getCarStatusSeen   = itemView.findViewById(R.id.car_status_seen);
        }
    }
}
