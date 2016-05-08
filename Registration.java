package com.example.tlee1.petmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText name, email, userName, pass1, pass2;
    Button btnsign;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        myDb = new DatabaseHelper(this);

        name = (EditText) findViewById(R.id.editTextName);
        email = (EditText) findViewById(R.id.editTextEmail);
        userName = (EditText) findViewById(R.id.editTextUserName);
        pass1 = (EditText) findViewById(R.id.editTextPass1);
        pass2 = (EditText) findViewById(R.id.editTextPass2);
        btnsign = (Button) findViewById(R.id.buttonSignUp);
        onSignUpClick();

    }

    public void onSignUpClick() {
        btnsign.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                                           /*boolean isInserted =*/
                        myDb.insertData(name.getText().toString(),
                                email.getText().toString(),
                                userName.getText().toString(),
                                pass1.getText().toString());
                                           /* if(isInserted =true)*/
                        Intent intent = new Intent(Registration.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(Registration.this, "done", Toast.LENGTH_LONG).show();

                       /* if (pass1 == pass2) {

                            Toast.makeText(Registration.this, "Information Saved", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(Registration.this, "Information not Saved", Toast.LENGTH_LONG).show();
                                            *//*Intent intent = new Intent(Registration.this, MainActivity.class);
                                            startActivity(intent);*//*

                        }*/
                    }

                }
        );


    }
}