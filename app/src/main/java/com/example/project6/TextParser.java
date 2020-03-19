package com.example.project6;

import android.annotation.SuppressLint;

import com.example.project6.AbstractAirline;
import com.example.project6.Airline;
import com.example.project6.AirlineParser;
import com.example.project6.Flight;
import com.example.project6.ParserException;


import java.io.*;
import java.text.SimpleDateFormat;


public class TextParser implements AirlineParser {
    private final String fileName;
    private final File dir;

    TextParser(String fileName, File dir) {
        this.fileName = fileName;
        this.dir = dir;
    }
    @Override
    public AbstractAirline parse() throws ParserException {
        BufferedReader bufferedReader = null;
        Airline parsedAirline = null;
        String airlineName = null;
        try {
            File st = new File(this.dir, this.fileName);
            FileReader fileReader = new FileReader(st);
            bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            if (line == null) {
                throw new ParserException("File is empty");
            }
            while (line != null) {
                if (airlineName == null) {
                    airlineName = line;
                    parsedAirline = new Airline(airlineName);
                    line = bufferedReader.readLine();
                }
                else {
                    /**
                     * Parse Flight information
                     */
                    String flightNumber = line;
                    line = bufferedReader.readLine();
                    if (line == null) {
                        throw new ParserException("Wrong Format");
                    }
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY hh:mm aa");
                    String src = line;
                    line = bufferedReader.readLine();
                    if (line == null) {
                        throw new ParserException("Wrong Format");
                    }

                    String[] departDateTimeTest = line.split(" ");
                    if (departDateTimeTest.length != 3) {
                        throw new ParserException("Wrong format");
                    }

                    String departDateTime = line;
                    line = bufferedReader.readLine();

                    if (line == null) {
                        throw new ParserException("Wrong Format");
                    }

                    String dest = line;
                    line = bufferedReader.readLine();
                    if (line == null) {
                        throw new ParserException("Wrong Format");
                    }

                    String[] arriveDateTimeTest = line.split(" ");
                    if (arriveDateTimeTest.length != 3) {
                        throw new ParserException("Wrong format");
                    }

                    String arriveDateTime = line;

                    Flight flight = new Flight(flightNumber, src, departDateTime, dest, arriveDateTime);
                    parsedAirline.addFlight(flight);
                    line = bufferedReader.readLine();
                }
            }
        }
        catch (FileNotFoundException e) {
            /**
             * If reading file does not exist, try to create a new one.
             */
            try {
                File file = new File(this.dir,this.fileName);
                if (file.createNewFile()) {
                    throw new ParserException("File is created successfully");
                }
            }
            catch (IOException e1) {
                throw new ParserException("File does not exist and cannot create a new file;");
            }
        }
        catch (IOException e2) {
            throw new ParserException("File is empty");
        }

        if (parsedAirline == null){
            throw new ParserException("Cannot parse file");
        }


        return parsedAirline;
    }

}
