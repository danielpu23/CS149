import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class JUnit {
	private MainThread m;
	
	@Before
	public void setup() { // setup  main thread before executing tests
        int [] mergeArr = new int [1113];
		
		for(int i = 0; i< 1113; i++) {
			mergeArr[i] = (int)(Math.random() * (700) + 1);
		}
		int [] quickArr = new int [1113];
		System.arraycopy(mergeArr, 0, quickArr, 0, quickArr.length);
		 m = new MainThread(mergeArr, quickArr);
	}
		
    // Mergesort Thread tests
    @Test
    public void testMerge() { // check if mergesort's merge function works as expected
    	int [] testArray = {4, 6, 1, 3, 8};
    	int [] expectedArray = {1, 3, 4, 6, 8};
    	MergesortThread merge = new MergesortThread(testArray, 0, testArray.length);
    	merge.merge(testArray.length/2);
    	boolean arrayEquals = Arrays.equals(testArray, expectedArray);
    	assertTrue(arrayEquals);
    }
    
    @Test
    public void testMergesort() { // check if mergesort successfully sorts the array
    	int [] testArray = new int [301];
    	for(int i = 300; i>=0; i--) {
    		testArray[i] = i;
    	}
    	MergesortThread merge = new MergesortThread(testArray, 0, testArray.length);
    	merge.compute();
    	int [] expectedArray = new int [301];
    	for(int i = 0; i<301; i++) {
    		expectedArray[i] = i;
    	}
    	boolean arrayEquals = Arrays.equals(testArray, expectedArray);
    	assertTrue(arrayEquals);   	
    }
    
    // Quicksort Thread tests    
    @Test 
    public void testPartition() { // check if quicksort's partition is the correct number and outputs the expected changes to the array
    	int [] testArray = {4, 6, 1, 3, 8};
    	int [] expectedArray = {3, 1, 6, 4, 8};
    	QuicksortThread quick = new QuicksortThread(testArray, 0, testArray.length-1);
    	int pivot = quick.partition(testArray, 0, testArray.length-1);
    	int expectedPivot = 1;
    	boolean arrayEquals = Arrays.equals(testArray, expectedArray);
    	assertTrue(pivot == expectedPivot && arrayEquals );
    }
    @Test
    public void testQuicksort() { // check if quicksort successfully sorts the array
    	int [] testArray = new int [301];
    	for(int i = 300; i>=0; i--) {
    		testArray[i] = i;
    	}
    	QuicksortThread quick = new QuicksortThread(testArray, 0, testArray.length-1);
    	quick.compute();
    	int [] expectedArray = new int [301];
    	for(int i = 0; i<301; i++) {
    		expectedArray[i] = i;
    	}
    	boolean arrayEquals = Arrays.equals(testArray, expectedArray);
    	assertTrue(arrayEquals);    	
    }
    // Test the ranged insertion sort for both Mergesort and Quicksort threads (they are exactly the same)
    @Test
    public void testInsertionsortRange() {
    	int [] testArray = {3, 1, 8, 2, 9, 4};
    	int [] testArrayCopy = {3, 1, 8, 2, 9, 4};
    	int [] expectedArray = {1, 3, 8, 2, 9, 4};
    	MergesortThread merge = new MergesortThread(testArray, 0, 2); // only test a small range for both
    	QuicksortThread quick = new QuicksortThread(testArrayCopy, 0, 2);
    	merge.insertionSortRange(testArray);
    	quick.insertionSortRange(testArrayCopy);
    	boolean arrayEquals = Arrays.equals(testArray, expectedArray);
    	boolean arrayEquals2 = Arrays.equals(testArrayCopy, expectedArray);
    	assertTrue(arrayEquals && arrayEquals2);
    }
    
    // Main Thread tests
    @Test
    public void testInsertionsort() {
    	int [] testArray = {1, 2, 8, 4, 3, 9};
    	int [] expectedArray = {1, 2, 3, 4, 8, 9};
    	m.insertionSort(testArray);
    	boolean arrayEquals = Arrays.equals(testArray, expectedArray);
    	assertTrue(arrayEquals);   	
    }
    @Test
  	public void testMainMergesort() { // check if elements are sorted by mergesort after executing thread
  		m.compute();
  		boolean inOrder = true;
  		for(int i = 1; i<m.getMergeArr().length;i++) {
  			if(m.getMergeArr()[i] < m.getMergeArr()[i-1]) {
  				inOrder = false;
  			}
  		}
  		assertTrue(inOrder);	
  	}
      
      @Test
      public void testMainQuicksort() { // check if elements are sorted by quicksort after executing thread
      	m.compute();
      	boolean inOrder = true;
      	for(int i = 1; i<m.getQuickArr().length;i++) {
  			if(m.getQuickArr()[i] < m.getQuickArr()[i-1]) {
  				inOrder = false;
  			}
  		}
      	assertTrue(inOrder);	
      }
}
