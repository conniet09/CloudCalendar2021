package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingsPage extends AppCompatActivity {

    private ImageView calendar;
    private ImageView back;
    private Button signOut;
    private Button changeToBlack;
    private Button changeToWhite;
    private Button changeToPurple;
    private Button changeToBlue;
    private ImageView faceIcon;
    private TextView hi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        calendar = findViewById(R.id.calendarIcon);
        back = findViewById(R.id.backIcon);
        signOut = findViewById(R.id.signOutButton);
        changeToBlack = findViewById(R.id.blackButton);
        changeToWhite = findViewById(R.id.whiteButton);
        changeToPurple = findViewById(R.id.purpleButton);
        changeToBlue = findViewById(R.id.blueButton);
        faceIcon = findViewById(R.id.faceIcon);
        hi = findViewById(R.id.hi);

        //go back to calendar
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToCalendar = new Intent(SettingsPage.this, HomePage.class);
                goToCalendar.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(goToCalendar, 0);
            }
        });

        //go back a page
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsPage.this, MorePage.class));
            }
        });

        //change color icon
        changeToBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                faceIcon.setColorFilter(Color.parseColor("#FF000000"));
                hi.setTextColor(Color.parseColor("#ffffff"));
                signOut.setBackgroundColor(Color.parseColor("#FF000000"));
                signOut.setTextColor(Color.parseColor("#ffffff"));
            }
        });

        changeToWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                faceIcon.setColorFilter(Color.parseColor("#ffffff"));
                hi.setTextColor(Color.parseColor("#FF000000"));
                signOut.setBackgroundColor(Color.parseColor("#ffffff"));
                signOut.setTextColor(Color.parseColor("#DDFBBB"));
            }
        });

        changeToPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                faceIcon.setColorFilter(Color.parseColor("#DBBBFB"));
                hi.setTextColor(Color.parseColor("#ffffff"));
                signOut.setBackgroundColor(Color.parseColor("#DBBBFB"));
                signOut.setTextColor(Color.parseColor("#ffffff"));
            }
        });

        changeToBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                faceIcon.setColorFilter(Color.parseColor("#8DBAEE"));
                hi.setTextColor(Color.parseColor("#ffffff"));
                signOut.setBackgroundColor(Color.parseColor("#8DBAEE"));
                signOut.setTextColor(Color.parseColor("#ffffff"));
            }
        });

        //sign out button
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsPage.this, MainActivity.class));
            }
        });
    }

}