package sudoku.domain;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Random;

/**
 * Created by Adri on 2/12/15.
 */
public class SudokuMatch extends Match {

    private SudokuBoard board;
    private SudokuGame game;
    public SudokuMatch(int GameId, int difficult) {
        super(GameId, difficult);
        time = (int) System.currentTimeMillis();
        hints = 10;
        GameId = 0;
    }
    public SudokuMatch(SudokuMatch match){
        time = match.time;
        hints = match.hints;
        GameId = match.GameId;
        board = match.board;
    }
    public int getGameId() {return GameId;}
    public void setGameId(int GameId) {this.GameId = GameId;}
    SudokuBoard getBoard() {return board;}
    public void setBoard(SudokuBoard board) {
        this.board = board;
    }
    SudokuGame getGame() {return SudokuGame;}
    public void setGame(SudokuGame game) {
        this.game = game;
    }
    AbstractMap.SimpleEntry<String, Pair> getInfo() {
        return new SimpleEntry<String, Pair> (game.toString(), new Pair(((int) (System.currentTimeMillis()- time) /1000), hints));
    }
    public void setValueCell(int val, int col, int row){
        game.setValueCell(val,col,row);
    }
    public void setCellNumber(int i, int j, int val) {
        return SudokuGame.getCellPossibleNumbers(i,j);
    }
    SimpleEntry<Integer, Integer> setRandomCell() {
        SimpleEntry<Integer, Integer> cellFilled = null;
        if (!SudokuGame.isAllBoardFilled() && hints >= 1) {
            hints--;
            Random r = new Random(System.nanoTime());
            int lineStart = r.nextInt(board.getSudokuSize()*board.getSudokuSize());
            int columnStart = r.nextInt(board.getSudokuSize()*board.getSudokuSize());
            boolean inserted = false;
            while(!inserted) {
                if (SudokuGame.getCellNumber(lineStart, columnStart) == 0) {
                    SudokuGame.setCellNumber(lineStart, columnStart, board.getCellNumber(lineStart, columnStart));
                    cellFilled = new SimpleEntry<Integer, Integer>(lineStart, columnStart);
                    inserted = true;
                }
                else {
                    if (columnStart == 8) {
                        if (lineStart == 8) {
                            lineStart = 0;
                            columnStart = 0;
                        }
                        else {
                            lineStart++;
                            columnStart = 0;
                        }
                    }
                    else
                        columnStart++;
                }
            }
        }
        if (cellFilled == null)
            cellFilled = new SimpleEntry<Integer, Integer>(-1, -1);
        return cellFilled;
    }
    public boolean Correct() {
        if (game.Correct()) {
            time = (int) (System.currentTimeMillis() - time) / 1000;
            return true;
        }
        return false;
    }
}
