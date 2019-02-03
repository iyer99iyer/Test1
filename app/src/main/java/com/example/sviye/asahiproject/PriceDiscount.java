package com.example.sviye.asahiproject;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DecimalFormat;

public class PriceDiscount extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PriceDiscount";

    TextView priceTextView, priceFinalTextView;

    EditText discountEditText;
    Double removeDisPrice;

    public static final String DIAMETER_KEY = "diameter";
    public static final String CORE_KEY = "core";
    public static final String CONSTRUCTION_KEY = "construction";

    String diameterString, constString, coreString, path;

    String diameterAns;
    Double priceAns;
    int galPrice = 0, gstRate = 18;

    Button getFinalPriceBtn, getFinalPriceWithGSTBtn;

    private Button[] btn = new Button[2];
    private Button btn_unfocus;
    private int[] btn_id = {R.id.grpGalvinBtn, R.id.grpGalvinBtn1};

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_discount);

        priceTextView = findViewById(R.id.priceTextView);
        priceFinalTextView = findViewById(R.id.priceFinalTextView);

        getFinalPriceBtn = findViewById(R.id.getFinalPrice);
        getFinalPriceWithGSTBtn = findViewById(R.id.getFinalPriceWithGST);

        disableBtn(getFinalPriceBtn);
        disableBtn(getFinalPriceWithGSTBtn);

        Intent intent = getIntent();
        diameterString = intent.getStringExtra(DIAMETER_KEY);
        constString = intent.getStringExtra(CONSTRUCTION_KEY);
        coreString = intent.getStringExtra(CORE_KEY);

        Toast.makeText(this, diameterString + " " + coreString + " " + constString, Toast.LENGTH_LONG).show();

        path = constString + " " + coreString;

        showPrice();

        for (int i = 0; i < btn.length; i++) {
            btn[i] = findViewById(btn_id[i]);
            btn[i].setBackgroundResource(R.drawable.buttonunselected);
            btn[i].setOnClickListener(this);
        }
        btn_unfocus = btn[0];

        discountEditText = findViewById(R.id.discountEditText);
    }


    private void showPrice() {
        Log.d(TAG, "showPrice: ");
        db.collection(path)
                .whereEqualTo("diameter", diameterString)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                            DiaPrice diaPrice = documentSnapshot.toObject(DiaPrice.class);

                            diameterAns = diaPrice.getDiameter();
                            priceAns = diaPrice.getPrice();

                            DecimalFormat decimalFormat = new DecimalFormat("0.00");
                            String priceAnsString = decimalFormat.format(priceAns);

                            Log.d(TAG, "onSuccess: " + "Diameter : " + diameterAns
                                    + " Price : " + priceAns.toString());

                            /*Toast.makeText(PriceDiscount.this,path+"Diameter : " +
                                    diameterAns + " Price : " + priceAns.toString(),Toast.LENGTH_LONG).show();*/

                            priceTextView.setText(priceAnsString);

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: ");
            }
        });

    }

    @Override
    public void onClick(View v) {

        initBtn(getFinalPriceBtn);
        disableBtn(getFinalPriceWithGSTBtn);

        switch (v.getId()) {
            case R.id.grpGalvinBtn:
                setFocus(btn_unfocus, btn[0]);
                constString = btn[0].getText().toString();
                galPrice = 12;
                break;

            case R.id.grpGalvinBtn1:
                setFocus(btn_unfocus, btn[1]);
                constString = btn[1].getText().toString();
                galPrice = 0;
                break;
        }
    }

    private void setFocus(Button btn_unfocus, Button btn_focus) {
        btn_unfocus.setTextColor(Color.rgb(49, 50, 51));
        btn_unfocus.setBackgroundResource(R.drawable.buttonunselected);
        btn_focus.setTextColor(Color.rgb(255, 255, 255));
        btn_focus.setBackgroundResource(R.drawable.button);
        this.btn_unfocus = btn_focus;
    }

    private void finalPrice() {
        Double addGalPrice = priceAns + (priceAns * galPrice / 100);

        Double discount;

        if (discountEditText.getText().toString().equals("")) {
            discount = 0.0;
        } else {
            discount = Double.parseDouble(discountEditText.getText().toString());
        }

        removeDisPrice = addGalPrice - (addGalPrice * discount / 100);

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String finalprice = decimalFormat.format(removeDisPrice);

        priceFinalTextView.setText(finalprice);

    }

    public void getDisPrice(View view){
        finalPrice();
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getFinalPriceBtn.getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception
        }
        initBtn(getFinalPriceWithGSTBtn);
    }

    private void initBtn(Button button){
        button.setEnabled(true);
        button.setBackgroundResource(R.drawable.button);
    }

    private void disableBtn(Button button){
        button.setBackgroundResource(R.drawable.buttonunselected);
        button.setTextColor(Color.rgb(255, 255, 255));
        button.setEnabled(false);
    }

    public void getDisPriceWithGST(View view){
        Double finalPrice = removeDisPrice+(removeDisPrice*gstRate/100);

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String finalprice = decimalFormat.format(finalPrice);

        priceFinalTextView.setText(finalprice);
    }


}
