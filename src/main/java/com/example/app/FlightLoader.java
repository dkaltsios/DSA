package com.example.app;

// This class is used to enter the data for the flights from a csv file
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class FlightLoader {
    // The date range of departure for the flight would be from the 01 of April to
    // the end of the month
    // The arrival time would be the departure time plus duration
    // Duration is calculated by dividing the distance by the
    // speed of the plane
    public static FlightMap loadFlights(String file, HashMap<String, Airline> airlines,
            HashMap<String, Plane> planes, HashMap<String, Airport> airports) {
        FlightMap flights = new FlightMap();
        // Read and load airlines from file
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            // Skip the first line
            reader.readNext();
            String[] parts;
            while ((parts = reader.readNext()) != null) {
                // Departure,Arrival,Airline,Plane,Departure_Date,Arrival_Date,Number,IATA
                Airport departure = airports.get(parts[0]);
                Airport arrival = airports.get(parts[1]);
                Airline airline = airlines.get(parts[2]);
                airline.newBooking();
                Plane plane = planes.get(parts[3]);
                LocalDateTime departureTime = LocalDateTime.parse(parts[4]);
                LocalDateTime arrivalTime = LocalDateTime.parse(parts[5]);
                int number = Integer.parseInt(parts[6]);
                String code = parts[7];

                // Adding the flight to the flight map
                flights.add(
                        new Flight(code, number, departure, arrival, plane, airline, departureTime, arrivalTime));
            }
        } catch (IOException | CsvValidationException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        // ! Big 500000
        // Duration of loading: 1832.0 miliseconds
        // Average duration of one: 0.0034185354785016666 miliseconds
        // ! Medium 75000
        // Duration of loading: 69.0 miliseconds
        // Average duration of one: 0.0009119505167719596 miliseconds
        // ! Small 4000
        // Duration of loading: 3.0 miliseconds
        // Average duration of one: 0.000725689404934688 miliseconds

        return flights;
    }
}
