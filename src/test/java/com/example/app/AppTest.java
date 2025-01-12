package com.example.app;

import java.time.LocalDate;
import java.time.LocalDateTime;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
                extends TestCase {
        /**
         * Create the test case
         *
         * @param testName name of the test case
         */
        public AppTest(String testName) {
                super(testName);
        }

        /**
         * @return the suite of tests being tested
         */
        public static Test suite() {
                return new TestSuite(AppTest.class);
        }

        public void test() {
                // -------------------------------------------------------------------------------------------------------------------------------
                // Sample Data
                float a = (float) 2.5;
                float x1 = (float) 40.741895;
                float y1 = (float) -73.872611;
                float x2 = (float) 50.741895;
                float y2 = (float) -20.872611;
                float x3 = (float) 60.741895;
                float y3 = (float) -30.872611;
                Plane plane = new Plane("SAMPLE_PLANE", "SAMPLE_MANUFACTURER", 180);
                Airline airline = new Airline("SAMPLE_AIRLINE", "SAMPLE_CODE");

                Airport airport1 = new Airport("SAMPLE_AIRPORT1", "SA1", "SAMPLE_CITY1", "SAMPLE_COUNTRY1", a, x1, y1);
                Airport airport2 = new Airport("SAMPLE_AIRPORT2", "SA2", "SAMPLE_CITY2", "SAMPLE_COUNTRY2", a, x2, y2);
                Airport airport3 = new Airport("SAMPLE_AIRPORT3", "SA3", "SAMPLE_CITY3", "SAMPLE_COUNTRY3", a, x3, y3);

                Flight flight1 = new Flight("SAMPLE_CODE1", 1, airport1, airport2, plane, airline, LocalDateTime.now(),
                                LocalDateTime.now().plusMinutes(120));
                Flight flight2 = new Flight("SAMPLE_CODE2", 2, airport2, airport3, plane, airline,
                                LocalDateTime.now().plusMinutes(160), LocalDateTime.now().plusMinutes(300));
                Flight flight3 = new Flight("SAMPLE_CODE3", 2, airport1, airport2, plane, airline,
                                LocalDateTime.now().plusMinutes(160), LocalDateTime.now().plusMinutes(300));

                // -------------------------------------------------------------------------------------------------------------------------------
                // Testing the FlightMap ADT
                FlightMap flightMap = new FlightMap();
                // Testing the addition to the flight
                flightMap.add(flight1);
                flightMap.add(flight2);
                flightMap.add(flight3);
                // Testing if the getter of the flight works. Should return newly added flight.
                Assert.assertEquals(flight1, flightMap.get(flight1.getCode()));
                // Testing if the getFlights from A to B works
                Assert.assertTrue(
                                !(flightMap.getFlightsFromAToB(airport1.getCode(), airport3.getCode(), LocalDate.now())
                                                .isEmpty()));
                // Testing if the removal of a flight works
                flightMap.remove(flight3.getCode());
                Assert.assertNull(flightMap.get(flight3.getCode()));
                // -------------------------------------------------------------------------------------------------------------------------------
                // Testing for the ReservationMap ADT
                ReservationMap reservationMap = new ReservationMap();
                Reservation reservation = new Reservation(
                                flightMap.getFlightsFromAToB(airport1.getCode(), airport3.getCode(), LocalDate.now())
                                                .get(0),
                                "John_Doe");
                // Testing the addition of reservation
                reservationMap.add(reservation);
                // Testing if the getter of the reservation ADT works. Should return newly added
                // reservation.
                Assert.assertEquals(reservation, reservationMap.get(reservation.getCode()));
                // Testing if the remove method actually removes the reservation
                reservationMap.remove(reservation.getCode());
                Assert.assertNull(reservationMap.get(reservation.getCode()));
        }
}
