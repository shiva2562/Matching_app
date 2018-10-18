package com.example.lab.stablematching;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Spinner spinner= findViewById(R.id.spinner1);
        Spinner spinner4= findViewById(R.id.spinner2);
        Spinner spinner5= findViewById(R.id.spinner3);
        spinner.setOnItemSelectedListener(this);
        spinner4.setOnItemSelectedListener(this);
        spinner5.setOnItemSelectedListener(this);
        spinner.setOnItemSelectedListener(this);
        List<Integer> ranks = new ArrayList<Integer>();
        ranks.add(1);
        ranks.add(2);
        ranks.add(3);
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, ranks);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner4.setAdapter(dataAdapter);
        spinner5.setAdapter(dataAdapter);
    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item;
        switch( parent.getId()){
            case R.id.spinner1:
                item = parent.getItemAtPosition(position).toString();
                TextView textElement = (TextView) findViewById(R.id.ran1);
                textElement.setText(item);
                break;
            case R.id.spinner2:
                item = parent.getItemAtPosition(position).toString();
                textElement = (TextView) findViewById(R.id.ran2);
                textElement.setText(item);
                break;
            case R.id.spinner3:
                item = parent.getItemAtPosition(position).toString();
                textElement = (TextView) findViewById(R.id.ran3);
                textElement.setText(item);
                break;
        }


    }
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
