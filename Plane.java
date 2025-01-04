public class Plane {
    private String name;
    private String code;
    private String airline;
    private Seat[] seats;

    public Plane(String name, String code, String airline, int numberOfSeats) {
        this.name = name;
        this.code = code;
        this.airline = airline;
        setSeats(numberOfSeats);
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getAirline() {
        return airline;
    }

    public Seat[] getSeats() {
        return seats;
    }

    private void setSeats(int capacity) {
        this.seats = new Seat[capacity];
        String seatcode;
        char[] seatrows = {'A', 'B', 'C', 'D', 'E', 'F'};
        for (int i = 0; i < capacity/6; i++) {
            for(int j = 0; j < 6; j++) {
                seatcode = (i+1 + "" + seatrows[j]);
                this.seats[i*6+j] = new Seat(seatcode);
            }

        }
    }

    public void setBooked(String seatCode) {
        int seatIndex = Integer.parseInt(seatCode.substring(0, seatCode.length()-1));
        char seatRow = seatCode.charAt(seatCode.length()-1);
        int seatRowInt = seatRow - 'A';
        int index = (seatIndex-1)*6 + seatRowInt;
        this.seats[index].setBooked(true);
    }
}
