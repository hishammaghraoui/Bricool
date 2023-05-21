package com.example.brycool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class verifyPhoneNo extends AppCompatActivity {
    Button verify_btn ;
    EditText phoneNoEnteredbytheUser ;
    ProgressBar progressBar ;
    String verificationCodeBySystem ;
    Button callSignUp, login_btn;
    ImageView image;
    TextView logoText, sloganText;
    TextInputLayout username, password;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone_no);

        verify_btn =  findViewById(R.id.verify_btn);
        phoneNoEnteredbytheUser =  findViewById(R.id.verification_code_entered_by_user);
        progressBar =  findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
        String phoneNo = getIntent().getStringExtra("phoneNo");

        sendVereficationCodetoUser(phoneNo);
        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = phoneNoEnteredbytheUser.getText().toString();
                if(code.isEmpty() || code.length() < 6 ){
                    phoneNoEnteredbytheUser.setError("Wrong OTP.......");
                    phoneNoEnteredbytheUser.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }
        });
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    verificationCodeBySystem = s;
                }

                @Override
                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                    String code = phoneAuthCredential.getSmsCode();
                    if (code != null) {
                        progressBar.setVisibility(View.VISIBLE);
                        verifyCode(code);
                    }
                }

                @Override
                public void onVerificationFailed(FirebaseException e) {
                    Toast.makeText(verifyPhoneNo.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            };
    private void sendVereficationCodetoUser(String phoneNo) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+212" + phoneNo)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
//        PhoneAuthProvider.verifyPhoneNumber(
//                PhoneAuthOptions
//                        .newBuilder(FirebaseAuth.getInstance())
//                        .setActivity(this)
//                        .setPhoneNumber("+212" + phoneNo)
//                        .setTimeout(60L, TimeUnit.SECONDS)
//                        .setCallbacks(mCallbacks)
//                        .setForceResendingToken(token)
//                        .build());
        PhoneAuthProvider.verifyPhoneNumber(options);
//        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                "+212" + phoneNo ,
//                60,
//                TimeUnit.SECONDS,
//                (Activity) TaskExecutors.MAIN_THREAD,
//                mCallbacks
//        );
    }
    private void verifyCode(String codeByUser) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem, codeByUser);
        signInTheUserByCredentials(credential);

    }

    private void signInTheUserByCredentials(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(verifyPhoneNo.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Toast.makeText(verifyPhoneNo.this, "Your Account has been created successfully!", Toast.LENGTH_SHORT).show();

                            //Perform Your required action here to either let the user sign In or do something required
                            Intent intent = new Intent(getApplicationContext(), DashboerdUser.class);
                             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                             isUser();
                             startActivity(intent);

                        } else {
                            Toast.makeText(verifyPhoneNo.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void isUser() {
        Intent intent = getIntent();
        String userEntredUsername  =intent.getStringExtra("username");
        rootNode = FirebaseDatabase.getInstance("https://bricooll-default-rtdb.firebaseio.com/");
        reference = rootNode.getReference().child("users");
        Query checkUser = reference.orderByChild("username").equalTo(userEntredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String username = snapshot.child(userEntredUsername).child("username").getValue(String.class);
                String namefromDb= snapshot.child(userEntredUsername).child("name").getValue(String.class);
                String emailfromDb= snapshot.child(userEntredUsername).child("email").getValue(String.class);
                String phoneNofromDb= snapshot.child(userEntredUsername).child("phoneNo").getValue(String.class);
                String usernamefromDb= snapshot.child(userEntredUsername).child("username").getValue(String.class);
                Intent intent =  new Intent(getApplicationContext(),DashboerdUser.class);
                intent.putExtra("name",namefromDb);
                intent.putExtra("email",emailfromDb);
                intent.putExtra("phoneNo",phoneNofromDb);
                intent.putExtra("username",usernamefromDb);

                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}