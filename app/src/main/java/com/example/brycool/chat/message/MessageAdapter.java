package com.example.brycool.chat.message;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brycool.R;
import com.example.brycool.chat.message.chat.Chat;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    private List<MessageList> messageLists;
    private final Context context;

    public MessageAdapter(List<MessageList> messageLists, Context context) {
        this.messageLists = messageLists;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_adapter_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MyViewHolder holder, int position) {
        MessageList list2 = messageLists.get(position);
        if (!list2.getProfilepic().isEmpty()) {
            Picasso.get().load(list2.getProfilepic()).into(holder.profilePic);

        }
        holder.name.setText(list2.getName());
        holder.lastmessage.setText(list2.getLastMessage());
        if (list2.getUnseenMessages() == 0) {
            holder.unseenmessage.setVisibility(View.GONE);
            holder.lastmessage.setTextColor(Color.parseColor("#959595"));
        } else {
            holder.unseenmessage.setVisibility(View.VISIBLE);
            holder.unseenmessage.setText(list2.getUnseenMessages() + "");
            holder.lastmessage.setTextColor(context.getResources().getColor(R.color.theme_color_80));
        }
        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Chat.class);
                intent.putExtra("mobile", list2.getMobile());
                intent.putExtra("name", list2.getName());
                intent.putExtra("profile_pic", list2.getProfilepic());
                intent.putExtra("chat_key", list2.getChatKey());
                context.startActivity(intent);
            }
        });
    }

    public void updateData(List<MessageList> messageLists) {
        this.messageLists = messageLists;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return messageLists.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView profilePic;
        private TextView name, lastmessage, unseenmessage;
        private LinearLayout rootLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            profilePic = itemView.findViewById(R.id.profile_pic);
            name = itemView.findViewById(R.id.message_name);
            lastmessage = itemView.findViewById(R.id.message_text);
            unseenmessage = itemView.findViewById(R.id.unseenMessages);
            rootLayout = itemView.findViewById(R.id.rootLayout);
        }
    }
}
