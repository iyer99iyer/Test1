package com.example.sviye.asahiproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {



    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference doc1 = db.document("Price List/18X7");
    DocumentReference doc2 = db.document("Price List/6X19(12\\6\\1)");
    DocumentReference doc3 = db.document("Price List/6X36");
    DocumentReference doc4 = db.document("Price List/6X19");

    DocumentReference doc1F = db.document(doc1+"core/fmc");
    DocumentReference doc1S = db.document(doc1+"core/sc");
    DocumentReference doc2F = db.document(doc2+"core/fmc");
    DocumentReference doc2S = db.document(doc2+"core/sc");
    DocumentReference doc3F = db.document(doc3+"core/fmc");
    DocumentReference doc3S = db.document(doc3+"core/sc");
    DocumentReference doc4F = db.document(doc4+"core/fmc");
    DocumentReference doc4S = db.document(doc4+"core/sc");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
}
