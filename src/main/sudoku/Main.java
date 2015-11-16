package main.sudoku;

public class Main {

    public static void main(String[] args) {
	// write your code here
        SudokuBoard board = new SudokuBoard(16);
        SudokuGenerator gen = new SudokuGenerator(SudokuGenerator.Difficulty.EASY, 16);
        gen.generate(board);
        //if(board.setValueCell(3, 8, 2)) System.out.println("GOOD");
        //if(board.setValueCell(3, 1, 1)) System.out.println("HEY!");
        board.print();
    }
}
