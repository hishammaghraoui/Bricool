package com.example.brycool;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    //Variables
    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn, regToLoginBtn;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //Hooks to all xml elements in activity_sign_up.xml
        regName = findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPhoneNo = findViewById(R.id.reg_phoneNo);
        regPassword = findViewById(R.id.reg_password);
        regBtn = findViewById(R.id.reg_btn);
        regToLoginBtn = findViewById(R.id.reg_login_btn);
        //Save data in FireBase on button click
        regToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

    }//onCreate Method End



    private boolean validateName() {
        String name = regName.getEditText().getText().toString();
        if (name.isEmpty()) {
            regName.setError("Field cannot be empty");
            return false;

        } else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;

        }
    }
    private boolean validateUserName() {
        String username = regUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (username.isEmpty()) {
            regUsername.setError("Field cannot be empty");
            return false;

        } else if(username.length()>=15) {
            regUsername.setError("Username is too long");
            return false;

        }
        else if (!username.matches(noWhiteSpace)){
            regUsername.setError("White spaces are not allowed");
            return false ;
        }
        else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;

        }
    }
    private boolean validateEmail() {
        String email = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        } else if (!email.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        } else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatePhoneNo() {
        String phoneNo = regPhoneNo.getEditText().getText().toString();

        if (phoneNo.isEmpty()) {
            regPhoneNo.setError("Field cannot be empty");
            return false;
        } else {
            regPhoneNo.setError(null);
            regPhoneNo.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword (){
        String password = regPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (password.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        } else if (!password.matches(passwordVal)) {
            regPassword.setError("Invalid Password address");
            return false;
        } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }
    public void registerUser(View view) {
        //Get all the values
        rootNode = FirebaseDatabase.getInstance("https://bricooll-default-rtdb.firebaseio.com/");
        reference = rootNode.getReference().child("users");
        if(!validateName() | !validateEmail() | !validateUserName() | !validatePassword() |!validatePhoneNo() ){
            return;
        }
        String name = regName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String phoneNo = regPhoneNo.getEditText().getText().toString();
        Log.d("phone:", phoneNo);
        String password = regPassword.getEditText().getText().toString();
      UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password,"");
       reference.child(username).setValue(helperClass);
      //  reference.setValue(helperClass);
        String phoneNo1 = regPhoneNo.getEditText().getText().toString();

        //Call the next activity and pass phone no with it
        Intent intent = new Intent(getApplicationContext(), verifyPhoneNo.class);
        intent.putExtra("phoneNo", phoneNo1);
        intent.putExtra("username",username);
        intent.putExtra("userHelper", helperClass);

        startActivity(intent);

    }

}