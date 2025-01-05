import java.util.ArrayList;

public class DynamicFlightPricingSystem {
    public static void main(String[] args) {

    }

    public static void sort(ArrayList<ArrayList<Flight>> flightList) {
        quickSort(flightList);
        return;
    }

    public static int averageOfConnectingFlights(ArrayList<Flight> flights) {
        int sum = 0;
        int seats = 0;
        for (int i = 0; i < flights.size() - 1; i++) {
            sum += flights.get(i).getPlane().getAvailableSeats();
            seats += flights.get(i).getPlane().getSeats().length;
        }
        return sum / seats;
    }

    public static void quickSort(ArrayList<ArrayList<Flight>> theArray) {
        quickSort(theArray, 0, theArray.size() - 1);
    }

    private static int median3(ArrayList<ArrayList<Flight>> theArray, int left, int right) {

        // The complexity of this method is O(1)
        // For its placement within the algorithm, we place it in the "partition"
        // method, where we actually pick the pivot.

        int center = (left + right) / 2;

        if (averageOfConnectingFlights(theArray.get(left)) > averageOfConnectingFlights(theArray.get(center)))
            swapValues(theArray, left, center);

        if (averageOfConnectingFlights(theArray.get(left)) > averageOfConnectingFlights(theArray.get(right)))
            swapValues(theArray, left, right);

        if (averageOfConnectingFlights(theArray.get(center)) > averageOfConnectingFlights(theArray.get(right)))
            swapValues(theArray, center, right);

        swapValues(theArray, center, right - 1);
        return averageOfConnectingFlights(theArray.get(right - 1));
    }

    private static final void swapValues(ArrayList<ArrayList<Flight>> a, int index1, int index2) {
        ArrayList<Flight> tmp = a.get(index1);
        a.set(index1, a.get(index2));
        a.set(index2, tmp);
    }

    private static void quickSort(ArrayList<ArrayList<Flight>> theArray, int low, int high) {
        int pivot_index;
        if (low < high) {
            pivot_index = partition(theArray, low, high);
            quickSort(theArray, low, pivot_index - 1);
            quickSort(theArray, pivot_index + 1, high);
        }
    }

    private static int partition(ArrayList<ArrayList<Flight>> theArray, int low, int high) {
        int left = low;
        int right = high;

        // Exercise 2b
        int pivot = median3(theArray, low, high); // was: theArray[ low ];

        while (left < right) {
            while ((left < high) && (averageOfConnectingFlights(theArray.get(left)) <= pivot))
                left++;

            while (averageOfConnectingFlights(theArray.get(right)) > pivot)
                right--;

            if (left < right)
                swapValues(theArray, left, right);
        }

        swapValues(theArray, low, right);
        return right;
    }
}