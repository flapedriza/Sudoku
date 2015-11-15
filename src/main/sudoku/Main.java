package main.sudoku;

public class Main {

    public static void main(String[] args) {
	// write your code here
        SudokuBoard board = new SudokuBoard(9);
        SudokuGenerator gen = new SudokuGenerator(SudokuGenerator.Difficulty.EASY, 9);
        gen.generate(board);
        board.print();
    }
}
