public class Tester {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Plane plane = new Plane("Boeing 747", "B747", "Boeing", 60);
        Seat[] seats = plane.getSeats();
        plane.setBooked("1A");
        // for (int i = 0; i < seats.length; i++) {
        //     System.out.println(seats[i].getSeatCode());
        //     System.out.println(seats[i].isBooked());
        // }
    }
}
