package sudoku.domain;

public class Main {

    public static void main(String[] args) {
	// write your code her
        SudokuBoard board = null;
        try {
            board = new SudokuBoard(9);
        } catch (OutOfRangeException e) {
            e.printStackTrace();
        }
        /*try {
            board.read();
        } catch (InvalidNumberInCellException | OutOfRangeException e) {
            e.printStackTrace();
        }*/
        SudokuGenerator gen = null;
        try {
            gen = new SudokuGenerator(SudokuGenerator.Difficulty.EASY, 9);
        } catch (OutOfRangeException e) {
            e.printStackTrace();
        }
        gen.generate(board);
        try {
            gen.removeCells(board, 25);
        } catch (OutOfRangeException e) {
            e.printStackTrace();
        }
        board.print();
        SudokuSolver2 solver = new SudokuSolver2();
        int res = solver.uniqueSolution(board);
        if(res == 0 || res == 1) System.out.print("No ");
        System.out.print("té solució");
        if(res != 0) System.out.println(" única");
        solver.solve(board).print();



    }
}
