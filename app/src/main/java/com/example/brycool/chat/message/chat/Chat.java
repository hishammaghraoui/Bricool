package com.example.brycool.chat.message.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brycool.R;
import com.example.brycool.chat.MemoryData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;


public class Chat extends AppCompatActivity {
    DatabaseReference reference1 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bricooll-default-rtdb.firebaseio.com/");
    private String chatKey;
    private  List<ChatList> chatLists = new ArrayList<>();
    String getUsernamedata = "";
    private RecyclerView chattingRecyclview;
    private  ChatAdapter chatAdapter;
    private  boolean loadingFirstTime = true ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        final ImageView back_btn = findViewById(R.id.back_btn);
        final TextView nameTV = findViewById(R.id.name_chat);
        final EditText messgaeEditText = findViewById(R.id.messageEditText);
        final CircleImageView profilePic = findViewById(R.id.profilePic);
        final ImageView sendBtn = findViewById(R.id.sendBtn);


        chattingRecyclview = findViewById(R.id.chattingRecycleview);
        String getname = getIntent().getStringExtra("name");
        final String getProfilePic = getIntent().getStringExtra("profile_pic");
        chatKey = getIntent().getStringExtra("chat_key");
        final String getUsername = getIntent().getStringExtra("name");

        getUsernamedata = MemoryData.getUsername(Chat.this);
        Log.d("curentUserName", getUsernamedata);
        nameTV.setText(getname);
        Picasso.get().load(getProfilePic).into(profilePic);

        chattingRecyclview.setHasFixedSize(true);
        chattingRecyclview.setLayoutManager(new LinearLayoutManager(Chat.this));
        chatAdapter= new ChatAdapter(chatLists ,Chat.this);
        chattingRecyclview.setAdapter(chatAdapter);

        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (chatKey.isEmpty()) {
                    chatKey = "1";
                    if (snapshot.hasChild("chat")) {
                        chatKey = String.valueOf(snapshot.child("chat").getChildrenCount() + 1);
                    }
                }
                if (snapshot.hasChild("chat")){
                    if(snapshot.child("chat").child(chatKey).hasChild("messages")){

                        for (DataSnapshot messagesSnapshot :snapshot.child("chat").child(chatKey).child("messages").getChildren())
                        {
                            if(messagesSnapshot.hasChild("msg") && messagesSnapshot.hasChild("Username")) {
                                final String messageTimestamps = messagesSnapshot.getKey();
                                final String getUsername1 = messagesSnapshot.child("Username").getValue(String.class);
                                final String getMsg = messagesSnapshot.child("msg").getValue(String.class);

                                Timestamp timestamp = new Timestamp(Long.parseLong(messageTimestamps));
                                Date date =  new Date(timestamp.getTime());
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy ", Locale.getDefault());
                                SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(" hh:mm aa", Locale.getDefault());
                                ChatList chatlist  = new ChatList(getUsername1, getname , getMsg, simpleDateFormat.format(date), simpleTimeFormat.format(date));
                                chatLists.add(chatlist);
                                if( loadingFirstTime || Long.parseLong(messageTimestamps) > Long.parseLong(MemoryData.getLastMessage(Chat.this,chatKey))){
                                    loadingFirstTime = false ;
                                    MemoryData.saveLastMessageTS(messageTimestamps, chatKey, Chat.this);

                                    chatAdapter.updateChatList(chatLists);
                                    chattingRecyclview.scrollToPosition(chatLists.size() - 1 );


                                }
                            }

                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String getTxtMessage = messgaeEditText.getText().toString();
                final String currentTimestamp = String.valueOf(System.currentTimeMillis()).substring(0, 10);

                reference1.child("chat").child(chatKey).child("user_1").setValue(getUsername);
                reference1.child("chat").child(chatKey).child("user_2").setValue(getUsernamedata);
                reference1.child("chat").child(chatKey).child("messages").child(currentTimestamp).child("msg").setValue(getTxtMessage);
                reference1.child("chat").child(chatKey).child("messages").child(currentTimestamp).child("Username").setValue(getUsername);


                messgaeEditText.setText("");

            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}