package com.example.app;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class PlaneLoader {
    // The date range of departure for the flight would be form the current time to
    // the next 2 weeks
    // The arrival time would be the departure time plus the distance divided by the
    // speed of the plane

    // TODO: Write a script for the flight number to be generated from 1000-9999 but
    // be unique to the airline and route
    public static HashMap<String, Plane> loadPlanes(String file) {
        HashMap<String, Plane> planes = new HashMap<>();
        // Read and load airlines from file
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            // Skip the first line
            reader.readNext();
            String[] parts;
            while ((parts = reader.readNext()) != null) {
                // Model,Aircraft Manufacturer,Seats
                String model = parts[0];
                String manufacturer = parts[1];
                int numberOfSeats = Integer.parseInt(parts[2]);
                planes.put(model, new Plane(model, manufacturer, numberOfSeats));
            }
        } catch (IOException | CsvValidationException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return planes;
    }
}
