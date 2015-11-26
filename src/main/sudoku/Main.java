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
            board.read();
        } catch (InvalidNumberInCellException | OutOfRangeException e) {
            e.printStackTrace();
        }
        SudokuSolver2 solver = new SudokuSolver2();
        solver.solve(board).print();



    }
}
