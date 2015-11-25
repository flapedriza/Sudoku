package main.sudoku;

public class Main {

    public static void main(String[] args) {
	// write your code her
        SudokuBoard board = null;
        try {
            board = new SudokuBoard(9);
        } catch (OutOfRangeException e) {
            e.printStackTrace();
        }
        try {
            board.setValueCell(1,1,1);
        } catch (OutOfRangeException e) {
            e.printStackTrace();
        }
        System.out.println(board.buides);
        board.erase(1, 1);
        System.out.println(board.buides);


    }
}
