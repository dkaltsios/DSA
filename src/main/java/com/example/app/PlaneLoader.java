package com.example.app;

// This class is used to enter the data for the planes from a csv file
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class PlaneLoader {

    public static HashMap<String, Plane> loadPlanes(String file) {
        HashMap<String, Plane> planes = new HashMap<>();
        // Read and load planes from file
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            // Skip the first line
            reader.readNext();
            String[] parts;
            while ((parts = reader.readNext()) != null) {
                // Model,Aircraft Manufacturer,Seats
                String model = parts[0];
                String manufacturer = parts[1];
                // Plane have static number of seats
                int numberOfSeats = 180;
                planes.put(model, new Plane(model, manufacturer, numberOfSeats));
            }
        } catch (IOException | CsvValidationException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return planes;
    }
}
