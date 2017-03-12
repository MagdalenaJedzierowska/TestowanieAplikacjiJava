package com.four.app.inRow;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ConnectFourTest {
	
    @Rule
    public ExpectedException exception = ExpectedException.none();
	
	private ConnectFour tested;

	@Before
	public void setUp() {
		tested = new ConnectFour();
	}

	@Test
	public void boardEmptyTest(){
		assertThat(tested.getDiscNum(), is(0));
	}

	@Test
	public void discOutOfBoardTest(){
		int column = -1;
		exception.expect(RuntimeException.class);
		exception.expectMessage("Invalid column " + column);
		tested.putDisc(column);
	}

	@Test
	public void firstDiscZeroPositionTest(){
		int column = 1;
		assertThat(tested.putDisc(column), is(0));
	}

}
