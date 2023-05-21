package com.example.brycool;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.brycool.chat.MemoryData;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private static final int RC_SIGN_IN = 2001;
    Button callSignUp, login_btn;
    ImageView image, googleImage;
    TextView logoText, sloganText;
    private FirebaseAuth mAuth;
    TextInputLayout username, password;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    UserHelperClass helperClass;


// @Override
//    protected void onStart() {
//        super.onStart();
//     GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
//     FirebaseUser user = mAuth.getCurrentUser();
////        if(user!=null){
////            Intent intent = new Intent(getApplicationContext(), DashboerdUser.class);
////            startActivity(intent);
////        }
//
//
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //This Line will hide the status bar from the screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        //Hooks
        callSignUp = (Button) findViewById(R.id.signup_screen);
        image = findViewById(R.id.logo_image);
        logoText = findViewById(R.id.logo_name);
        sloganText = findViewById(R.id.slogan_name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.login_btn);
        createRequest();
        mAuth = FirebaseAuth.getInstance();
        if (!MemoryData.getPhone(this).isEmpty()) {
            Log.d("Username_inMemorydata: ", MemoryData.getPhone(this));
            Intent intent = new Intent(getApplicationContext(), DashboerdUser.class);
            intent.putExtra("name", MemoryData.getName(this));
            intent.putExtra("email", MemoryData.getEmail(this));
            intent.putExtra("phoneNo", MemoryData.getPhone(this));
            intent.putExtra("username", MemoryData.getUsername(this));
            intent.putExtra("password", MemoryData.getEmail(this));
            // startActivity(intent);
        }


        findViewById(R.id.googleImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        findViewById(R.id.forgetPassword_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FogetPassword.class));
            }
        });

    }

    private void createRequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(Login.this, gso);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                // ...
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {


        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(), DashboerdUser.class);
                            startActivity(intent);


                        } else {
                            Toast.makeText(Login.this, "Sorry auth failed.", Toast.LENGTH_SHORT).show();


                        }


                        // ...
                    }
                });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private Boolean validatePassword() {
        String password1 = password.getEditText().getText().toString();

        if (password1.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUserName() {
        String username1 = username.getEditText().getText().toString();

        if (username1.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;

        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;

        }
    }

    public void loginUser(View view) {
        if (!validatePassword() | !validateUserName()) {
            return;
        } else {
            isUser();
        }
    }

    private void isUser() {
        String userEntredUsername = username.getEditText().getText().toString().trim();
        String userEntredPassword = password.getEditText().getText().toString().trim();
        rootNode = FirebaseDatabase.getInstance("https://bricooll-default-rtdb.firebaseio.com/");
        reference = rootNode.getReference().child("users");
        Query checkUser = reference.orderByChild("username").equalTo(userEntredUsername);
        Log.d("Query", String.valueOf(checkUser));
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("snapshot", snapshot.toString());
                if (snapshot.exists()) {
                    username.setError(null);
                    username.setErrorEnabled(false);
                    String passwordfromDb = snapshot.child(userEntredUsername).child("password").getValue(String.class);
                    Log.d("password", passwordfromDb);
                    if (passwordfromDb.equals(userEntredPassword)) {
                        String namefromDb = snapshot.child(userEntredUsername).child("name").getValue(String.class);
                        String emailfromDb = snapshot.child(userEntredUsername).child("email").getValue(String.class);
                        String phoneNofromDb = snapshot.child(userEntredUsername).child("phoneNo").getValue(String.class);
                        String usernamefromDb = snapshot.child(userEntredUsername).child("username").getValue(String.class);
                        String profilepicformDb = snapshot.child(usernamefromDb).child("profile_pic").getValue(String.class);
                        helperClass = new UserHelperClass(namefromDb, userEntredUsername, emailfromDb, phoneNofromDb, passwordfromDb, profilepicformDb);
                        Intent intent = new Intent(getApplicationContext(), DashboerdUser.class);
                        MemoryData.savePhone(phoneNofromDb, Login.this);

                        MemoryData.savePassword(passwordfromDb, Login.this);
                        MemoryData.saveEmail(emailfromDb, Login.this);
                        Log.d("whatsaved :", usernamefromDb);
                        MemoryData.saveUsername(userEntredUsername, Login.this);
                        MemoryData.saveName(usernamefromDb, Login.this);

                        intent.putExtra("name", namefromDb);
                        intent.putExtra("email", emailfromDb);
                        intent.putExtra("phoneNo", phoneNofromDb);
                        intent.putExtra("username", usernamefromDb);
                        intent.putExtra("password", passwordfromDb);
                        intent.putExtra("userHelper", helperClass);

                        startActivity(intent);


                    } else {

                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                } else {

                    username.setError("No Such User exist");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void callSignUpScreen(View view) {
        Intent intent = new Intent(Login.this, SignUp.class);
        Pair[] pairs = new Pair[7];
        pairs[0] = new Pair<View, String>(image, "logo_image");
        pairs[1] = new Pair<View, String>(logoText, "logo_text");
        pairs[2] = new Pair<View, String>(sloganText, "logo_desc");
        pairs[3] = new Pair<View, String>(username, "username_tran");
        pairs[4] = new Pair<View, String>(password, "password_tran");
        pairs[5] = new Pair<View, String>(login_btn, "button_tran");
        pairs[6] = new Pair<View, String>(callSignUp, "login_signup_tran");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
        startActivity(intent, options.toBundle());

    }
}