package com.example.app;

// Read the csv
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class AirlineLoader {
    public static HashMap<String, Airline> loadAirlines(String file) {
        HashMap<String, Airline> airlines = new HashMap<>();
        // Read and load airlines from file
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            // Skip the first line
            reader.readNext();
            String[] parts;
            while ((parts = reader.readNext()) != null) {
                // Airline,IATA,Flights
                String name = parts[0];
                String code = parts[1];
                airlines.put(code, new Airline(name, code));
            }
        } catch (IOException | CsvValidationException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return airlines;
    }
}
