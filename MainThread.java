
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class MainThread extends RecursiveAction {
	private int [] arr;
	private static final int maxLength = 100;
	
	public MainThread(int [] arr) {
		this.arr = arr;
	}
	
	public static void main(String [] args) {
		int [] arr = {1, 8, 4, 7}; // control
		System.out.print("First Array Before Sorting Contains: ");
		MainThread t1 = new MainThread(arr);
		t1.printArray(arr);
		System.out.println();
		System.out.print("First Array After Sorting Contains: ");
		t1.compute();
		t1.printArray(arr);
		System.out.println();
		int [] arr2 = new int [1113];
		
		for(int i = 0; i< 1113; i++) {
			arr2[i] = (int)(Math.random() * (700) + 1); // generate a random number between 1 and 700
		}
		MainThread t2 = new MainThread(arr2);
		System.out.print("Second Array Before Sorting Contains: ");
		System.out.println();
		t2.printArray(arr2);
		t2.compute();
		System.out.println();
		System.out.print("Second Array After Sorting Contains: ");
		t2.printArray(arr2);
		
	}
	
	public int [] getArray() {
		return arr;
	}
	
	public void printArray(int [] arr) {
	
		for(int i = 0; i<arr.length; i++) {
			if(i % 10 == 0) {
				System.out.print("\n");
			}
			System.out.print(arr[i]+" ");
		}
	}

	@Override
	protected void compute() {
		if(arr.length > maxLength) {  // if array size greater than 100, split up the array
		        int [] subArrayOne = new int [(arr.length+1)/2]; // Mergesort array
		        int [] subArrayTwo = new int [arr.length-subArrayOne.length]; // Quicksort array
		        
		        System.arraycopy(arr, 0, subArrayOne, 0, subArrayOne.length);  // copy first half
		        System.arraycopy(arr, subArrayOne.length, subArrayTwo, 0, subArrayTwo.length); // copy second half
		      
		        MainThread msort = new MainThread(subArrayOne); // make 2 separate threads for both halves
		        MainThread qsort = new MainThread(subArrayTwo);
		        msort.mergeSort(msort.arr, 0, msort.arr.length-1); // Mergesort the first thread
			    qsort.quickSort(qsort.arr, 0, qsort.arr.length-1); // Quicksort the second thread
			    mergeTogether(msort.arr, qsort.arr, msort.arr.length, qsort.arr.length, arr); // merge the two threads' arrays together before forking
		        ForkJoinTask.invokeAll(msort, qsort); 	// fork threads  
		}
		else {
			insertionSort(arr); // if array size is small enough, can just use insertion sort
		}
		
	}
	
	public void mergeTogether(int [] arr1, int [] arr2, int length1, int length2, int [] arr) {  // merges 2 sorted arrays together
		 int i = 0, j = 0, k = 0;    
	        while (i<length1 && j <length2) 
	        {
	            if (arr1[i] < arr2[j])
	                arr[k++] = arr1[i++];
	            else
	                arr[k++] = arr2[j++];
	        }
	     
	        while (i < length1)
	            arr[k++] = arr1[i++];
	     
	        while (j < length2)
	            arr[k++] = arr2[j++];
	}
	
	public void insertionSort(int arr[]) // insertion sort algorithm
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
	public void mergeSort( int[] arr, int left, int right) { // mergesort algorithm
		if(arr.length <= maxLength) {
			insertionSort(arr);
			return;		
		}
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSort(arr, left, mid );
			mergeSort(arr, mid+1, right);
			merge(arr, left, right, mid);
		}
	}

	public void merge(int[] arr, int left, int right, int mid) { // merge part of mergesort, helper method
		int leftSize = mid - left + 1;
		int rightSize = right - mid;
		int[] leftArr = new int[leftSize];
		int[] rightArr = new int[rightSize];
		for (int i = 0; i < leftSize; i++) {
			leftArr[i] = arr[i + left];
		}
		for (int j = 0; j < rightSize; j++) {
			rightArr[j] = arr[j + mid + 1];
		}
		int leftIndex = 0;
		int rightIndex = 0;
		int currentIndex = left;
		while (leftIndex < leftSize && rightIndex < rightSize) {
			if(leftArr[leftIndex]< rightArr[rightIndex]) {
				arr[currentIndex] = leftArr[leftIndex++];
			}
			else {
				arr[currentIndex] = rightArr[rightIndex++];
			}
			currentIndex++;
		}
		while(leftIndex<leftSize) {
			arr[currentIndex] = leftArr[leftIndex++];
			currentIndex++;
		}
		while(rightIndex<rightSize) {
			arr[currentIndex] = rightArr[rightIndex++];
			currentIndex++;
		}
	}
	
	public void quickSort(int[] arr, int low, int high) { // quicksort algorithm
		if (low == high) {
			return;
		}
		if(arr.length <= maxLength) {
			insertionSort(arr);
			return;		
		}
		if (low < high) {
			int partitionIndex = partition(arr, low, high);
			 quickSort(arr, low, partitionIndex - 1);
			 quickSort(arr, partitionIndex + 1, high);
		}
	}

	public int partition(int[] arr, int low, int high) { // partition part of quicksort, helper method
		int pivot = arr[high];
		int i = low - 1;
		for (int j = low; j <= high; j++) {
			if (arr[j] <= pivot) {
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		return i;
	}

}
