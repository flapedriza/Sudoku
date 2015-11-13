package main.sudoku;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by Francesc on 7/11/2015.
 */

class Pair {
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
        int ret = (a == 0) ? -1 : 1;
        if(p1.second == p2.second) return ret;
        else return (p1.second < p2.second) ? -1 : 1;
    }
}

public class SudokuGenerator {
    public enum Difficulty {EASY, NORMAL, HARD}
    Difficulty difficulty;
    static int easy_threshold = 1;
    static int normal_threshold = 2;
    static int hard_threshold = 3;
    static int min_cells;
    static int max_cells;
    public int size;
    Random random;

    public SudokuGenerator(Difficulty dif, int size) {
        this.size = size;
        this.difficulty = dif;
        this.random = new Random();

    }
    public void generate(SudokuBoard board) {
        create(board);
        int diff;
        Boolean done;
        switch (difficulty) {
            case EASY : diff = easy_threshold;
                break;
            case NORMAL: diff = normal_threshold;
                break;
            case HARD: diff = hard_threshold;
                break;
            default: break;
        }
        for(int i = 1; i < max_cells; ++i) removecell(board);
        do {
            removecell(board);
            done = (true);
        } while (!done );

    }

    private Pair rowColFromNum(int n) {
        int row = n/size+1;
        int col = n%size;
        if(col == 0) col = size;
        return new Pair(row, col);
    }

    private int regFromNum(int n) {
        Pair rowcol = rowColFromNum(n);
        return (int) (1 + ((rowcol.second-1)/Math.sqrt(size)) + ((rowcol.first-1)/Math.sqrt(size))*Math.sqrt(size));
    }

    private void updaterow(int n, ArrayList<Pair> a) {
        for(Pair i : a) {
            int row = rowColFromNum(i.first).first;
            if(row == n) --i.second;
        }
    }

    private void updatecol(int n, ArrayList<Pair> a) {
        for(Pair i : a) {
            int col = rowColFromNum(i.first).first;
            if(col == n) --i.second;
        }
    }

    private void updatereg(int n, ArrayList<Pair> a) {
        for(Pair i : a) {
            int reg = regFromNum(i.first);
            if(reg == n) --i.second;
        }
    }

    private void removecell(SudokuBoard board){}

    private void create(SudokuBoard board) {
        ArrayList<Pair> cells = new ArrayList<>();
        for(int i = 0; i<size*size; ++i) {
            cells.add(new Pair(i+1, size));
        }
        Collections.shuffle(cells);
        for(int i = 0; i<size*size; ++i) {
            Pair actualelem = Collections.min(cells, new PairComparator());
            Pair actualRowCol = rowColFromNum(actualelem.first);
            ArrayList<Integer> falts = board.falten(actualRowCol.first, actualRowCol.second);
            int num = falts.get(random.nextInt(falts.size()));
            board.setValueCell(num, actualRowCol.first, actualRowCol.second);
            updatecol(actualelem.first, cells);
            updatereg(actualelem.first, cells);
            updaterow(actualelem.first, cells);
            cells.remove(actualelem);
        }
    }
}
