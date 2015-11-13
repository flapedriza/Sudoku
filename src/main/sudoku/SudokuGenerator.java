package main.sudoku;



import javafx.util.Pair;

import java.util.*;

/**
 * Created by Francesc on 7/11/2015.
 */

private class PairComparator implements Comparator<Pair<Integer, Integer>> {
    public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
        Random random = new Random();
        int a = random.nextInt(2);
        int ret = (a == 0) ? -1 : 1;
        if(p1.getValue() == p2.getValue()) return ret;
        else return (p1.getValue() < p2.getValue()) ? -1 : 1;
    }
}

public class SudokuGenerator {
    public enum Difficulty {EASY, NORMAL, HARD}
    Difficulty difficulty;
    public int size;
    Random random;

    public SudokuGenerator(Difficulty dif, int size) {
        this.size = size;
        this.difficulty = dif;
        this.random = new Random();

    }
    public void generate(SudokuBoard board) {

    }

    private Pair<Integer, Integer> rowColFromNum(int n) {
        int row = n/size+1;
        int col = n%size;
        if(col == 0) col = size;
        return new Pair<>(row, col);
    }

    private int getmin(HashMap<Integer, Integer> map) {
        Map.Entry<Integer, Integer> min = null;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(min == null || min.getValue() > entry.getValue()) min = entry;
        }
        return min.getKey();
    }

    private void create(SudokuBoard board) {
        ArrayList<Pair<Integer, Integer>> cells = new ArrayList<>();
        for(int i = 0; i<size*size; ++i) {
            cells.add(new Pair<>(i+1, size));
        }
        Collections.shuffle(cells);
        for(int i = 0; i<size*size; ++i) {
            int actual = Collections.min(cells, new PairComparator()).getKey();

        }

    }
}
