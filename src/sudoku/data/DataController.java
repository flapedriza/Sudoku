package sudoku.data;

/**
 * Created by Adri on 14/12/15.
 */
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

public class DataController {
    private Database database;

    public DataController () {
        database = new Database();
        //database.createDatabaseSchema();
    }

    public boolean existUser(String username, String password) {
        return database.existUser(username, password);
    }

    public void createUser(String username, String password) throws SQLException {
        database.createUser(username, password);
    }

    public void closeProgram() {
        database.closeDatabase();
    }
    public void changePassword(String username, String password) {
        database.changePassword(username,password);
    }

    public void disableUser(String username) {
        database.disableUser(username);
    }

    public ArrayList<AbstractMap.SimpleEntry<String, Integer>> getRanking(String limit) {
        return database.ranking(limit);
    }

    public int getNumberUsers() {
        return database.numberUsers();
    }

    public int getNumberBoards() {
        return database.numberBoards();
    }

    public int getNumberFinishedMatches() {
        return database.numberFinishedMatches();
    }

    public int getNumberFinishedMatches(int idBoard) {
        return database.numberFinishedMatches(idBoard);
    }

    public ArrayList<SimpleEntry<Integer, SimpleEntry<String, Integer>>> getAllBoards() {
        return database.allBoards();
    }

    public ArrayList<SimpleEntry<Integer, SimpleEntry<String, Integer>>> getFinishedMatches(String username) {
        return database.finishedMatches(username);
    }

    public SimpleEntry<Integer, SimpleEntry<String, Integer>> getFinishedMatch(String username, int option) {
        return database.finishedMatch(username, option);
    }

    public SimpleEntry<String, Integer> getBoard(int id) {
        return database.getBoardWithId(id);
    }

    public ArrayList<SimpleEntry<String, Integer>> getBoardBestTimes(int id) {
        return database.getBoardBestTimes(id);
    }

    public ArrayList<SimpleEntry<Integer, SimpleEntry<String, SimpleEntry<Integer, Integer>>>> getSavedGames(String username) {
        return database.savedGames(username);
    }

    public void saveBoard(String board, int size) {
        database.saveBoard(board, size);
    }

    public void saveMatch(int time, int ended, int idBoard, String boardSolved, String boardState, String username,
                          int hintsRemaining) {
        database.saveMatch(time,ended,idBoard,boardSolved,boardState,username,hintsRemaining);
    }

    public void saveRecord(String username, int idBoard, int time) {
        database.saveRecord(username, idBoard, time);
    }

    public SimpleEntry<Integer, SimpleEntry<Integer, SimpleEntry<String, SimpleEntry<String, Integer>>>> getSavedMatch(
            int id, String username) {
        SimpleEntry<Integer, SimpleEntry<Integer, SimpleEntry<String, SimpleEntry<String, Integer>>>> result = database.savedMatch(id, username);
        database.deleteSavedMatch(id);
        return result;
    }

    public SimpleEntry<String, String> getSysBoard(int diff) {
        return database.sysBoard(diff);
    }

    public void updateScore(String username) {
        database.updateScore(username);
    }

}