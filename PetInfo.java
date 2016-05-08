package com.example.tlee1.petmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class PetInfo extends AppCompatActivity {
    DatabaseHelper myDbInfo;
    Button submit, btnviewAll, btnupdate;
    EditText petName, breed, birthYear, vetName, vetNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_info);
        myDbInfo = new DatabaseHelper(this);

        petName = (EditText) findViewById(R.id.editTextPetName);
        breed = (EditText) findViewById(R.id.editTextBreed);
        birthYear = (EditText) findViewById(R.id.editTextBirthYear);
        vetName = (EditText) findViewById(R.id.editTextVetName);
        vetNumber = (EditText) findViewById(R.id.editTextVetPhone);
        submit = (Button) findViewById(R.id.buttonSubmit);
        btnviewAll = (Button)findViewById(R.id.buttonView);
        btnupdate = (Button)findViewById(R.id.buttonUpdate);
        onClickListener();
        viewAll();
        UpdateData();


    }

    public void onClickListener() {
        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                                           /*boolean isInserted =*/
                        myDbInfo.insertInfo(petName.getText().toString(), breed.getText().toString(),  birthYear.getText().toString(),
                                vetName.getText().toString(), vetNumber.getText().toString());
                                           /* if(isInserted =true)*/
                        Intent intent = new Intent(PetInfo.this, PetInfo.class);
                        startActivity(intent);
                        Toast.makeText(PetInfo.this, "done", Toast.LENGTH_LONG).show();


                    }
                }
        );
    }

    public void viewAll(){
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor result = myDbInfo.getAllData();
                        if(result.getCount() == 0) {
                            //show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (result.moveToNext()) {
                            buffer.append("petName :" + result.getString(1) +"\n");
                            buffer.append("breed :" + result.getString(2) +"\n");
                            buffer.append("birthYear :" + result.getString(3) +"\n");
                            buffer.append("vetName :" + result.getString(4) +"\n");
                            buffer.append("vetNumber :" + result.getString(5) +"\n\n");

                        }
                        //show all data
                        showMessage("Pet Information", buffer.toString());

                    }
                }
        );
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void UpdateData() {
        btnupdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDbInfo.updateData(petName.getText().toString(),
                                breed.getText().toString(), birthYear.getText().toString(), vetName.getText().toString(),
                                vetNumber.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(PetInfo.this, "Data Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(PetInfo.this, "Data not Updated", Toast.LENGTH_LONG).show();
                        }

                }
        );
    }

}

