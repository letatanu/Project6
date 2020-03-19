package com.example.project6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private AlertDialog errorAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void addNewFlight(View view) {
        Intent intent = new Intent(this, AddNewFlight.class);
        startActivity(intent);
    }

    public void search_view(View view) {
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }

    public void about(View view) {
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
        errorAlert.setMessage("In this project you will use Android to implement a small device application for your Airline application.\n" + "" +
                "For this project you will write an application using Android that has all of the functionality of the previous\n" +
                "assignments: creating an airline, entering flights, pretty printing an airlint and its flights, searching for flights,\n" +
                "etc.");
        errorAlert.show();
    }
}
