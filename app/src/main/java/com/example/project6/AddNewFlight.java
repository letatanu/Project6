package com.example.project6;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class AddNewFlight extends AppCompatActivity {
    private EditText airlineName;
    private EditText flightNumber;
    private EditText src;
    private EditText departDateTime;
    private EditText dest;
    private EditText arriveDateTime;
    private AlertDialog errorAlert;
    public static String EXTRA_AIRLINE = "com.example.project6.AddNewFlight";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_flight);
        airlineName = findViewById(R.id.airlineName);
        flightNumber = findViewById(R.id.flightNumber);
        src = findViewById(R.id.src);
        departDateTime = findViewById(R.id.departDateTime);
        dest = findViewById(R.id.dest);
        arriveDateTime = findViewById(R.id.arriveDateTime);

//        Create error dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setCancelable(false);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                System.out.println("Which " + new String(String.valueOf(which)));
//                AddNewFlight.this.clean();

            }
        });
        errorAlert = alert.create();

    }

    public void clean() {
        this.airlineName.getText().clear();
        this.flightNumber.getText().clear();
        this.arriveDateTime.getText().clear();
        this.dest.getText().clear();
        this.departDateTime.getText().clear();
        this.src.getText().clear();
    }

    ;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void addNewFlight(View view) {
        String airlineName = this.airlineName.getText().toString();
        String flightNumber = this.flightNumber.getText().toString();
        String src = this.src.getText().toString();
        String departDateTime = this.departDateTime.getText().toString();
        String dest = this.dest.getText().toString();
        String arriveDateTime = this.arriveDateTime.getText().toString();
        Airline airline = null;
        File dir = getFilesDir();
        TextParser textParser = new TextParser(airlineName, dir);
        Flight flight = null;
        boolean go = true;
        try {
            flight = new Flight(flightNumber, src, departDateTime, dest, arriveDateTime);

            try {
                airline = (Airline) textParser.parse();
                if (!airline.getName().equals(airlineName)) {
                    this.errorAlert.setMessage("The airline name is not as same as the airline name from the file");
                    this.errorAlert.show();
                    System.err.println("The airline name is not as same as the airline name from the file");
                }
                // Airline adds new flight
                airline.addFlight(flight);
                TextDumper textDumper = new TextDumper(airlineName, dir);
                try {
                    textDumper.dump(airline);
                } catch (IOException e) {
                    this.errorAlert.setMessage(e.getMessage());
                    this.errorAlert.show();
                    System.err.println(e.getMessage());
                    go = false;
                }
            } catch (ParserException e) {
                if (Objects.equals(e.getMessage(), "File is created successfully") || Objects.equals(e.getMessage(), "File is empty")) {
                    airline = new Airline(airlineName);
                    airline.addFlight(flight);
                    TextDumper textDumper = new TextDumper(airlineName, dir);
                    try {
                        textDumper.dump(airline);
                    } catch (IOException err) {
                        this.errorAlert.setMessage(e.getMessage());
                        this.errorAlert.show();
                        System.err.println(err.getMessage());
                        go = false;
                    }
                } else {
                    System.err.println(e.getMessage());
                    this.errorAlert.setMessage(e.getMessage());
                    this.errorAlert.show();
                    go = false;

                }
            }

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            this.errorAlert.setMessage(e.getMessage());
            this.errorAlert.show();
            go = false;
        }
        if (go) {
            Toast.makeText(this, "Flight added Successfully", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Result.class);
            String result = "The airline: " + airlineName + "\n" +
                    flight.toString();
            intent.putExtra("result", result);
            startActivity(intent);
        }

    }

}

