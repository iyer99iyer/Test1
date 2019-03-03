package com.example.sviye.asahiproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static int SPLASH_TIME_OUT = 1000;

    private FirebaseAuth mAuth;


    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        logo = findViewById(R.id.imageView);

        Log.d(TAG, "onCreate: ");

    }

    @Override
    public void onStart() {

        super.onStart();
        splashScreen();


    }

    private void loginCheck() {

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            startLogin();
        } else {
            selectActivity();
        }

    }

    //goes to SelectActivity.class
    private void selectActivity() {
        Intent loginIntent = new Intent(MainActivity.this, SelectActivity.class);
        startActivity(loginIntent);
        finish();
    }

    //goes to LoginActivity.class
    private void startLogin() {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    public void splashScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // Check if user is signed in (non-null) and update UI accordingly.
                loginCheck();

            }
        }, SPLASH_TIME_OUT);

    }
}



