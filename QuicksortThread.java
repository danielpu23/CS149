import java.util.concurrent.RecursiveAction;
import java.util.Arrays;

public class QuicksortThread extends RecursiveAction{
	  private int[] arr;
	  private int low;
	  private int high;
	  
	  public QuicksortThread(int[] arr, int low, int high) { // constructor
	    this.arr = arr;
	    this.low = low;
	    this.high = high;
	  }

	  @Override
	  protected void compute() {
		if (arr.length <= 100) {     // use Array sort if the array is small enough
			Arrays.sort(arr, low, high);
		}
	    if(low < high){ 
	      int pivot = partition(arr, low, high); // find the pivot
	      QuicksortThread leftPartition = new QuicksortThread(arr,low, pivot);
	      QuicksortThread rightPartition =  new QuicksortThread(arr, pivot + 1, high);
	      invokeAll(leftPartition, rightPartition);
	    }
	  }

	  public int partition(int[] array, int low, int high) { // partition method of quicksort
		  int pivot = array[low]; 
		  int i = low - 1;
		  int j  = high + 1;
		  while (i < j){
			  do {
				  i++;
			  }
			  while (array[i] < pivot);
			  do {
			  	  j--;
			  }
			  while (array[j] > pivot);
			  if (i < j) {
				  int temp = arr[i];
				  arr[i] = arr[j];
				  arr[j] = temp;
	      }
	    }
	    return j;
	  }
 
	}

