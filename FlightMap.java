import java.time.LocalDate;
import java.util.ArrayList;

public class FlightMap {
    HashMap<String, Flight> flightMap;
    HashMap<String, ArrayList<Flight>> departureAirportMap;

    FlightMap() {
        flightMap = new HashMap<>();
        departureAirportMap = new HashMap<>();
    }

    public void addFlight(Flight flight) {
        flightMap.put(flight.getCode(), flight);
        ArrayList<Flight> departure = departureAirportMap.get(flight.getDepartureAirport().getCode());
        if (departure != null) {
            departure.add(flight);
        } else {
            departure = new ArrayList<>();
            departure.add(flight);
            departureAirportMap.put(flight.getDepartureAirport().getCode(), departure);
        }
    }

    public Flight getFlight(String code) {
        return flightMap.get(code);
    }

    public void removeFlight(String code) {
        flightMap.remove(code);
    }

    // Using an ArrayList<> that contains an inner ArrayList of Flights that
    // represent the path from Airport a to Airport b
    public ArrayList<ArrayList<Flight>> getFlightsFromAToB(Airport a, Airport b, LocalDate date) {
        ArrayList<ArrayList<Flight>> pathList = new ArrayList<>();
        for (Flight flight : departureAirportMap.get(a.getCode())) {
            if (flight.getPlane().getAvailableSeats() > 0) {
                if (
                    flight.getArrivalAirport().getCode().equals(b.getCode())
                    && flight.getDepartureTime().isBefore(date.atTime(23, 59))
                    ) {
                    ArrayList<Flight> path = new ArrayList<>();
                    path.add(flight);
                    pathList.add(path);
                } else if (
                    flight.getDepartureTime().isBefore(date.atTime(23, 59))) {
                    for (Flight nextFlight : departureAirportMap.get(flight.getArrivalAirport().getCode())) {
                        // Checking if the next flight has b as its destination airport and if the next
                        // flight departs 30 minutes after the current flight arrives
                        if (
                            nextFlight.getArrivalAirport() == b
                            && flight.getArrivalTime().plusMinutes(30).isBefore(nextFlight.getDepartureTime())
                            && nextFlight.getPlane().getAvailableSeats() > 0
                            && nextFlight.getDepartureTime().isBefore(flight.getDepartureTime().plusDays(1))
                            ) {
                            ArrayList<Flight> path = new ArrayList<>();
                            path.add(flight);
                            path.add(nextFlight);
                            pathList.add(path);
                        }
                    }
                }
            }
        }
        return pathList;
    }

    
}