/**
 * Created by victor on 11/11/2015.
 */

package main.sudoku;

import java.util.ArrayList;


public abstract class Stats {

    private ArrayList<Player> players;
    private ArrayList<? extends Playable> games;
    private ArrayList<Match> matches;

    public Stats(ArrayList<Player> players, ArrayList<? extends Playable> games, ArrayList<Match> matches)
    {
        this.players = players;
        this.games = games;
        this.matches = matches;
    }

    /////// PLAYER STATS ////////////////////////////////////////////////////////////////////
    public Ranking rankingGlobal()
    {
        ArrayList<Integer> scores = new ArrayList<Integer>();
        for (int i = 0; i < players.size())
            scores.add(i,score(players.get(i));
        return Ranking(players,scores);
    }

    protected abstract int score(Player player);

    public int countMatches(Player player)
    {
        int count;
        String name = players.get(i).getName();
        for (int i = 0; i < matches.size(); ++i)
            if (matches.get(i).getPlayerName() == name) ++count;
        return count;
    }

    public int countSolvedGames(Player player)
    {
        ArrayList<Integer> countedGames = new ArrayList<Integer>();
        String name = player.getName();

        for (int i = 0; i < matches.size(); ++i) {
            Match m = matches.get(i);
            if (m.getPlayerName() == name && m.getResult() != -1)
                insert_no_repeat(countedGames, matches.get(i).getGameId());
        }

        return countedGames.size();
    }

    public int countSolvedGamesDiff(int difficulty, Player player)
    {
        ArrayList<Integer> countedGames = new ArrayList<Integer>();
        String name = player.getName();

        for (int i = 0; i < matches.size(); ++i) {
            Match m = matches.get(i);
            if (m.getPlayerName() == name && m.getResult() != -1 && getDiff(m) == difficulty)
                insert_no_repeat(countedGames, matches.get(i).getGameId());
        }

        return countedGames.size();
    }

    private void insert_no_repeat(ArrayList<Integer> array, int new_one)
    {
        int left = 0;
        int right = array.size();
        while (left <= right) {
            int i = (left + right) / 2;
            if (array.get(i) < new_one) left = i + 1;
            else if (array.get(i) > new_one) right = i - 1;
            else return;
        }
        if (array.get(left) != new_one) array.add(left,new_one);
    }

    private int getDiff(Match m)
    {
        int id = m.getGameId();
        for (int i = 0; i < games.size(); ++i)
            if (games.get(i).getGameId == id)
                return games.get(i).getDifficult();
    }

    /////// GAME STATS //////////////////////////////////////////////////////////////////////
    public Ranking recordsGame(? extends Playable game)
    {
        ArrayList<Integer> besTimes = new ArrayList<Integer>();

        // work in progress

        return Ranking(players,besTimes);
    }

    public int countTimesPlayed(? extends Playable game)
    {
        int count = 0;
        int id = game.getGameId();

        for (int i = 0; i < matches.size(); ++i)
            if (matches.get(i).getGameId() == id) ++count;

        return count;
    }

    public int countTimesSolved(? extends Playable game)
    {
        int count = 0;
        int id = game.getGameId();

        for (int i = 0; i < matches.size(); ++i)
            if (matches.get(i).getGameId() == id
                    && matches.get(i).getResult() != -1) ++count;

        return count;
    }

    /////// USELESS STATS ///////////////////////////////////////////////////////////////////
    public int countPlayers() { return players.size(); }
    public int countGames() { return games.size(); }
    public int countMatches() { return matches.size(); }
}