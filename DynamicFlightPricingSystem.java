import java.util.ArrayList;

public class DynamicFlightPricingSystem {
    public static void main(String[] args) {

    }

    public ArrayList<ArrayList<Flight>> sort(ArrayList<ArrayList<Flight>> flightList) {
        for (ArayList<Flight> flight : flightList) {

        }
        return null;
    }

    public float averageOfConnectingFlights(ArrayList<Flight> flights) {
        int sum = 0;
        int seats = 0;
        for (int i = 0; i < flights.size() - 1; i++) {
            sum += flights.get(i).getPlane().getAvailableSeats();
            seats += flights.get(i).getPlane().getSeats().length;
        }
        return sum / seats;
    }
}

















































private static int median3(int[] theArray, int left, int right) {

    // The complexity of this method is O(1)
    // For its placement within the algorithm, we place it in the "partition"
    // method, where we actually pick the pivot.

    int center = (left + right) / 2;

    if (theArray[left] > theArray[center])
        swapValues(theArray, left, center);

    if (theArray[left] > theArray[right])
        swapValues(theArray, left, right);

    if (theArray[center] > theArray[right])
        swapValues(theArray, center, right);

    swapValues(theArray, center, right - 1);
    return theArray[right - 1];
}

private static final void swapValues(int[] a, int index1, int index2) {
    int tmp = a[index1];
    a[index1] = a[index2];
    a[index2] = tmp;
}

private static void quickSort(int[] theArray, int low, int high) {
    int pivot_index;
    if (low < high) {
        pivot_index = partition(theArray, low, high);
        quickSort(theArray, low, pivot_index - 1);
        quickSort(theArray, pivot_index + 1, high);
    }
}

private static int partition(int[] theArray, int low, int high) {
    int left = low;
    int right = high;

    // Exercise 2b
    int pivot = median3(theArray, low, high); // was: theArray[ low ];

    while (left < right) {
        while ((left < high) && (theArray[left] <= pivot))
            left++;

        while (theArray[right] > pivot)
            right--;

        if (left < right)
            swapValues(theArray, left, right);
    }

    swapValues(theArray, low, right);
    return right;
}