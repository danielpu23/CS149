
import java.util.concurrent.ForkJoinPool;

public class MainThread {
	private int [] mergeArr;
	private int [] quickArr;
	
	public MainThread(int [] mergeArr, int [] quickArr) {
		this.mergeArr = mergeArr;
		this.quickArr = quickArr;
	}
	
	public void compute() {
		ForkJoinPool forkJoinPool = ForkJoinPool.commonPool(); 
		if(mergeArr.length <= 100) { // if mergesort array is small enough, just use insertion sort
			insertionSort(mergeArr);
		}
		else if(mergeArr.length > 100) { // if mergesort thread is greater than 100, then invoke mergesort thread
			MergesortThread m = new MergesortThread(mergeArr, 0, mergeArr.length);
			forkJoinPool.invoke(m);
		}
		if(quickArr.length<= 100) { // if quicksort array is small enough, just use insertion sort
			insertionSort(quickArr);
		}
		else if (quickArr.length > 100) { // if quicksort array is greater than 100, then invoke quicksort thread
			QuicksortThread q = new QuicksortThread(quickArr, 0, quickArr.length-1);
			forkJoinPool.invoke(q);
		}
	}
	
	public void insertionSort(int arr[]) { // insertion sort algorithm
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
		
	public void printArray(int [] arr) {  // print the array
		
		for(int i = 0; i<arr.length; i++) {
			if(i % 10 == 0) {
				System.out.print("\n");
			}
			System.out.print(arr[i]+" ");
		}
	}

	public int [] getMergeArr() { // getters
		return mergeArr;
	}
	public int [] getQuickArr() {
		return quickArr;
	}

	public static void main(String [] args) {
		int [] arr = {1, 8, 4, 7}; // control
		System.out.print("First Array Before Sorting Contains: ");
		int [] arrCopy =  {1, 8, 4, 7}; 
		MainThread t1 = new MainThread(arr, arrCopy);
		t1.printArray(arr);
		System.out.println();
		System.out.print("First Array After Sorting Using Mergesort Contains: ");
		t1.compute();
		t1.printArray(arr);
		System.out.println();
		System.out.print("First Array After Sorting Using Quicksort Contains: ");
		t1.printArray(arrCopy);
		int [] arr2 = new int [1113];
		int [] arr2Copy = new int [1113];
		System.out.println();
		
		for(int i = 0; i< 1113; i++) {
			arr2[i] = (int)(Math.random() * (700) + 1); // generate a random number between 1 and 700
		}
		System.arraycopy(arr2, 0, arr2Copy, 0, arr2.length);
		MainThread t2 = new MainThread(arr2, arr2Copy);
		System.out.print("Second Array Before Sorting Contains: ");
		System.out.println();
		t2.printArray(arr2);
		t2.compute();
		System.out.println();
		System.out.println();
		System.out.println("Second Array After Sorting Using Mergesort Contains: ");
		t2.printArray(arr2);
		System.out.println();
		System.out.println();
		System.out.println("Second Array After Sorting Using Quicksort Contains: ");
		t2.printArray(arr2Copy);
		
	}
	
}
