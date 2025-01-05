import java.util.ArrayList;
public class Reservation {
    private ArrayList<Flight> flight;
    private ArrayList<Seat> seat;
    private String username;
    private String code;

    public Reservation(ArrayList<Flight> flight, String username) {
        this.username = username;
        this.flight = flight;
        this.seat = new ArrayList<>();
        this.code = createCode();
        for(Flight f : flight) {
            setSeat(f);
        }
    }
    public ArrayList<Flight> getFlight() {
        return flight;
    }
    public String getUsername() {
        return username;
    }
    public ArrayList<Seat> getSeat() {
        return seat;
    }
    public void setSeat(Flight flight) {
        for(Seat seat : flight.getPlane().getSeats()) {
            if(!seat.isBooked()) {
                flight.getPlane().setBooked(seat.getSeatCode());
                this.seat.add(seat);
                return;
            }
        }
    }
    public String getCode() {
        return code;
    }
    public String createCode(){
        String code = username;
        for(Flight f : flight) {
            code += f.getCode();
        }
        return code;
    }
    public String toString() {
        return "Reservation{" +
                "flight=" + flight +
                ", username='" + username + '\'' +
                ", seat=" + seat +
                '}';
    }
}
