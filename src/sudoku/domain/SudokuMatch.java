package sudoku.domain;

import java.util.AbstractMap;

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
    public boolean Correct() {
        if (game.Correct()) {
            time = (int) (System.currentTimeMillis() - time) / 1000;
            return true;
        }
        return false;
    }
}
