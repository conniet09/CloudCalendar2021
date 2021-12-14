package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText eInput;
    private EditText pwInput;
    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connects XML layout with variables defined
        eInput = findViewById(R.id.emailAddress);
        pwInput = findViewById(R.id.enterPassword);
        Button logIn = findViewById(R.id.loginButton);
        TextView signUp = findViewById(R.id.signUp);

        //if logIn button is clicked
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputEmail = eInput.getText().toString();
                String inputPassword = pwInput.getText().toString();

                if(inputEmail.isEmpty() || inputPassword.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter your email and/or password.", Toast.LENGTH_SHORT).show();
                }
                else{
                    isValid = validate(inputEmail, inputPassword);
                    if(!isValid){
                        Toast.makeText(MainActivity.this, "Incorrect login.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent goToHomePage = new Intent(MainActivity.this, HomePage.class);
                        startActivity(goToHomePage);
                    }
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSignUp = new Intent(MainActivity.this, SignUpScreen.class);
                startActivity(goToSignUp);
            }
        });
    }

    private boolean validate(String email, String password){
        String username = "admin";
        String password1 = "12345";
        if(email.equals(username) && password.equals(password1)){
            return true;
        }
        return false;
    }


}