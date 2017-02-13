import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class CalcTest {

	private Calc test;
	
	@Before
	public void setUp() {
		test = new Calc();
	}
	
	@Test
	public void TestAdd() {
		assertEquals(4, test.add(1, 3));
		assertEquals(4, test.add(7, -3));
		assertEquals(-4, test.add(-1, -3));
		assertEquals(4, test.add(4, 0));		
	}
	@Test
	public void TestSub() {
		assertEquals(4, test.sub(8, 4));
		assertEquals(4, test.sub(2, -2));
		assertEquals(-4, test.sub(-8, -4));
		assertEquals(4, test.sub(4, 0));
	}
	@Test
	public void TestMulti() {
		assertEquals(4, test.multi(2, 2));
		assertEquals(-4, test.multi(2, -2));
		assertEquals(4, test.multi(-2, -2));
		assertEquals(0, test.multi(4, 0));
	}
	@Test
	public void TestDiv() {
		assertEquals(4, test.div(12, 3));
		assertEquals(4, test.div(12, 3));
		assertEquals(4, test.div(-12, -3));
		try{
			assertEquals(4, test.div(12, 0));
		}
		catch(Exception e){
			assertEquals(e.getMessage(), "/ by zero");
		}
	}
	
	@After
	public void tearDown() {
		test = null;
		
	}
}
