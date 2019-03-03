package com.example.sviye.asahiproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private FirebaseAuth mAuth;


    TextInputEditText passwordEditText, emailEditText;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();


        passwordEditText = findViewById(R.id.passwordEditText);
        emailEditText = findViewById(R.id.emailEditText);
        progressBar = findViewById(R.id.progressBar);

    }


    public void loginUpdate(View view){

        progressBar.setVisibility(View.VISIBLE);

        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty()){
            emailEditText.setError("Email Required!");
            emailEditText.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (password.isEmpty()){
            passwordEditText.setError("Password Required");
            passwordEditText.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Please enter valid email Address!");
            emailEditText.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (password.length()<6){
            passwordEditText.setError("Minimum password length is 6");
            passwordEditText.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }


        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    Intent intent = new Intent(LoginActivity.this,SelectActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this,"authentication failed", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
