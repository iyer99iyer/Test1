package com.example.sviye.asahiproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SelectActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Button[] btn = new Button[6];
    private Button btn_unfocus;
    private int[] btn_id = {R.id.grpBtn0, R.id.grpBtn1, R.id.grpBtn2, R.id.grpBtn3, R.id.grpCoreBtn0, R.id.grpCoreBtn1};
    String buttonConst, buttonCore;

    Spinner spinner;
    String spinnerDia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        spinner = findViewById(R.id.diaSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(this,
                        R.array.diameters,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        for(int i = 0; i < btn.length; i++){
            btn[i] = findViewById(btn_id[i]);
            btn[i].setBackgroundResource(R.drawable.buttonunselected);
            btn[i].setOnClickListener(this);
        }
        btn_unfocus = btn[0];

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.grpBtn0 :
                setFocus(btn_unfocus, btn[0]);
                buttonConst = btn[0].getText().toString();
                break;

            case R.id.grpBtn1 :
                setFocus(btn_unfocus, btn[1]);
                buttonConst = btn[0].getText().toString();
                break;

            case R.id.grpBtn2 :
                setFocus(btn_unfocus, btn[2]);
                buttonConst = btn[0].getText().toString();
                break;

            case R.id.grpBtn3 :
                setFocus(btn_unfocus, btn[3]);
                buttonConst = btn[0].getText().toString();
                break;

            case R.id.grpCoreBtn0:
                setFocus(btn_unfocus, btn[4]);
                break;
            case R.id.grpCoreBtn1:
                setFocus(btn_unfocus, btn[5]);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerDia = parent.getItemAtPosition(position).toString();
        Toast.makeText(this,spinnerDia+" "+buttonConst+" are selected!",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
