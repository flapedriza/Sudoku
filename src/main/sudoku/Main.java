package main.sudoku;

public class Main {

    public static void main(String[] args) {
	// write your code here
        SudokuBoard board = new SudokuBoard(9);
        SudokuGenerator gen = new SudokuGenerator(SudokuGenerator.Difficulty.EASY,9);
        gen.generate(board);
        board.print();
        gen.removeCells(board, 30);
        SudokuBoard board2 = board.copia();
        board2.setValueCell(1,1,1);
        board.print();


        SudokuSolver solver = new SudokuSolver(board);
        solver.solve().print();
        //gen.generate(board);
        //board.print();
    }
}
