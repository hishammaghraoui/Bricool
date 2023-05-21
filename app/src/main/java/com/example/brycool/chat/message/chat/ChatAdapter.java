package com.example.brycool.chat.message.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brycool.R;
import com.example.brycool.chat.MemoryData;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    private List<ChatList> chatlists;
    private final Context context;

    private String Username;

    public ChatAdapter(List<ChatList> chatlist, Context context) {
        this.chatlists = chatlist;
        this.context = context;
        this.Username = MemoryData.getName(context);
    }

    @NonNull
    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_adapter_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.MyViewHolder holder, int position) {
        ChatList list2 = chatlists.get(position);

        if (list2.getUsername() != null && list2.getUsername().equals(Username)) {
            holder.Mylayout.setVisibility(View.VISIBLE);
            holder.oppoLayout.setVisibility(View.GONE);

            holder.myMessage.setText(list2.getMessage());
            holder.MyTime.setText(list2.getTime()+" "+list2.getDate());
        } else {
            holder.Mylayout.setVisibility(View.GONE);
            holder.oppoLayout.setVisibility(View.VISIBLE);
            holder.oppoMessage.setText(list2.getMessage());
            holder.oppoTime.setText(list2.getTime()+" "+list2.getDate());
        }
    }

    @Override
    public int getItemCount() {
        return chatlists.size();
    }
    public  void updateChatList (List<ChatList> chatlists){
 this.chatlists = chatlists;
    }
    static class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout oppoLayout, Mylayout;
        private TextView oppoMessage, myMessage;
        private TextView oppoTime, MyTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            oppoLayout = itemView.findViewById(R.id.oppoLayout);
            Mylayout = itemView.findViewById(R.id.myLayout);
            oppoMessage = itemView.findViewById(R.id.oppoMessage);
            myMessage = itemView.findViewById(R.id.myMessage);
            oppoTime = itemView.findViewById(R.id.oppomsgtime);
            MyTime = itemView.findViewById(R.id.mymsgtime);
        }
    }
}
