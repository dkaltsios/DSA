package com.example.app;

// Read the csv
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class AirportLoader {
    public static HashMap<String, Airport> loadAirports(String file) {
        HashMap<String, Airport> airports = new HashMap<>();
        // Read and load airports from file
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            // Skip the first line
            reader.readNext();
            String[] parts;
            while ((parts = reader.readNext()) != null) {
                // ID, Name, City, Country, IATA, ICAO, Latitude, Longitude, Altitude, Timezone, DST, TZ, Type, Source
                String name = parts[1];
                String code = parts[4];
                String city = parts[2];
                String country = parts[3];
                String timezone = parts[9];
                float gmt= Float.parseFloat(timezone);
                // Latitude and Longitude
                float latitude = Float.parseFloat(parts[6]);
                float longitude = Float.parseFloat(parts[7]);
                airports.put(code, new Airport(name, code, city, country, gmt, latitude, longitude));
            }
        } catch (IOException | CsvValidationException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return airports;
    }
}
