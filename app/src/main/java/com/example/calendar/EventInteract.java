package com.example.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class EventInteract extends HomePage {
    Button editButton, deleteButton, cancelButton;
    EventListParcel eventListParcel;
    ArrayList<Event> eventArrayList;
    int arrayIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_interact);

        Intent intent = getIntent();
        eventListParcel = intent.getParcelableExtra("EventInteract");
        eventArrayList = eventListParcel.getEventArrayList();
        arrayIndex = eventListParcel.getArrayIndex();
        Event currentEvent = eventArrayList.get(arrayIndex);
        String eventDetails = currentEvent.getEventDetails();
        int year = currentEvent.getYear();
        int month = currentEvent.getMonth();
        int day = currentEvent.getDay();


        // Resizing activity
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8), (int)(height*.4));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -200;
        getWindow().setAttributes(params);



        // Mapping XML elements
        editButton = findViewById(R.id.editEvent);
        deleteButton = findViewById(R.id.deleteEvent);
        cancelButton = findViewById(R.id.cancelEventInteract);



        // Button Interactions
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // This line here will need to get stuff from some sort of new Event Creator
                eventArrayList.set(eventListParcel.getArrayIndex(), eventArrayList.get(eventListParcel.getArrayIndex()));

                intent.putExtra("EventArrayList", eventArrayList);
                setResult(2, intent);
                finish();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("EventArrayList", eventArrayList);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });



    }
}
