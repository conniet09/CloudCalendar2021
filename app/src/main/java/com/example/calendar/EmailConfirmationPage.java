package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EmailConfirmationPage extends AppCompatActivity {

    private TextView goToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_confirmation_page);

        goToLogin = findViewById(R.id.returnToLogin);

        goToLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent goToLogin = new Intent(EmailConfirmationPage.this, MainActivity.class);
                startActivity(goToLogin);
            }
        });
    }
}