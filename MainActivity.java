package com.example.tlee1.petmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static Button button_login;
    private static Button button_signup;
    private static EditText username;
    private static EditText password;


    // DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onButtonClick();


    }


    public void onButtonClick() {

        username = (EditText) findViewById(R.id.editTextUser);
        password = (EditText) findViewById(R.id.editTextPass);
        button_login = (Button) findViewById(R.id.buttonLogin);
        button_signup = (Button) findViewById(R.id.buttonSign);

        button_signup.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Registration.class);
                        startActivity(intent);
                    }

                }
        );
        button_login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (username.getText().toString().equals("user") &&
                                password.getText().toString().equals("pass")) {
                            Toast.makeText(MainActivity.this, "User and Password is correct",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, MainPage.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "User and Password is not correct",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }}




















