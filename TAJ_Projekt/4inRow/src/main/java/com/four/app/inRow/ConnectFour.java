package com.four.app.inRow;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.IntStream;

public class ConnectFour {

	private static final int rows = 6;
	private static final int columns = 7;
	private static final String empty = " ";
	private static final String RED = "R";
	
	private PrintStream outputChannel;

	private String[][] board = new String[rows][columns];
	private String currentPlayer = RED;

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
    
    public int putDisc(int column) {
        checkColumn(column);
        int row = getDiscInColNum(column);
        checkPositionToPut(row, column);
        board[row][column] = currentPlayer;
        printBoard();
        checkWinner(row, column);
        switchPlayer();
        return row;
    }
    
    private void checkColumn(int column) {
        if (column < 0 || column >= columns){
            throw new RuntimeException(String.format("Invalid column %d", column));
        }
    }
    
    private void checkPositionToPut(int row, int column) {
        if (row == rows)
            throw new RuntimeException(String.format("No more room in column %d", column));
    }
    
    public String getCurrentPlayer() {
        outputChannel.printf("Player %s turn%n", currentPlayer);
        return currentPlayer;
    }
}