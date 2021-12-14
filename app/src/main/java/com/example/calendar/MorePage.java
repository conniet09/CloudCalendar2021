package com.example.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MorePage extends AppCompatActivity {

    private Button signOut;
    private Button settings;
    private Button eventsNearMe;
    private ImageView calendar;
    private ImageView back;
    EventListParcel eventListParcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_page);

        Intent intent = getIntent();
        eventListParcel = intent.getParcelableExtra("GlobalEvents");


        signOut = findViewById(R.id.signOutButton);
        settings = findViewById(R.id.settingsButton);
        eventsNearMe = findViewById(R.id.eventsNearMeButton);
        calendar = findViewById(R.id.calendarIcon);
        back = findViewById(R.id.backIcon);

        //go back to calendar
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("GlobalEvents", eventListParcel.getEventArrayList());
                setResult(2, intent);
                finish();
            }
        });

        //go back a page
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("GlobalEvents", eventListParcel.getEventArrayList());
                setResult(2, intent);
                finish();
            }
        });

        //go to settings page
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MorePage.this, SettingsPage.class));
            }
        });

        //go to events near you page
        eventsNearMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToENMPage = new Intent(MorePage.this, EventsNearMePage.class);
                goToENMPage.putExtra("GlobalEvents", eventListParcel);
                startActivityForResult(goToENMPage, 1);
            }
        });

        //sign out
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MorePage.this, MainActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        eventListParcel.setEventArrayList(data.getParcelableArrayListExtra("GlobalEvents"));
    }


    //CREATING MENU
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.mymenu, menu);
//        return true;
//    }
//
//    //MAKING MENU FUNCTIONAL
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch(item.getItemId()) {
//            case R.id.settingsOption:
//                startActivity(new Intent(MorePage.this, SettingsPage.class));
//                return true;
//            case R.id.eventsNearMeOption:
//                startActivity(new Intent(MorePage.this, EventsNearMePage.class));
//                return true;
//            case R.id.signOutOption:
//                startActivity(new Intent(MorePage.this, MainActivity.class));
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}