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
    boolean finished;

    public SudokuGenerator(Difficulty dif, int size) {
        this.size = size;
        this.difficulty = dif;
        this.finished = false;

    }
    public void generate(SudokuBoard board) {
        create(board,1);
        finished = false;
        /*int diff;
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
            done = true;//(Solver.evaluate(board) > diff);
        } while (!done );*/

    }

    private Pair rowColFromNum(int n) {
        int m = n-1;
        int row = m/size;
        int col = m%size;
        return new Pair(row+1, col+1);
    }

    //TODO
    private void removecell(SudokuBoard board){}

    private void create(SudokuBoard board, Integer rec) {
        if(!finished) {
            Pair rc = rowColFromNum(rec);
            ArrayList<Integer> list = new ArrayList<>();
            for(int k = 1; k<size+1;++k) list.add(k);
            Collections.shuffle(list);
            for(int n = 0; n<size&&!finished;++n) {
                int m = list.get(n);
                if(board.setValueCell(m, rc.first, rc.second)) {
                    if(rec.equals(size*size)) {
                        finished = true;
                        return;
                    }
                    else create(board, rec+1);
                }
                if(!finished)board.setValueCell(0, rc.first, rc.second);
            }
        }
    }
}
