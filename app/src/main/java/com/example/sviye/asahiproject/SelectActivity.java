package com.example.sviye.asahiproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

public class SelectActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private static final String TAG = "SelectActivity";

    private FirebaseAuth mAuth;

    public static final String DIAMETER_KEY ="diameter";
    public static final String CORE_KEY="core";
    public static final String CONSTRUCTION_KEY="construction";

    private Button[] btn = new Button[4];
    private Button[] coreBtn = new Button[2];  // button for core

    private Button btn_unfocus,coreBtn_unfocus;  // button for core

    private int[] btn_id = {R.id.grpBtn0, R.id.grpBtn1, R.id.grpBtn2, R.id.grpBtn3};
    private int[] coreBtn_id = {R.id.grpCoreBtn0,R.id.grpCoreBtn1};  // button for core

    String constString, coreString;

    Spinner spinner;
    String diameterString;

    Button getPriceBtn;

    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        mToolBar = findViewById(R.id.main_hello_bar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("Select Wire");

        mAuth = FirebaseAuth.getInstance();



        spinner = findViewById(R.id.diaSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(this,
                        R.array.diameters,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        getPriceBtn = findViewById(R.id.getPricebtn);

        getPriceBtn.setEnabled(false);
        getPriceBtn.setBackgroundResource(R.drawable.buttonunselected);
        getPriceBtn.setTextColor(Color.rgb(255, 255, 255));

        for(int i = 0; i < btn.length; i++){
            btn[i] = findViewById(btn_id[i]);
            btn[i].setBackgroundResource(R.drawable.buttonunselected);
            btn[i].setOnClickListener(this);
        }
        btn_unfocus = btn[0];

        for (int i = 0 ; i<coreBtn.length ; i++){
            coreBtn[i] = findViewById(coreBtn_id[i]);
            coreBtn[i].setEnabled(false);
            coreBtn[i].setTextColor(Color.rgb(255, 255, 255));
            coreBtn[i].setBackgroundResource(R.drawable.buttonunselected);
        }
        coreBtn_unfocus = coreBtn[0];

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.grpBtn0 :
                setFocus(btn_unfocus, btn[0]);
                coreButtonInit();
                coreButtonRenew(1,2);
                constString = btn[0].getText().toString();
                break;

            case R.id.grpBtn1 :
                setFocus(btn_unfocus, btn[1]);
                coreButtonInit();
                coreButtonRenew(1,2);
                constString = btn[1].getText().toString();
                break;

            case R.id.grpBtn2 :
                setFocus(btn_unfocus, btn[2]);
                coreButtonInit();
                coreButtonRenew(1,1);
                constString = btn[2].getText().toString();
                break;

            case R.id.grpBtn3 :
                setFocus(btn_unfocus, btn[3]);
                coreButtonInit();
                coreButtonRenew(2,2);
                constString = btn[3].getText().toString();
                break;
        }

        switch (v.getId()){
            case R.id.grpCoreBtn0:
                setCoreFocus(coreBtn_unfocus, coreBtn[0]);
                coreString = coreBtn[0].getText().toString().toLowerCase();
                break;
            case R.id.grpCoreBtn1:
                setCoreFocus(coreBtn_unfocus, coreBtn[1]);
                coreString = coreBtn[1].getText().toString().toLowerCase();
                break;
        }

    }

    private void setFocus(Button btn_unfocus, Button btn_focus){
        btn_unfocus.setTextColor(Color.rgb(49, 50, 51));
        btn_unfocus.setBackgroundResource(R.drawable.buttonunselected);
        btn_focus.setTextColor(Color.rgb(255, 255, 255));
        btn_focus.setBackgroundResource(R.drawable.button);
        this.btn_unfocus = btn_focus;
    }

    private void setCoreFocus(Button coreBtn_unfocus, Button coreBtn_focus){
        coreBtn_unfocus.setTextColor(Color.rgb(49, 50, 51));
        coreBtn_unfocus.setBackgroundResource(R.drawable.buttonunselected);
        coreBtn_focus.setTextColor(Color.rgb(255, 255, 255));
        coreBtn_focus.setBackgroundResource(R.drawable.button);

        if (!coreBtn[0].isEnabled()){
            coreBtn[0].setTextColor(Color.rgb(255, 255, 255));
        }

        this.coreBtn_unfocus = coreBtn_focus;

        getPriceBtn.setEnabled(true);
        getPriceBtn.setTextColor(Color.rgb(255, 255, 255));
        getPriceBtn.setBackgroundResource(R.drawable.button);
    }

    public void coreButtonInit(){
        getPriceBtn.setEnabled(false);
        getPriceBtn.setBackgroundResource(R.drawable.buttonunselected);
        getPriceBtn.setTextColor(Color.rgb(255, 255, 255));

        for (int i = 0; i<coreBtn.length; i++){
            coreBtn[i].setEnabled(false);
            coreBtn[i].setTextColor(Color.rgb(255, 255, 255));
            coreBtn[i].setBackgroundResource(R.drawable.buttonunselected);
            coreString ="";
        }
    }

    public void coreButtonRenew(int start, int end){
        for (int i = start-1 ; i<end ; i++){
            coreBtn[i].setEnabled(true);
            coreBtn[i].setTextColor(Color.rgb(49, 50, 51));
            coreBtn[i].setBackgroundResource(R.drawable.buttonunselected);
            coreBtn[i].setOnClickListener(this);
        }
        coreBtn_unfocus = coreBtn[0];
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        diameterString = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // sending data to next activity

    public void getPrice(View v) {
        Intent intent = new Intent(this,PriceDiscount.class);
        intent.putExtra(DIAMETER_KEY, diameterString);
        intent.putExtra(CONSTRUCTION_KEY, constString);
        intent.putExtra(CORE_KEY, coreString);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.asahi_app_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId()==R.id.logout_btn){

            FirebaseAuth.getInstance().signOut();

            Intent loginIntent = new Intent(SelectActivity.this,LoginActivity.class);
            startActivity(loginIntent);

        }

        return true;
    }
}