package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class HomePage extends AppCompatActivity {

    Event startingDate = new Event();
    EventListAdapter adapter;
    int startingYear = 1;
    int startingMonth = Calendar.MONTH;
    int startingDay = Calendar.DAY_OF_MONTH;
    String selectedDate = startingDate.selectDate(startingYear, startingMonth, startingDay);
    ArrayList<Event> eventArrayList = new ArrayList<Event>();
    EventListParcel eventListParcel = new EventListParcel(eventArrayList);
    ListView eventListView;
    // Date currentTime = Calendar.getInstance().getTime();

    public ArrayList<Event> getEventList() {
        Log.d("string", eventArrayList.toString());
        return eventArrayList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_page);
        ConstraintLayout homePage = findViewById(R.id.activity_home_page);
        ImageView moreIcon = findViewById(R.id.threeDots);
        Button newEventButton = findViewById(R.id.createEvent);
        RelativeLayout eventSection = findViewById(R.id.eventSection);
        LinearLayout newEventBox = findViewById(R.id.newEventBox);
        EditText editEvent = findViewById(R.id.enterEvent);
        CalendarView calendarView = findViewById(R.id.calendarView);
        eventListView = (ListView) findViewById(R.id.eventListView);

        // Doesn't give in format I want
        // selectedDate = currentTime.toString();
        // calendarView.setDate(new Date().getTime());

        // Will implement try "get from data base" and catch will make new arraylist
        // try {
        // eventArrayList = new ArrayList<Event>();
        // eventListParcel = new EventListParcel(eventArrayList);
        // adapter = new EventListAdapter(eventSection.getContext(),
        // R.layout.event_view_layout,
        // eventArrayList);
        // eventListView.setAdapter(adapter);
        // } catch (Exception exception) {
        //
        // }

        // Associates the ListView with the arraylist of Event objects
        eventArrayList = new ArrayList<Event>();
        eventListParcel = new EventListParcel(eventArrayList);
        adapter = new EventListAdapter(eventSection.getContext(), R.layout.event_view_layout,
                eventArrayList);
        eventListView.setAdapter(adapter);

        // Gets date values when selected - does not work until changing date
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Event getsDate = new Event();
                startingYear = year;
                startingMonth = month;
                startingDay = dayOfMonth;
                selectedDate = getsDate.selectDate(year, month, dayOfMonth);
            }
        });

        // Creates box that allows for event creation
        newEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button confirm = findViewById(R.id.confirmEvent);
                Button cancel = findViewById(R.id.cancelEvent);
                newEventBox.setVisibility(View.VISIBLE);

                // Enter new event
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String stringEventText = editEvent.getText().toString();
                        newEventBox.setVisibility(View.GONE);
                        Event newEvent = new Event(stringEventText, selectedDate, false);
                        newEvent.setYear(startingYear);
                        newEvent.setMonth(startingMonth);
                        newEvent.setDay(startingDay);
                        eventArrayList.add(newEvent);
                        Collections.sort(eventArrayList);
                        eventListParcel.setEventArrayList(eventArrayList);
                        eventListView.setAdapter(adapter);
                        InputMethodManager closeKeyboard = (InputMethodManager) getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                        closeKeyboard.hideSoftInputFromWindow(homePage.getWindowToken(), 0);
                        editEvent.setText(null);

                    }
                });

                // Cancel event creation
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editEvent.setText(null);
                        newEventBox.setVisibility(View.GONE);
                        InputMethodManager closeKeyboard = (InputMethodManager) getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                        closeKeyboard.hideSoftInputFromWindow(homePage.getWindowToken(), 0);

                    }
                });
            }
        });

        // Allows for the kebab menu icon to be clickable
        moreIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoMorePage = new Intent(HomePage.this, MorePage.class);
                gotoMorePage.putExtra("GlobalEvents", eventListParcel);
                startActivityForResult(gotoMorePage, 2);
            }
        });

        // Enables interaction with ListView
        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent eventInteract = new Intent(getApplicationContext(), EventInteract.class);
                eventListParcel.setArrayIndex(i);
                eventInteract.putExtra("EventInteract", eventListParcel);

                // This is deprecated, but it may be the best solution at this point
                startActivityForResult(eventInteract, 1);
            }
        });

    }

    // After Interaction with ListView
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result From EventInteract
        if (requestCode == 1) {

            // Delete Event
            if (resultCode == RESULT_OK) {
                deleteEvent(eventListParcel.getArrayIndex());
            }
            // Update Event
            else if (resultCode == 2) {
                eventListParcel.setEventArrayList(data.getParcelableArrayListExtra("EventArrayList"));
                updateEventArrayList(eventListParcel.getArrayIndex());
            }
        }
        // Result from Adding ENM
        else if (requestCode == 2) {
            addENM(data);
        }
    }

    // Functions for Event Manipulation
    protected void deleteEvent(int arrayIndex) {
        eventArrayList.remove(arrayIndex);
        Collections.sort(eventArrayList);
        eventListParcel.setEventArrayList(eventArrayList);
        adapter.notifyDataSetChanged();
    }

    // This is not working for some reason
    protected void updateEventArrayList(int arrayIndex) {
        Event templateEvent = eventListParcel.getEventArrayList().get(arrayIndex);

        // Condense this into constructor later
        Event updatedEvent = new Event();
        updatedEvent.setEventDetails(templateEvent.getEventDetails());
        updatedEvent.setYear(templateEvent.getYear());
        updatedEvent.setMonth(templateEvent.getMonth() - 2);
        updatedEvent.setDay(templateEvent.getDay());
        updatedEvent.setEventDate(updatedEvent.selectDate(
                updatedEvent.getYear(), updatedEvent.getMonth(), updatedEvent.getDay()));
        updatedEvent.setRecurringEvent(false);
        eventArrayList.set(arrayIndex, updatedEvent);
        Collections.sort(eventArrayList);
        eventListParcel.setEventArrayList(eventArrayList);
        adapter.notifyDataSetChanged();
    }

    protected void addENM(Intent data) {
        eventListParcel.setEventArrayList(data.getParcelableArrayListExtra("GlobalEvents"));
        for (int i = eventArrayList.size(); i < eventListParcel.getEventArrayList().size(); i++){
            Event templateEvent = eventListParcel.getEventArrayList().get(i);
            Event updatedEvent = new Event();
            updatedEvent.setEventDetails(templateEvent.getEventDetails());
            updatedEvent.setYear(templateEvent.getYear());
            updatedEvent.setMonth(templateEvent.getMonth() - 1);
            updatedEvent.setDay(templateEvent.getDay());
            updatedEvent.setEventDate(updatedEvent.selectDate(
                    updatedEvent.getYear(), updatedEvent.getMonth(), updatedEvent.getDay()));
            updatedEvent.setRecurringEvent(false);

            eventArrayList.add(updatedEvent);
        }
        eventListParcel.setEventArrayList(eventArrayList);
        Collections.sort(eventArrayList);
        adapter.notifyDataSetChanged();
    }
}