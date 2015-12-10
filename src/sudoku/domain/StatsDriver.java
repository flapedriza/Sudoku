package sudoku.domain;

import java.util.Scanner;

public class StatsDriver {
    static Table<Player> _players;
    static Table<stubGame> _games;
    static Table<stubMatch> _matches;
    static SudokuStats _stats;
    static Scanner _scan = new Scanner(System.in);

    public static void main(String args[])
    {
        initialize();
        trial();
    }

    private static void initialize()
    {
        _players = new Table<>();
        _games = new Table<>();
        _matches = new Table<>();

        write("First, we are going to read the data to be computed into stats.");
        write("We will begin with the players. For every player you have to enter " +
                "a username. For every string you enter we will create a new player.\n" +
                "Obviously, two different players can't have the same username. " +
                "When you finish, enter a 0 after the last player.");
        String username;
        username = readString();
        while (!username.equals("0")) {
            _players.add(new Player(username));
            username = readString();
        }
        write("OK, we have the players. Now come the games. For every game you want " +
                "to create, you have to provide a game ID and a difficulty, both as numbers.\n" +
                "Particularly, the difficulty can take values from 1 (Easy) to 3 (Hard). " +
                "Just like before, enter a 0 when you finish.");
        int gameID, difficulty;
        gameID = readInt();
        while (gameID != 0) {
            difficulty = readInt();
            _games.add(new stubGame(gameID, difficulty));
            gameID = readInt();
        }
        write("OK, got them. For last, we need to pair the players and games into matches. " +
                "For every match, enter a player username and a game ID.\nEnter also a time for the match; " +
                " if it's 0, it will be saved as not finished. Remember the 0 after the last one!");
        username = readString();
        while (!username.equals("0")) {
            gameID = readInt();
            int time = readInt();

            int posP = find(username);
            int posG = find(gameID);
            if (posP == -1 || posG == -1) write("Player or Game not found, Match not saved.");
            else {
                Player p = _players.get(posP);
                stubGame g = _games.get(posG);
                stubMatch m = new stubMatch(p,g);
                if (time > 0) m.setTime(time);
                _matches.add(m);
            }
            username = readString();
        }
        _stats = new SudokuStats(_players, _games, _matches);
        write("Congrats, we've finished. You can now try the Stats Class with your data.");
    }

    private static void trial()
    {
        int option;
        do {
            write("Main Menu | Select an option:  1. Try Player Stats" +
                    "  2. Try Game Stats  3. Try Global Stats  0. Close Driver");
            option = readInt();
            switch (option) {
                case 1:
                    trial_playerStats();
                    break;
                case 2:
                    trial_gameStats();
                    break;
                case 3:
                    trial_globalStats();
                    break;
                case 0:
                    break;
                default:
                    write("Not an option.");
                    break;
            }
        } while (option != 0);
    }

    private static void trial_playerStats()
    {
        int option;
        do {
            write("Player Stats | Select an option:  1. countMatches" +
                    "  2. countSolvedGames  3. countSolvedGamesDiff  4. rank  0. Return");
            option = readInt();
            if (option < 0 || 4 < option) write("Not an option.");
            else if (option != 0) {
                write("Enter a valid username.");
                String username = readString();
                int pos = find(username);
                Player player = null;
                if (pos == -1) option = 5;
                else player = _players.get(pos);
                switch (option) {
                    case 1:
                        write("Player "+username+" has played "+_stats.countMatches(player)+" matches.");
                        break;
                    case 2:
                        write("Player "+username+" has solved "+
                                _stats.countSolvedGames(player)+" distinct games.");
                        break;
                    case 3:
                        write("Enter a difficulty level.");
                        int difficulty = readInt();
                        write("Player "+username+" has solved "+_stats.countSolvedGamesDiff
                                (difficulty,player)+" distinct games of difficulty "+difficulty+".");
                        break;
                    case 4:
                        write("Player "+username+" is ranked "+
                                _stats.rank(player)+" in the game global ranking.");
                        break;
                    case 5:
                        write("Invalid username.");
                        break;
                }
            }
        } while (option != 0);
    }

    private static void trial_gameStats()
    {
        int option;
        do {
            write("Game Stats | Select an option:  1. recordsGame" +
                    "  2. countTimesPlayed  3. countTimesSolved  0. Return");
            option = readInt();
            if (option < 0|| 3 < option) write("Not an option.");
            else if (option != 0) {
                write("Enter a valid gameID.");
                int gameID = readInt();
                int pos = find(gameID);
                stubGame game = null;
                if (pos == -1) option = 4;
                else game = _games.get(pos);
                switch (option) {
                    case 1:
                        write("Position | Player | Time");
                        print(_stats.recordsGame(game));
                        break;
                    case 2:
                        write("Game "+gameID+" has been played "+_stats.countTimesPlayed(game)+" times.");
                        break;
                    case 3:
                        write("Game "+gameID+" has been solved "+_stats.countTimesSolved(game)+" times.");
                        break;
                    case 4:
                        write("Invalid game ID.");
                        break;
                }
            }
        } while (option != 0);
    }

    private static void trial_globalStats()
    {
        int option;
        do {
            write("Global Stats | Select an option:  1. rankingGlobal" +
                    "  2. countPlayers  3. countGames  4. countMatches  0. Return");
            option = readInt();
            switch (option) {
                case 1:
                    write("Position | Player | Score");
                    print(_stats.rankingGlobal());
                    break;
                case 2:
                    write("Number of players in the program: "+_stats.countPlayers()+".");
                    break;
                case 3:
                    write("Number of games in the program: "+_stats.countGames()+".");
                    break;
                case 4:
                    write("Number of matches in the program: "+_stats.countMatches()+".");
                    break;
                case 0: break;
                default:
                    write("Not an option.");
                    break;
            }
        } while (option != 0);
    }

    private static int find(String username)
    {
        for (int i = 0; i < _players.size(); ++i)
            if (_players.get(i).getName().equals(username)) return i;
        return -1;
    }

    private static int find(int gameID)
    {
        for (int i = 0; i < _games.size(); ++i)
            if (_games.get(i).getId() == gameID) return i;
        return -1;
    }

    private static void print(Ranking ranking)
    {
        int size = ranking.getSize();
        if (size == 0) write("This ranking is void.");
        else {
            int digits = digits(size);
            for (int i = 0; i < size; ++i)
                write(num(i+1,digits) + "  " + ranking.getPlayer(i)
                        .getName() + "   " + ranking.getValue(i));
        }
    }

    private static String num(int num, int digits)
    {
        String numS = "";
        int zeros = digits - digits(num);
        for (int i = 1; i <= zeros; ++i) numS = numS + "0";
        return numS + num;
    }

    private static int digits(int num)
    {
        int digits = 1;
        while (num > 9) {
            ++digits;
            num /= 10;
        }
        return digits;
    }

    private static String readString()
    {
        String input;
        input = _scan.next();
        return input;
    }

    private static int readInt()
    {
        return _scan.nextInt();
        /*
        int input;
        try {
            input = _scan.nextInt();
        } catch (InputMismatchException e) {
            write("Hey! The program expects a number. Try again.");
            input = readInt();
        }
        return input;
        */   // Sense això pot petar. Això, no ho resol i a més treu un munt de merda per pantalla.
    }
    
    private static void write(String output)
    {
        System.out.println(output);
    }
}