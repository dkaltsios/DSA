public class Plane {
    private String name;
    private String code;
    private String airline;
    private Seat[] seats;
    // Using enum since the seat letters are fixed
    private enum SeatLetter {
        A, B, C, D, E, F
    }
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
    public int getAvailableSeats() {
        int available = 0;
        for (int i=0 ; i<seats.length ; i++) {
            if (!seats[i].isBooked()) {
                available++;
            }
        }
        return available;
    }
    private void setSeats(int capacity) {
        this.seats = new Seat[capacity];
        String seatcode;
        for (int i = 0; i < capacity/6; i++) {
            for(int j = 0; j < 6; j++) {
                seatcode = (i+1 + "" + SeatLetter.values()[j]);
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

    public String toString() {
        return "Plane{" +
                "\nname='" + name + '\'' +
                ", \ncode='" + code + '\'' +
                ", \nairline='" + airline + '\'' +
                ", \navailable seats=" + getAvailableSeats() +
                '}';
    }
}