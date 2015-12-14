/*package sudoku.domain;

import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;

import sudoku.data.DataController;

import java.util.ArrayList;
/**
 * Created by Adri on 14/12/15.
 *
public class DomainController {
    private DataController dataController;
    private User User;
    private SudokuMatch match;

    public DomainController() {
        super();
        dataController = new DataController();
        User = new User();
        match = null;
    }

    public boolean correctUserLogin (String username, String password) {
        if (dataController.existUser(username, password)){
            User.username = username;
            User.password = password;
            return true;
        }
        return false;
    }

    public void createUser(String username, String password) {
        try {
            dataController.createUser(username, password);
            System.out.println("User created succesfully");
        } catch (SQLException e) {
            //e.printStackTrace();
            System.err.println("User alredy exists.");
        }
    }
    public void closeProgram() {
        dataController.closeProgram();
    }

    public void changePassword(String password) {
        dataController.changePassword(User.username,password);
        User.password = password;
    }
    public void disableUser() {
        dataController.disableUser(User.username);
    }

    public ArrayList<AbstractMap.SimpleEntry<String, Integer>> getRanking() {
        return dataController.getRanking("10");
    }

    public void logOut() {
        User.username = "";
        User.password = "";
    }

    public int getNumberUsers() {
        return dataController.getNumberUsers();
    }

    public int getNumberBoards() {
        return dataController.getNumberBoards();
    }
    public int getNumberFinishedMatches() {
        return dataController.getNumberFinishedMatches();
    }

    public ArrayList<SimpleEntry<Integer, SimpleEntry<String, Integer>>> getAllBoards() {
        return dataController.getAllBoards();
    }

    public ArrayList<SimpleEntry<Integer, SimpleEntry<String, Integer>>> getFinishedMatches() {
        return dataController.getFinishedMatches(User.username);
    }

    public SimpleEntry<Integer, SimpleEntry<String, Integer>> getFinishedMatch(int option) {
        return dataController.getFinishedMatch(User.username, option);
    }

    public int getNumberFinishedMatches(int id) {
        return dataController.getNumberFinishedMatches(id);
    }

    public SimpleEntry<String, Integer> getBoard(int id) {
        return dataController.getBoard(id);
    }

    public ArrayList<SimpleEntry<String, Integer>> getBoardBestTimes(int id) {
        return dataController.getBoardBestTimes(id);
    }

    public ArrayList<SimpleEntry<Integer, SimpleEntry<String, SimpleEntry<Integer, Integer>>>> getSavedGames() {
        return dataController.getSavedGames(User.username);
    }
}
*/