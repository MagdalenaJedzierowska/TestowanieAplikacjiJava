package TAJ_Lab3.BinSearch;

import static org.junit.Assert.*;

import org.junit.Test;

public class AppTest 
{
	
	@Test
	public void binPositiveTest(){
		App bin = new App();
		int [] A = {1, 8, 9, 88, 26, 35, 49, 63, 22, 14, 57};
		assertEquals(2, bin.binSearch(9, A, 0, A.length));
	}
	@Test
	public void binNegativeTest(){
		App bin = new App();
		int [] A = {1, 8, 9, -3, 26, 35, 49, 63, 22, 14, 57};
		assertEquals(-1, bin.binSearch(-3, A, 0, A.length));
	}

	@Test
	public void binPosandNegTest(){
		App bin = new App();
		int [] A = {1, 8, 9, -3, 26, 35, -8, 63, 22, 14, 57};
		assertEquals(5, bin.binSearch(35, A, 0, A.length));
	}
   
	@Test
	public void binNullTest(){
		App bin = new App();
		int [] A = {};
		assertEquals(-1, bin.binSearch(35, A, 0, A.length));
	}
}
