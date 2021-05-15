import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class JUnit {
	private MainThread m;
	
	@Before
	public void setup() {
        int [] arr = new int [1113];
		
		for(int i = 0; i< 1113; i++) {
			arr[i] = (int)(Math.random() * (700) + 1);
		}
		 m = new MainThread(arr);
	}
	

	@Test
	public void testArraySize() { // check if array size is expected
	  m.compute();
	  assertEquals(m.getArray().length, 1113);
	}
	
	
    @Test
	public void testArrayOrder() { // check if elements are sorted after executing thread
		m.compute();
		boolean inOrder = true;
		for(int i = 1; i<m.getArray().length;i++) {
			if(m.getArray()[i] < m.getArray()[i-1]) {
				inOrder = false;
			}
		}
		assertTrue(inOrder);	
	}
    
    @Test
    public void testMergeTogether() { // check if two sorted arrays merged together produce the expected result
    	int [] testArray1 = {1,7,12,16};
    	int [] testArray2 = {2, 9, 11, 13};
    	int [] mergedArray = new int[8];
    	int [] expectedArray = {1, 2, 7, 9, 11, 12, 13, 16};
    	m.mergeTogether(testArray1, testArray2, testArray1.length, testArray2.length, mergedArray);
    	boolean arrayEquals = Arrays.equals(mergedArray, expectedArray);
    	assertTrue(arrayEquals);
    }
	
    @Test
    public void testInsertionsort() { // check if insertion sort successfully sorts the array
    	int [] testArray = {1, 8, 9, 5, 12, 3, 4};
    	m.insertionSort(testArray);
    	int [] expectedArray = {1, 3, 4, 5, 8, 9, 12};
    	boolean arrayEquals = Arrays.equals(testArray, expectedArray);
    	assertTrue(arrayEquals);
    }
    
    @Test
    public void testMergesort() { // check if merge sort successfully sorts the array
    	int [] testArray = new int [301];
    	for(int i = 300; i>=0; i--) {
    		testArray[i] = i;
    	}
    	m.mergeSort(testArray, 0, testArray.length-1);
    	int [] expectedArray = new int [301];
    	for(int i = 0; i<301; i++) {
    		expectedArray[i] = i;
    	}
    	boolean arrayEquals = Arrays.equals(testArray, expectedArray);
    	assertTrue(arrayEquals);
    	
    }
    @Test
    public void testQuicksort() { // check if quick sort successfully sorts the array
    	int [] testArray = new int [301];
    	for(int i = 300; i>=0; i--) {
    		testArray[i] = i;
    	}
    	m.quickSort(testArray, 0, testArray.length-1);
    	int [] expectedArray = new int [301];
    	for(int i = 0; i<301; i++) {
    		expectedArray[i] = i;
    	}
    	boolean arrayEquals = Arrays.equals(testArray, expectedArray);
    	assertTrue(arrayEquals);
    	
    }
	
	
}
