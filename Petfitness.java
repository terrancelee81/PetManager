package com.example.tlee1.petmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Petfitness extends Activity {

    DatabaseHelper myDbFitness;

    private TextView textViewX;
    private TextView textViewY;
    private TextView textViewZ;

    private TextView textSensitive;

    private TextView textViewSteps;
    private TextView steps;
    private TextView textDate;
    Button buttonReset;
    Button btnHistory;


    private SensorManager sensorManager;
    private float acceleration;

    private float previousY;
    private float currentY;
    private int numSteps;

    private SeekBar seekBar;
    private int threshold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petfitness);
        myDbFitness = new DatabaseHelper(this);

        textViewX = (TextView) findViewById(R.id.textViewX);
        textViewY = (TextView) findViewById(R.id.textViewY);
        textViewZ = (TextView) findViewById(R.id.textViewZ);

        textViewSteps = (TextView) findViewById(R.id.textSteps);
        textSensitive = (TextView) findViewById(R.id.textSensitive);
        steps = (TextView)findViewById(R.id.textSteps);
        textDate = (TextView)findViewById(R.id.textDate);

        buttonReset = (Button) findViewById(R.id.buttonReset);
        btnHistory = (Button)findViewById(R.id.buttonHistory);

        seekBar = (SeekBar) findViewById(R.id.seekBar);

        seekBar.setProgress(10);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        threshold = 10;
        textSensitive.setText(String.valueOf(threshold));

        previousY = 0;
        currentY = 0;
        numSteps = 0;

        acceleration = 0.00f;

        enableAccelerometerListening();
        seeHistory();


    }

    private void enableAccelerometerListening() {
        //Initialize the Sensor Manager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    private SensorEventListener sensorEventListener = new SensorEventListener() {


        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            currentY = y;

            if (Math.abs(currentY - previousY) > threshold) {
                numSteps++;
                textViewSteps.setText(String.valueOf(numSteps));
            }

            textViewX.setText(String.valueOf(x));
            textViewY.setText(String.valueOf(y));
            textViewZ.setText(String.valueOf(z));

            previousY = y;

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

         public void resetSteps(View v) {
              buttonReset.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                             /*boolean isInserted =*/
                        new EndpointsAsyncTask().execute(numSteps);
                        myDbFitness.insertSteps(textViewSteps.getText().toString()/*, textDate.getText().toString()*/);
                        /* if(isInserted =true)*/
                        numSteps = 0;
                        textViewSteps.setText(String.valueOf(numSteps));
                        Toast.makeText(Petfitness.this, "done", Toast.LENGTH_LONG).show();

                    }
                }

                    );

                    }

    public void seeHistory(){
        btnHistory.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor result = myDbFitness.getSteps();
                        if (result.getCount() == 0) {
                            //show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (result.moveToNext()) {
                            buffer.append("steps :" + result.getString(1) + "\n");

                        }
                        //show all data
                        showMessage("History", buffer.toString());

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


                    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            threshold = seekBar.getProgress();

                            textSensitive.setText(String.valueOf(threshold));
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    };
                }






