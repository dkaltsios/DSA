public class Seat {
    private String seatCode;
    private boolean isBooked;

    public Seat(String seatCode) {
        this.seatCode = seatCode;
        this.isBooked = false;
    }

    public String getSeatCode() {
        return seatCode;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public String toString() {
        return "Seat{" +
                "\nseatCode='" + seatCode + '\'' +
                ", \nisBooked=" + isBooked +
                '}';
    }
}