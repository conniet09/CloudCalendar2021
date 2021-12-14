package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;

public class ENMPopUp extends EventsNearMePage{
    EventListParcel eventListParcel;
    String pickedEvent;
    int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enmpop_up);



        Intent intent = getIntent();
        pickedEvent = intent.getStringExtra("pickedEvent");
        year = intent.getIntExtra("pickedYear", 1);
        month = intent.getIntExtra("pickedMonth", 1);
        day = intent.getIntExtra("pickedDay", 1);
        eventListParcel = intent.getParcelableExtra("GlobalEvents");


        //set buttons equal to variables in this class
        Button saveButton = findViewById(R.id.ENMPopSave);
        Button cancelButton = findViewById(R.id.ENMPopCancel);

        //if person clicks on save
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                    Event addEvent = new Event(pickedEvent, year, month, day, false);
                    eventListParcel.getEventArrayList().add(addEvent);
                    intent.putExtra("GlobalEvents", eventListParcel.getEventArrayList());
                    setResult(RESULT_OK, intent);
                    finish();
            }
        });

        //if person clicks on cancel
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //sets up the popup box
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.2));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);
    }
}