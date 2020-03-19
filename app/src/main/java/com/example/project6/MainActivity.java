package com.example.project6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
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
}
