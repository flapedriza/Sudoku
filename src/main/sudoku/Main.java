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
        SudokuGenerator gen = new SudokuGenerator(SudokuGenerator.Difficulty.EASY, 9);
        gen.generate(board);
        gen.removeCells(board, 10);
        SudokuBoard solved = SudokuSolver.solve(board);
        board.print();
        solved.print();

    }
}
