package sudoku.domain;

import java.util.TreeSet;

/**
 * Created by Adri on 2/12/15.
 */
public class SudokuGame extends Game {

        private SudokuBoard boardSudoku;

        SudokuGame() {
            boardSudoku = null;
        }

        SudokuGame(SudokuBoard board, int size) {
            try {
                boardSudoku = new SudokuBoard(board);
            } catch (OutOfRangeException e) {
                e.printStackTrace();
            }
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
            try {
                return boardSudoku.falten(i, j);
            } catch (OutOfRangeException e) {
                e.printStackTrace();
            }
        }

        void setCellNumber (int i, int j, int number) {
            try {
                boardSudoku.setValueCell(number,i, j);
            } catch (OutOfRangeException e) {
                e.printStackTrace();
            }
        }

        boolean isCorrectSudoku() {
            return boardSudoku.isBoardCorrect();
        }

        void printBoard() {
            boardSudoku.print();
        }

        String boardToString(){
            return boardSudoku.toString();
        }

        void partialSolve() {
            SudokuSolver2.nonTrivialSudokuBoard(boardSudoku);
        }

        Integer sudokuSolve () {
            return (int) SudokuSolver2.runSolver(boardSudoku);
        }

        boolean isAllBoardFilled() {
            return boardSudoku.isAllBoardFilled();
        }
    }


}
