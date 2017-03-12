package com.four.app.inRow;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ConnectFour {

	private static final int rows = 6;
	private static final int columns = 7;
	private static final String empty = " ";

	private String[][] board = new String[rows][columns];

	public ConnectFour(){
		for(String[] row : board){
			Arrays.fill(row, empty);
		}
	}

	public int getDiscNum() {
		return IntStream.range(0, columns).map(this::getDiscInColNum).sum();
	}
	
    private int getDiscInColNum(int column) {
        return (int) IntStream.range(0, rows)
                .filter(row -> !empty.equals(board[row][column]))
                .count();
    }
}