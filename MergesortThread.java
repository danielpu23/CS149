
import java.util.concurrent.RecursiveAction;

public class MergesortThread extends RecursiveAction {
	private final int[] array;
    private final int low;
    private final int high;

    MergesortThread(int[] array, int low, int high) {
    	this.array = array;
    	this.low = low;
    	this.high = high;
    }
    @Override
    public void compute() {
    	int size = high - low; 
    	if (size <= 100) {  // if array size is less than 100 then use insertion sort
    		insertionSortRange(array);
	    }
    	else {
    		int mid = low + (size/2); // find the midpoint to split the array
    		MergesortThread left = new MergesortThread(array, low, mid);
    		MergesortThread right = new MergesortThread(array, mid, high);
    		invokeAll(left, right);
    		merge(mid);
    	}
    }
    
    public void merge(int mid) {// merge part of mergesort
    
    	int start = low; // start represents starting index of first array
    	int start2 = mid; // start2 represents starting index of second array
    	
        while (start <= mid && start2 < high) {
            if (array[start] <= array[start2]) {
                start++;
            }
            else {
                int value = array[start2];
                int index = start2;
                while (index != start) {
                    array[index] = array[index - 1];
                    index--;
                }
                array[start] = value;
                start++;
                mid++;
                start2++;
            }
        }
    }
    void insertionSortRange(int arr[]) { // insertion sort algorithm for a certain range
        for (int i = low+1; i < high; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= low && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
    
}
