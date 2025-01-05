import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Plane plane1 = new Plane("Boeing 747", "B747", "Boeing", 60);
        Plane plane2 = new Plane("Kaltsios 747", "A747", "DimitrisAir", 60);
        Plane plane3 = new Plane("Sudak 747", "YY747", "YaroAirlines", 60);
        Plane plane4 = new Plane("Chatzo 747", "C747", "Air Chatzopoulos ", 60);
        // for (int i = 0; i < seats.length; i++) {
        //     System.out.println(seats[i].getSeatCode());
        //     System.out.println(seats[i].isBooked());
        // }
        // Airport airport = new Airport("Yaro", "YAR", "YaroCity", -10, 10, 10);
        // System.out.println(airport.getGMT());

        Airport a,b,c,d;
        a = new Airport("Yaro", "YAR", "YaroCity", -10, 10, 10);
        b = new Airport("Lagos", "LOS", "LagosCity", 10, 10, 10);
        c = new Airport("Ary", "ARY", "AryCity", 0, 10, 10);
        d = new Airport("Chatzo", "CHT", "ChatzoCity", 10, 10, 10);

        Flight flight1 = new Flight("F1", 1, a, b, plane1, LocalDateTime.now(), LocalDateTime.now().plusHours(2));

        Flight flight2 = new Flight("F2", 2, b, c, plane2, LocalDateTime.now().plusHours(28).plusMinutes(31), LocalDateTime.now().plusHours(30));

        Flight flight3 = new Flight("F3", 3, c, d, plane3, LocalDateTime.now().plusHours(5), LocalDateTime.now().plusHours(7));

        Flight flight4 = new Flight("F4", 4, a, c, plane4, LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        FlightMap map = new FlightMap();
        map.addFlight(flight1);
        map.addFlight(flight2);
        map.addFlight(flight3);
        map.addFlight(flight4);
        ArrayList<ArrayList<Flight>> pathList = map.getFlightsFromAToB(a, c, LocalDate.of(2025, 1,5));
        for (ArrayList<Flight> path : pathList) {
            Reservation reservation = new Reservation(path, "User");
            System.out.println(reservation);
            for (Flight flight : path) {
                System.out.println(flight.getCode());
            }
            System.out.println();
        }
        
    }
}
