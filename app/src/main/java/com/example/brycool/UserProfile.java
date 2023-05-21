package com.example.brycool;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfile extends AppCompatActivity {
    TextInputLayout fullName , email , phoneNo , username ;
    TextView fullNameLabel  , usernameLabel;
    UserHelperClass userHelperClass ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        fullName = findViewById(R.id.full_name_profile);
        email = findViewById(R.id.email_profile);
        phoneNo = findViewById(R.id.phone_no_profile);
        username = findViewById(R.id.username_profile);


        fullNameLabel = findViewById(R.id.full_name_label);
        userHelperClass = (UserHelperClass) getIntent().getSerializableExtra("userHelper");
        usernameLabel = findViewById(R.id.username_label);
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);

        if(userHelperClass!=null){
            showAllUserData();
        }
       else if(signInAccount != null){
            fullNameLabel.setText(signInAccount.getDisplayName());
            usernameLabel.setText(signInAccount.getDisplayName());
            email.getEditText().setText(signInAccount.getEmail());
       //     phoneNo.getEditText().setText((CharSequence) signInAccount.getPhotoUrl());
            username.getEditText().setText(signInAccount.getId());
        }

    }

    private void showAllUserData() {
        Intent intent = getIntent();

        String user_username = userHelperClass.getUsername();
        String user_name = userHelperClass.getName();
        String user_phoenNo = userHelperClass.getPhoneNo();
        String user_email = userHelperClass.getEmail();
         CircleImageView profilePic = findViewById(R.id.profile_image);
        String getProfilePic =  userHelperClass.getProfilepic();
       // Log.d("value",user_username);
        fullNameLabel.setText(user_name);
        usernameLabel.setText(user_username);
        username.getEditText().setText(user_username);
        fullName.getEditText().setText(user_name);
        phoneNo.getEditText().setText(user_phoenNo);
        email.getEditText().setText(user_email);
//        if(getProfilePic == null ){
//            getProfilePic = "https://firebasestorage.googleapis.com/v0/b/bricooll.appspot.com/o/car-repair.png?alt=media&token=203daa28-f334-4d16-8976-be2100aed064";
//        }
        Picasso.get().load(getProfilePic).into(profilePic);
    }
}