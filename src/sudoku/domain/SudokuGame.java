package sudoku.domain;

import java.util.ArrayList;

/**
 * Created by Adri on 2/12/15.
 */
public class SudokuGame extends Game {

        private SudokuBoard boardSudoku;

        SudokuGame() {
            boardSudoku = null;
        }

        SudokuGame(String board,int size) {
            boardSudoku = new SudokuBoard(board, size);
        }

        SudokuGame(SudokuBoard board) {
            boardSudoku = board;
        }

        void setBoardSudoku(SudokuBoard board) {
            boardSudoku = board;
        }

        Integer getCellNumber(int i, int j) {
            return boardSudoku.getCellNumber(i, j);
        }

        ArrayList<Integer> getCellPossibleNumbers(int i, int j) {
            return boardSudoku.getCellPossibleNumbers(i, j);
        }

        void setCellNumber (int i, int j, int number) {
            boardSudoku.setCellNumber(i, j, number);
        }

        boolean isCorrectSudoku() {
            return boardSudoku.isBoardCorrect();
        }

        void printBoard() {
            boardSudoku.print();
        }

        String boardToString(){
            return boardSudoku.boardToString();
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
