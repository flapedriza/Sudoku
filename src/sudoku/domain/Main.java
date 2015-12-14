package sudoku.domain;

public class Main {

    public static void main(String[] args) {
	// write your code her
        SudokuBoard board = null;
        board = new SudokuBoard(9);
        /*try {
            board.read();
        } catch (InvalidNumberInCellException | OutOfRangeException e) {
            e.printStackTrace();
        }*/
        SudokuGenerator gen = null;
        gen = new SudokuGenerator(SudokuGenerator.Difficulty.EASY, 9);
        gen.generate(board);
        gen.removeCells(board, 25);
        board.print();
        SudokuSolver2 solver = new SudokuSolver2();
        int res = solver.uniqueSolution(board);
        if(res == 0 || res == 1) System.out.print("No ");
        System.out.print("té solució");
        if(res != 0) System.out.println(" única");
        solver.solve(board).print();



    }
}
