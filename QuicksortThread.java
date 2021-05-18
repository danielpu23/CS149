import java.util.concurrent.RecursiveAction;

public class QuicksortThread extends RecursiveAction{
	  private int[] array;
	  private int low;
	  private int high;
	  
	  public QuicksortThread(int[] array, int low, int high) { // constructor
	    this.array = array;
	    this.low = low;
	    this.high = high;
	  }

	  @Override
	  protected void compute() {
		if (high-low <= 100) {     // use insertion sort if array is small enough
			insertionSortRange(array);
		}
	    if(low < high){ 
	      int pivot = partition(array, low, high); // find the pivot
	      QuicksortThread leftPartition = new QuicksortThread(array,low, pivot);
	      QuicksortThread rightPartition =  new QuicksortThread(array, pivot + 1, high);
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
				  int temp = array[i];
				  array[i] = array[j];
				  array[j] = temp;
	      }
	    }
	    return j;
	  }
	  
	    public void insertionSortRange(int arr[]) { // insertion sort algorithm for a certain range
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

