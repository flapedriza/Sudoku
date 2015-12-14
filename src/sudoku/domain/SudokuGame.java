package sudoku.domain;

import java.util.TreeSet;

/**
 * Created by Adri on 2/12/15.
 */
public class SudokuGame extends Game {

        private SudokuBoard boardSudoku;
        private SudokuSolver2  sudSolv;

        SudokuGame() {
            boardSudoku = null;
        }

        SudokuGame(String board, int size) {
            boardSudoku = new SudokuBoard(board, size);
        }

        SudokuGame(SudokuBoard board) {
            boardSudoku = board;
        }

        void setBoardSudoku(SudokuBoard board) {
            boardSudoku = board;
        }

        Integer getCellNumber(int i, int j) {
            return boardSudoku.getValueCell(i, j);
        }

        TreeSet<Integer> getCellPossibleNumbers(int i, int j) {
            return boardSudoku.falten(i, j);
        }

        void setCellNumber (int number, int i, int j) {
                boardSudoku.setValueCell(number,i+1, j+1);
        }

        void printBoard() {
            boardSudoku.print();
        }

        String boardToString(){
            return boardSudoku.toString();
        }

        Integer sudokuSolve () {
            sudSolv = new SudokuSolver2();
            boardSudoku = sudSolv.solve(boardSudoku);
            return 1;
        }

        boolean isAllBoardFilled() {
            return boardSudoku.getBuides().isEmpty();
        }
}

