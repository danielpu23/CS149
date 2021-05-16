import java.util.Arrays;
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

    public void compute() {
    	int size = high - low;
    	if (size <= 100) {
    		Arrays.sort(array, low, high);
	    }
    	else {
    		int mid = low + (size/2);
    		MergesortThread left = new MergesortThread(array, low, mid);
    		MergesortThread right = new MergesortThread(array, mid, high);
    		invokeAll(left, right);
    		merge(mid);
    	}
    }

    public void merge(int mid) { // merge part of mergesort
    	int lowerMid = high - low;
    	int higherMid = mid - low;
    	int[] copy = new int[lowerMid];
    	System.arraycopy(array, low, copy, 0, copy.length);
    	int j = 0;
    	int k = higherMid;
    	for (int i = low; i < high; i++) {
    		if (k >= lowerMid || (j < higherMid && copy[j] < copy[k])) {
    			array[i] = copy[j++];
	    }
    		else {
    			array[i] = copy[k++];
	    }
	  }
    }
       
}
