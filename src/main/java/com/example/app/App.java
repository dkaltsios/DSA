package com.example.app;

// Using java issued libraries for date/timing operations, arraylists and scanning
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    static FlightMap flightMap;
    static ReservationMap reservationMap;
    static HashMap<String, Plane> planes;
    static HashMap<String, Airport> airports;
    static HashMap<String, Airline> airlines;
    static boolean isRunning = true;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Airport database
        String airportFile = "src/main/resources/airports.csv";
        // Load airports into a hashmap
        airports = AirportLoader.loadAirports(airportFile);
        // Load airlines into a hashmap
        String airlineFile = "src/main/resources/airlines.csv";
        airlines = AirlineLoader.loadAirlines(airlineFile);
        // Load planes into a hashmap
        String planeFile = "src/main/resources/planes.csv";
        planes = PlaneLoader.loadPlanes(planeFile);
        // Load flights into a FlightMap
        String flightFile = "src/main/resources/flights_month_large.csv";
        flightMap = FlightLoader.loadFlights(flightFile, airlines, planes, airports);
        // Create an empty reservation map
        reservationMap = new ReservationMap();
        do {
            menu();
        } while (isRunning);
    }

    private static void menu() {

        // User input
        String destination;
        String origin;
        LocalDate date;
        // Get destination and origin airports
        origin = inputAirport(airports, "Enter origin airport (EXIT to quit): ");
        destination = inputAirport(airports, "Enter destination airport (EXIT to quit):");
        date = inputDate("Enter date (YYYY-MM-DD) (EXIT to quit): ");

        // Return a list of flights from origin to destination
        ArrayList<ArrayList<Flight>> flightList = flightMap.getFlightsFromAToB(origin, destination,
                date);
        if (flightList.isEmpty()) {
            System.out.println("No flights available");
        } else {
            printFlightList(flightList);
            int flightNum = inputReservation(flightList.size(),
                    "Enter the number of flight you want to reserve (EXIT to quit): ");
            printPath(flightList.get(flightNum - 1));
            Reservation reservation = new Reservation(flightList.get(flightNum - 1),
                    inputUser("Enter your username (EXIT to quit): "));
            reservationMap.add(reservation);
            printReservation(reservation);
        }
    }

    private static void printPath(ArrayList<Flight> path) {
        System.out.print(path.get(0).getDepartureAirport().getCode() + " -> ");
        for (int i = 0; i < path.size() - 1; i++) {
            System.out.print(path.get(i).getArrivalAirport().getCode() + " -> ");
        }
        System.out.print(path.get(path.size() - 1).getArrivalAirport().getCode() + "\n");
    }

    private static void printFlightList(ArrayList<ArrayList<Flight>> flights) {
        int flightNum = 1;
        for (ArrayList<Flight> path : flights) {
            System.out.print(flightNum++ + ". ");
            printPath(path);
            System.out.println("Price: " + (int) Flight.getPrice(path));
            System.out.println("Local departure time: "
                    + format(
                            createGMTTime(
                                    path.get(0).getDepartureTime(),
                                    path.get(0).getDepartureAirport().getGMT())));
            System.out.println("Local arrival time: " + format(
                    createGMTTime(
                            path.get(path.size() - 1).getArrivalTime(),
                            path.get(path.size() - 1).getArrivalAirport().getGMT())));
            System.out.println("Duration: "
                    + getDuration(path.get(0).getDepartureTime(), path.get(path.size() - 1).getArrivalTime()));
            System.out.println();
        }
    }

    private static void printFlight(Flight flight) {
        System.out.print(flight.getDepartureAirport().getCode() + " -> " + flight.getArrivalAirport().getCode() + "\n");
    }

    private static void printReservation(Reservation reservation) {
        System.out.println("Reservation code: " + reservation.getCode());
        System.out.println("Username: " + reservation.getUsername() + "\n");
        for (int i = 0; i < reservation.getFlight().size(); i++) {
            System.out.println("Flight " + (i + 1) + ": " + reservation.getFlight().get(i).getCode());
            printFlight(reservation.getFlight().get(i));
            System.out.println("Local departure time: "
                    + format(
                            createGMTTime(
                                    reservation.getFlight().get(i).getDepartureTime(),
                                    reservation.getFlight().get(i).getDepartureAirport().getGMT())));
            System.out.println("Local arrival time: " + format(
                    createGMTTime(
                            reservation.getFlight().get(i).getArrivalTime(),
                            reservation.getFlight().get(i).getArrivalAirport().getGMT())));
            System.out.println("Seat: " + reservation.getSeat().get(i).getSeatCode());
            System.out.println();
        }
    }

    private static String inputAirport(HashMap<String, Airport> airports, String message) {
        System.out.print(message);
        String airport;
        do {
            airport = input();
            if (!airports.containsKey(airport)) {
                System.out.print("Invalid airport. Please try again (EXIT to quit): ");
            }
        } while (!airports.containsKey(airport));
        return airport;
    }

    private static LocalDate inputDate(String message) {
        System.out.print(message);
        String date;
        boolean isValid = false;
        do {
            date = input();
            try {
                LocalDate.parse(date);
                isValid = true;
            } catch (Exception e) {
                System.out.print("Invalid date. Please try again (YYYY-MM-DD) (EXIT to quit): ");
            }
        } while (!isValid);
        return LocalDate.parse(date);
    }

    private static int inputReservation(int size, String message) {
        System.out.print(message);
        int reservation = 0;
        boolean isValid = false;
        do {
            String reservationStr = input();
            try {
                reservation = Integer.parseInt(reservationStr);
                if (reservation < 1 || reservation > size)
                    throw new Exception();
                isValid = true;
            } catch (Exception e) {
                System.out.print("Invalid number of flight. Please try again (EXIT to quit): ");
            }
        } while (!isValid);
        return reservation;
    }

    private static String inputUser(String message) {
        System.out.print(message);
        String username = input();
        return username;
    }

    private static String input() {
        String input = scanner.nextLine();
        input = input.toUpperCase();
        if (input.equals("EXIT")) {
            isRunning = false;
            System.exit(0);
        }
        return input;
    }

    private static LocalDateTime createGMTTime(LocalDateTime dateTime, float gmt) {
        return dateTime
                .plusMinutes((int) gmt * 60);
    }

    private static String format(LocalDateTime dateTime) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(dateTime);
    }

    private static long getDuration(LocalDateTime departureTime, LocalDateTime arrivalTime) {
        return Duration.between(departureTime, arrivalTime).toHours();
    }

}