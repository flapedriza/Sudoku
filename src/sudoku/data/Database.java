package sudoku.data;

/**
 * Created by Francesc on 10/12/2015.
 */

import java.io.*;
import java.sql.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

public class Database {
    private Connection conn = null;

    Database () {
        try {
            //Seleccionem el driver i ens conectem a la bd, en cas de no existir, la crea.
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:sudoku.sqlite");
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //System.out.println("Opened database successfully!!");
    }

    void createDatabaseSchema() {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ArrayList<String> sqlSentences = new ArrayList<>();
            sqlSentences.add("CREATE TABLE IF NOT EXISTS Users (username VARCHAR(15) PRIMARY KEY,"
                    + "password VARCHAR(45), score INTEGER DEFAULT 0, enabled INTEGER DEFAULT 1);");
            sqlSentences.add("CREATE TABLE IF NOT EXISTS Board (idBoard INTEGER PRIMARY KEY ASC,"
                    + "board VARCHAR(1000), size INTEGER);");
            sqlSentences.add("CREATE TABLE IF NOT EXISTS SysBoard (idBoard INTEGER PRIMARY KEY ASC," +
                    "board VARCHAR(1000), boardSolved VARCHAR(1000), size INTEGER);");
            sqlSentences.add("CREATE TABLE IF NOT EXISTS SavedGame (idSavedGame INTEGER PRIMARY KEY ASC," +
                    "timeElapsed INTEGER, ended INTEGER DEFAULT 0, Board_idBoard INTEGER, boardSolved" +
                    "VARCHAR(1000), boardState VARCHAR(1000), Users_username VARCHAR(15), hintsRemaining INTEGER);");
            sqlSentences.add("CREATE TABLE IF NOT EXISTS Record (Users_username VARCHAR(15), Board_idBoard INTEGER,"+
                    "timeElapsed INTEGER,  PRIMARY KEY (Users_username, Board_idBoard));");
            for (int i = 0; i < sqlSentences.size(); i++)
                stmt.executeUpdate(sqlSentences.get(i));

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //System.out.println("Created database schema successfully!!");
    }

    private ArrayList<String> readFile(String filePath) {
        ArrayList<String> result = new ArrayList<String>();
        File file = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            file = new File(filePath);
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String line;
            while((line=br.readLine())!=null) {
                result.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null)
                    fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    void createUser(String username, String password) throws SQLException {
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "INSERT INTO Users (username,password) " +
                "VALUES ('"+username+"', '"+password+"');";
        stmt.executeUpdate(sql);

        if (stmt != null)
            stmt.close();

        conn.commit();

    }

    void changePassword(String username, String password) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "UPDATE Users set password = '" + password + "' where username = '" + username + "';";
            stmt.executeUpdate(sql);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    boolean existUser (String username, String password) {
        boolean result = false;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Users WHERE username = '"+username+"'"
                    + " AND password = '"+password+"' AND enabled = '1';");
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    void saveBoard(String board, int size) {
        Statement stmt = null;

        try {
            stmt = conn.createStatement();

            String sql = "INSERT INTO Board (board,size) " +
                    "VALUES ('"+board+"', '"+size+"');";
            stmt.executeUpdate(sql);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    ArrayList<SimpleEntry<String, Integer>> ranking(String limit) {
        ArrayList<SimpleEntry<String, Integer>> result = new ArrayList<SimpleEntry<String, Integer>>();
        SimpleEntry<String, Integer> userSocre;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT username, score FROM Users ORDER BY score DESC LIMIT "+limit+";");
            while (rs.next()) {
                userSocre = new SimpleEntry<String, Integer>(rs.getString("username"), rs.getInt("score"));
                result.add(userSocre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    ArrayList<SimpleEntry<Integer, SimpleEntry<String, Integer>>> allBoards() {
        ArrayList<SimpleEntry<Integer, SimpleEntry<String, Integer>>> result =
                new ArrayList<SimpleEntry<Integer, SimpleEntry<String, Integer>>>();

        SimpleEntry<Integer, SimpleEntry<String, Integer>> board;


        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Board;");
            while (rs.next()) {
                board = new SimpleEntry<Integer, SimpleEntry<String,Integer>>(rs.getInt("idBoard"),
                        new SimpleEntry<String,Integer>(rs.getString("board"),rs.getInt("size")));
                result.add(board);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    SimpleEntry<String, Integer> getBoardWithId (int id) {
        SimpleEntry<String, Integer> board = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT board,size FROM Board WHERE idBoard = "+id+";");
            if (rs.next()) {
                board = new SimpleEntry<String, Integer>(rs.getString("board"), rs.getInt("size"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return board;
    }

    void closeDatabase() {
        try {
            //System.out.println("Bye!!!!!!!!");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void disableUser(String username) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "UPDATE Users set enabled = 0 where username = '" + username + "';";
            stmt.executeUpdate(sql);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    int numberUsers() {
        int count = 0;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT count(*) AS count FROM Users WHERE enabled = 1;");
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    int numberBoards() {
        int count = 0;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT count(*) AS count FROM Board;");
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    int numberFinishedMatches() {
        int count = 0;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT count(*) AS count FROM Record;");
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    int numberFinishedMatches(int idBoard) {
        int count = 0;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT count(*) AS count FROM Record WHERE Board_idBoard = "+idBoard+";");
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    ArrayList<SimpleEntry<Integer, SimpleEntry<String, Integer>>> finishedMatches(String username) {
        ArrayList<SimpleEntry<Integer, SimpleEntry<String, Integer>>> result =
                new ArrayList<SimpleEntry<Integer, SimpleEntry<String, Integer>>>();

        SimpleEntry<Integer, SimpleEntry<String, Integer>> board;


        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT idSavedGame, timeElapsed, boardSolved FROM SavedGame WHERE ended = 1 AND Users_username = '"+username+"';");
            while (rs.next()) {
                board = new SimpleEntry<Integer, SimpleEntry<String,Integer>>(rs.getInt("idSavedGame"),
                        new SimpleEntry<String,Integer>(rs.getString("boardSolved"),rs.getInt("timeElapsed")));
                result.add(board);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    SimpleEntry<Integer, SimpleEntry<String, Integer>> finishedMatch(String username, int id) {
        SimpleEntry<Integer, SimpleEntry<String, Integer>> result = null;

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT hintsRemaining, timeElapsed, boardSolved FROM SavedGame WHERE ended = 1 AND Users_username = '"+username+"' AND idSavedGame = "+id+";");
            if (rs.next()) {
                result = new SimpleEntry<Integer, SimpleEntry<String,Integer>>(rs.getInt("timeElapsed"),
                        new SimpleEntry<String,Integer>(rs.getString("boardSolved"),rs.getInt("hintsRemaining")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    ArrayList<SimpleEntry<String, Integer>> getBoardBestTimes(int id) {
        ArrayList<SimpleEntry<String, Integer>> result = new ArrayList<SimpleEntry<String, Integer>>();
        SimpleEntry<String, Integer> usertime;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT Users_username, timeElapsed FROM Record WHERE Board_idBoard = "+id+" ORDER BY timeElapsed;");
            while (rs.next()) {
                usertime = new SimpleEntry<String, Integer>(rs.getString("Users_username"), rs.getInt("timeElapsed"));
                result.add(usertime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    ArrayList<SimpleEntry<Integer, SimpleEntry<String, SimpleEntry<Integer, Integer>>>> savedGames(String username) {
        ArrayList<SimpleEntry<Integer, SimpleEntry<String, SimpleEntry<Integer, Integer>>>> result =
                new ArrayList<SimpleEntry<Integer, SimpleEntry<String, SimpleEntry<Integer, Integer>>>>();
        SimpleEntry<Integer, SimpleEntry<String, SimpleEntry<Integer, Integer>>> savedMatch;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT idSavedGame, boardState, timeElapsed, hintsRemaining FROM SavedGame WHERE ended = 0 AND Users_username = '"+username+"';");
            while (rs.next()) {
                savedMatch = new SimpleEntry<Integer, SimpleEntry<String, SimpleEntry<Integer, Integer>>>(rs.getInt("idSavedGame"),
                        new SimpleEntry<String, SimpleEntry<Integer, Integer>>(rs.getString("boardState"),
                                new SimpleEntry<Integer, Integer>(rs.getInt("timeElapsed"),rs.getInt("hintsRemaining"))));
                result.add(savedMatch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    void saveMatch(int time, int ended, int idBoard, String boardSolved, String boardState, String username,
                   int hintsRemaining) {
        Statement stmt = null;

        try {
            stmt = conn.createStatement();

            String sql = "INSERT INTO SavedGame (timeElapsed,ended,Board_idBoard,boardSolved,boardState,Users_username,hintsRemaining) " +
                    "VALUES ("+time+", "+ended+", "+idBoard+", '"+boardSolved+"', '"+boardState+"', '"+username+"', "+hintsRemaining+");";
            stmt.executeUpdate(sql);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    void saveRecord(String username, int idBoard, int time) {
        int recordTime = -1;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT timeElapsed FROM Record WHERE Users_username = '"+username+"' AND Board_idBoard = "+idBoard+";");
            if (rs.next()) {
                recordTime = rs.getInt("timeElapsed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (recordTime == -1) {
            Statement stmt2 = null;
            try {
                stmt2 = conn.createStatement();
                String sql = "INSERT INTO Record (Users_username,Board_idBoard,timeElapsed) " +
                        "VALUES ('"+username+"', "+idBoard+", "+time+");";
                stmt2.executeUpdate(sql);
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (stmt2 != null)
                        stmt2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        else if (recordTime > time) {
            Statement stmt3 = null;
            try {
                stmt3 = conn.createStatement();
                String sql = "UPDATE Record set timeElapsed = "+time+"  WHERE Users_username = '"+username+"' AND Board_idBoard = "+idBoard+";";
                stmt3.executeUpdate(sql);
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (stmt3 != null)
                        stmt3.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    SimpleEntry<Integer, SimpleEntry<Integer, SimpleEntry<String, SimpleEntry<String, Integer>>>> savedMatch(
            int id, String username) {
        SimpleEntry<Integer, SimpleEntry<Integer, SimpleEntry<String, SimpleEntry<String, Integer>>>> result = null;

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT timeElapsed,Board_idBoard,boardSolved,boardState,hintsRemaining FROM SavedGame WHERE Users_username = '"
                    +username+"' AND idSavedGame = "+id+";");
            if (rs.next()) {
                result = new SimpleEntry<Integer, SimpleEntry<Integer,SimpleEntry<String,SimpleEntry<String,Integer>>>>(rs.getInt("timeElapsed"),
                        new SimpleEntry<Integer,SimpleEntry<String,SimpleEntry<String,Integer>>>(rs.getInt("Board_idBoard"),
                                new SimpleEntry<String,SimpleEntry<String,Integer>>(rs.getString("boardSolved"),
                                        new SimpleEntry<String,Integer>(rs.getString("boardState"),rs.getInt("hintsRemaining")))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    void deleteSavedMatch(int id) {

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "DELETE from SavedGame where idSavedGame = "+id+";";
            stmt.executeUpdate(sql);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null)
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    SimpleEntry<String, String> sysBoard(int diff) {
        SimpleEntry<String, String> board = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT board,boardSolved FROM SysBoard WHERE idBoard = "+diff+";");
            if (rs.next()) {
                board = new SimpleEntry<String, String>(rs.getString("board"), rs.getString("boardSolved"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return board;
    }

    void updateScore(String username) {
        int score = 0;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT score FROM Users WHERE username = '"+username+"';");
            if (rs.next()) {
                score = rs.getInt("score");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        Statement stmt2 = null;
        try {
            stmt2 = conn.createStatement();
            String sql = "UPDATE Users set score = "+(score+10)+"  WHERE username = '"+username+"';";
            stmt2.executeUpdate(sql);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt2 != null)
                    stmt2.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
