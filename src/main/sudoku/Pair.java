package main.sudoku;

import java.util.Comparator;
import java.util.Random;

/**
 * Created by Francesc on 19/11/2015.
 */
public class Pair {
    int first;
    int second;
    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class PairComparator implements Comparator<Pair> {
    public int compare(Pair p1, Pair p2) {
        Random random = new Random();
        int a = random.nextInt(2);
        if(p1.second == p2.second) return (a==0) ? -1 : 1;
        else return (p1.second < p2.second) ? -1 : 1;
    }
}
