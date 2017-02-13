import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class NWDTest {

	private NWD test;

	@Before
	public void setUp() {
		test = new NWD();
	}
	
	@Test
	public void Test() {
		assertEquals(0, test.NWD(0, 0));
		assertEquals(2, test.NWD(2, 8));
		assertEquals(8, test.NWD(-2, 8));
		assertEquals(-2, test.NWD(-2, -8));
		
	}
	
	@After
	public void tearDown() {
		test = null;
		
	}
}
