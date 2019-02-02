package com.example.sviye.asahiproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class PriceDiscount extends AppCompatActivity {

    private static final String TAG = "PriceDiscount";

    public static final String DIAMETER_KEY ="diameter";
    public static final String CORE_KEY="core";
    public static final String CONSTRUCTION_KEY="construction";

    String diameterString, constString, coreString;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_discount);

        Intent intent = getIntent();
        diameterString = intent.getStringExtra(DIAMETER_KEY);
        constString = intent.getStringExtra(CONSTRUCTION_KEY);
        coreString = intent.getStringExtra(CORE_KEY);

        Toast.makeText(this,diameterString+" "+coreString+" "+constString,Toast.LENGTH_LONG).show();

    }
}
