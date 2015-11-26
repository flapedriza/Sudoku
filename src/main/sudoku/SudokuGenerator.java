package main.sudoku;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by Francesc on 7/11/2015.
 */

public class SudokuGenerator {
    public enum Difficulty {EASY, NORMAL, HARD}
    Difficulty difficulty;
    static int easy = 25;
    static int normal = 35;
    static int hard = 45;
    public int size;
    boolean finished;

    public SudokuGenerator(Difficulty dif, int size) throws OutOfRangeException {
        if (size != 9 && size != 16 && size != 4) throw new OutOfRangeException();
        this.size = size;
        this.difficulty = dif;
        this.finished = false;

    }
    public void generate(SudokuBoard board)  {
        board.clear();
        create(board,1);
        finished = false;
        //removeCells(board, (size*size)/2);
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

    public void setDifficulty(Difficulty dif) {
        this.difficulty = dif;
    }

    public void removeCells(SudokuBoard board, int number) throws OutOfRangeException {
        if(number > size*size) throw new OutOfRangeException();
        ArrayList<Integer> cells = new ArrayList<>();
        for(int i=1;i<=size*size;++i) cells.add(i);
        Collections.shuffle(cells);
        for(int i = 0; i<number; ++i) {
            Pair rc = rowColFromNum(cells.get(i));
            board.erase(rc.first, rc.second);
        }
    }

    public void create(SudokuBoard board, Integer rec)  {
        if(!finished) {
            Pair rc = rowColFromNum(rec);
            ArrayList<Integer> list = new ArrayList<>();
            TreeSet<Integer> set = null;
            try {
                set = board.falten(rc.first, rc.second);
            } catch (OutOfRangeException e) {
                e.printStackTrace();
            }
            Iterator<Integer> it = set.iterator();
            while(it.hasNext()) list.add(it.next());
            Collections.shuffle(list);
            for(int n = 0; n<list.size();++n) {
                int m = list.get(n);
                try {
                    if(board.setValueCell(m, rc.first, rc.second)) {
                        if(rec.equals(size*size)) {
                            finished = true;
                            return;
                        }
                        else create(board, rec+1);
                    }
                } catch (OutOfRangeException e) {
                    e.printStackTrace();
                }
                if(!finished) try {
                    board.erase( rc.first, rc.second);
                } catch (OutOfRangeException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
