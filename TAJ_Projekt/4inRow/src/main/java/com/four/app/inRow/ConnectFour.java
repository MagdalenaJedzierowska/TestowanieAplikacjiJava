package com.four.app.inRow;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ConnectFour {

	private static final int rows = 6;
	private static final int columns = 7;
	private static final String empty = " ";
	private String[][] board = new String[rows][columns];
	
	private static final int win_cond = 4;
	
	private static final String red = "R";
	private static final String green = "G";
	private String currentPlayer = red;
	
	private String winner = "";
	
	private static final String delimit = "|";
	
	private PrintStream outputChannel;

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
            throw new RuntimeException(String.format("Column %d is full", column));
    }
    
    public String getCurrentPlayer() {
        outputChannel.printf("Player %s turn%n", currentPlayer);
        return currentPlayer;
    }
    
    private void printBoard() {
        for (int row = rows - 1; row >= 0; row--) {
            StringJoiner stringJoiner = new StringJoiner(delimit, delimit, delimit);
            Stream.of(board[row]).forEachOrdered(stringJoiner::add);
            outputChannel.println(stringJoiner.toString());
        }
    }
    
    private void checkWinner(int row, int column) {
        if (winner.isEmpty()) {
            String colour = board[row][column];
            Pattern winPattern = Pattern.compile(".*" + colour + "{" + win_cond + "}.*");
 
            String vertical = IntStream.range(0, rows)
                    .mapToObj(r -> board[r][column])
                    .reduce(String::concat).get();
            if (winPattern.matcher(vertical).matches())
                winner = colour;
 
            if (winner.isEmpty()) {
                String horizontal = Stream.of(board[row]).reduce(String::concat).get();
                if (winPattern.matcher(horizontal).matches())
                    winner = colour;
            }
 
            if (winner.isEmpty()) {
                int startOffset = Math.min(column, row);
                int myColumn = column - startOffset, myRow = row - startOffset;
                StringJoiner stringJoiner = new StringJoiner("");
                do {
                    stringJoiner.add(board[myRow++][myColumn++]);
                } while (myColumn < columns && myRow < rows);
                if (winPattern.matcher(stringJoiner.toString()).matches())
                    winner = currentPlayer;
            }
 
            if (winner.isEmpty()) {
                int startOffset = Math.min(column, rows - 1 - row);
                int myColumn = column - startOffset, myRow = row + startOffset;
                StringJoiner stringJoiner = new StringJoiner("");
                do {
                    stringJoiner.add(board[myRow--][myColumn++]);
                } while (myColumn < columns && myRow >= 0);
                if (winPattern.matcher(stringJoiner.toString()).matches())
                    winner = currentPlayer;
            }
        }
    }
    
    private void switchPlayer() {
        if (red.equals(currentPlayer))
            currentPlayer = green;
        else currentPlayer = red;
    }
    
    public boolean isFinished() {
        return getDiscNum() == rows * columns;
    }
 
    public String getWinner() {
        return winner;
    }
}