/**
 * Created by victor on 11/11/2015.
 */

package main.sudoku;

import java.util.*;

public class Ranking {

    private ArrayList<Player> players;
    private ArrayList<Integer> values;
    private ArrayList<Integer> order;
    private Integer size;

    public Ranking() {}

    protected Ranking(ArrayList<Player> players, ArrayList<Integer> values)
    {
        this.players = players;
        this.values = values;
        order = new ArrayList<Integer>();
        sort();
        size = order.size();
    }

    private static void sort()
    {
        // work in progress;
    }

    public int getSize() { return size; }

    public int getValue(Player player) {
        int i = players.indexOf(player);    // Suposo que puc fer això per l'equals que heu implementat.
        return values.get(i);
    }

    public Player getPlayer(int position) {
        return players.get(order.get(position));
    }

    public int getValue(int position) {
        return values.get(order.get(position));
    }
}
