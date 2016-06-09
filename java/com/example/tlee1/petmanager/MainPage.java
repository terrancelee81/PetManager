package com.example.tlee1.petmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPage extends AppCompatActivity {
    private static Button button_pet;
    private static Button button_fitness;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        onClickListener();
    }

    public void onClickListener() {
        button_fitness = (Button) findViewById(R.id.buttonFitness);
        button_fitness.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainPage.this, Petfitness.class);
                        startActivity(intent);
                    }
                }
        );


        button_pet = (Button) findViewById(R.id.buttonPet);
        button_pet.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainPage.this, PetInfo.class);
                        startActivity(intent);
                    }
                }
        );

    }
}
