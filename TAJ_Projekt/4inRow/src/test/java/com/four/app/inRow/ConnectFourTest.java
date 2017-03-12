package com.four.app.inRow;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.isEmptyString;

public class ConnectFourTest {
	
    @Rule
    public ExpectedException exception = ExpectedException.none();
	
	private ConnectFour tested;
	private OutputStream output;

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

    @Test
    public void insertedDiscIncDiscNumTest() {
        int column = 1;
        tested.putDisc(column);
        assertThat(tested.getDiscNum(), is(1));
    }
    
    @Test
    public void fullColumnTest() {
        int column = 1;
        int maxVDiscNum = 6;
        for (int times = 0; times < maxVDiscNum; ++times) {
            tested.putDisc(column);
        }
        exception.expect(RuntimeException.class);
        exception.expectMessage("Column " + column + " is full");
        tested.putDisc(column);
    }
    
    @Test
    public void firstPlayerRedTest() {
        assertThat(tested.getCurrentPlayer(), is("R"));
    }
    
    @Test
    public void secondPlayerGreenTest() {
        int column = 1;
        tested.putDisc(column);
        assertThat(tested.getCurrentPlayer(), is("G"));
    }
    
    @Test
    public void playerTurnTest() {
        tested.getCurrentPlayer();
        assertThat(output.toString(), containsString("Player R turn"));
    }
    
    @Test
    public void discPutPrintBoardTest() {
        int column = 0;
        tested.putDisc(column);
        assertThat(output.toString(), containsString("|R| | | | | | |"));
    }
    
    @Test
    public void boardFullGameEndsTest() {
        for (int row = 0; row < 6; row++){
            for (int column = 0; column < 7; column++){
                tested.putDisc(column);
            }
        }
        assertTrue("Game must end", tested.isFinished());
    }
    
    @Test
    public void 4inColWinnerTest() {
        for (int row = 0; row < 3; row++) {
            tested.putDisc(1);
            tested.putDisc(2);
        }
        assertThat(tested.getWinner(), isEmptyString());
        tested.putDisc(1);
        assertThat(tested.getWinner(), is("R"));
    }
}
