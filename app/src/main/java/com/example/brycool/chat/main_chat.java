package com.example.brycool.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.brycool.DashboerdUser;
import com.example.brycool.MainActivity;
import com.example.brycool.R;
import com.example.brycool.UserHelperClass;
import com.example.brycool.chat.message.MessageAdapter;
import com.example.brycool.chat.message.MessageList;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class main_chat extends AppCompatActivity {
    String userEntredUsername ="Hicham";
    private  final List<MessageList> messageLists = new ArrayList<>();
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseDatabase rootNode;
    private int unseenMessage = 0 ;
    private  boolean dataSet =  false ;
    private String lastmessage = "";
    private  String chatKey = "";
    private DatabaseReference reference;
    DatabaseReference reference1 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bricooll-default-rtdb.firebaseio.com/");
    String namefromDb, emailfromDb, phoneNofromDb, usernamefromDb;
    UserHelperClass userHelperClass ;
    Intent intentglobal ;
    private RecyclerView messagesRecyclerView;
    private  MessageAdapter messageAdapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);
        //userEntredUsername = getIntent().getStringExtra("username");
        userHelperClass =   (UserHelperClass) getIntent().getSerializableExtra("userHelper");
        Log.d("Username : ", MemoryData.getUsername(this));
        usernamefromDb = "hicham" ;
        final CircleImageView userProfilePic = findViewById(R.id.userProfilePic);
        messagesRecyclerView = findViewById(R.id.messagesRecycler);
        messagesRecyclerView.setHasFixedSize(true);
        messagesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageAdapter = new MessageAdapter(messageLists , main_chat.this);
        messagesRecyclerView.setAdapter(messageAdapter);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("loading.....");
        progressDialog.show();
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("Username : ", userEntredUsername);
                final String profilePicUrl = snapshot.child("users").child(userEntredUsername).child("profile_pic").getValue(String.class);
                if (!profilePicUrl.isEmpty()) {
                    Picasso.get().load(profilePicUrl).into(userProfilePic);


                }
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();

            }

        });
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                messageLists.clear();
                unseenMessage= 0 ;
                lastmessage="";
                chatKey = "";
                for (DataSnapshot dataSnapshot : snapshot.child("users").getChildren()){
                    final String getUsername  = dataSnapshot.getKey();
                    dataSet = false ;
                    if(!getUsername.equals(usernamefromDb)){
                        final String namefromDb = dataSnapshot.child("name").getValue(String.class);
                        final String getProfilePic = dataSnapshot.child("profile_pic").getValue(String.class);


                        reference1.child("chat").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                int getChatCount = (int) snapshot.getChildrenCount();
                                if(getChatCount>0){
                                    for (DataSnapshot dataSnapshot1 : snapshot.getChildren()){

                                        final String getKey = dataSnapshot1.getKey();
                                        chatKey = getKey ;
                                        if(dataSnapshot1.hasChild("user_1") && dataSnapshot1.hasChild("user_2") && dataSnapshot1.hasChild("message")){
                                            final String getUserOne = dataSnapshot1.child("user_1").getValue(String.class);
                                            final String getUserTwo = dataSnapshot1.child("user_2").getValue(String.class);
                                            if((getUserOne.equals(getUsername) &&  getUserTwo.equals(userEntredUsername)) || (getUserOne.equals(userEntredUsername) &&  getUserTwo.equals(getUsername)  )){
                                                for(DataSnapshot chatdataSnapshot : dataSnapshot1.child("messages").getChildren()){
                                                    final long getMessagekey =   Long.parseLong(chatdataSnapshot.getKey());
                                                    final long getlastSeenMessage  = Long.parseLong(MemoryData.getLastMessage(main_chat.this,getKey));
                                                    lastmessage = chatdataSnapshot.child("msg").getValue(String.class);
                                                    if (getMessagekey>getlastSeenMessage){
                                                        unseenMessage ++ ;

                                                    }
                                                }
                                            }
                                        }

                                    }
                                }
                                if(!dataSet){
                                    dataSet=true;
                                    MessageList messageList =  new MessageList(namefromDb,getUsername, lastmessage,getProfilePic,unseenMessage, chatKey);
                                    messageLists.add(messageList);
                                    messageAdapter.updateData(messageLists);
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}