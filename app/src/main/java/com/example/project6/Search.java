package com.example.project6;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Search extends AppCompatActivity {
    private EditText airlineName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_view_added);
        airlineName = findViewById(R.id.airlineName);
    }

    public void searchAirline(View view) {
        File dir = getFilesDir();
        String airlineNa = this.airlineName.getText().toString();
        TextParser textParser = new TextParser(airlineNa, dir);
        Airline airline = null;
        try {
            airline = (Airline) textParser.parse();
            if (!airline.getName().equals(airlineName)) {
                Toast.makeText(this, "The airline name is not as same as the airline name from the file", Toast.LENGTH_LONG).show();
                System.err.println("The airline name is not as same as the airline name from the file");
            }
            else {
                String result = getPrettyPrint(airline);
                Intent intent = new Intent(this, Result.class);
                intent.putExtra("result", result);
                startActivity(intent);
            }
        } catch (ParserException e) {
            Toast.makeText(this, "The airline is not existing", Toast.LENGTH_LONG).show();
        }
    }

    private String getPrettyPrint(AbstractAirline abstractAirline) {
        StringBuilder result = new StringBuilder();
        ArrayList<AbstractFlight> Flights = (ArrayList<AbstractFlight>) abstractAirline.getFlights();

        //Convert Flights in airline
        ArrayList<Flight> flights = new ArrayList<>();
        for (AbstractFlight f: Flights){
            flights.add((Flight)f);
        }
        Collections.sort(flights);

        int l = flights.size();

        System.out.println("The airline is " + abstractAirline.getName() + " with " + Integer.toString(l) + " flights following: ");
        for (int i = 0; i < l; i++) {
            result.append("The flight ").append(i).append("\n");
            Flight flight = (Flight) flights.get(i);
            result.append(flight.toString()).append(" duration: ").append(Long.toString(flight.timesDiffInMinutes())).append(" minutes").append("\n");
            result.append("------------------------------------------").append("\n");
        }
        return result.toString();
    }
}
