package sudoku.domain;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by Francesc on 7/11/2015.
 */

/**
 * SudokuGenerator Class make sudokus with solution for solve later
 */
public class SudokuGenerator {
    public enum Difficulty {EASY, NORMAL, HARD}
    Difficulty difficulty;
    static int easy = 25;
    static int normal = 35;
    static int hard = 45;
    public int size;
    boolean finished;

    /**
     * Create a Sudoku with size "size" and dificulty "dif"
     * @param dif
     * @param size
     * @throws OutOfRangeException
     */
    public SudokuGenerator(Difficulty dif, int size) throws OutOfRangeException {
        if (size != 9 && size != 16 && size != 4) throw new OutOfRangeException();
        this.size = size;
        this.difficulty = dif;
        this.finished = false;

    }

    /**
     * Generate a new Sudoku With dificulty.
     * @param board
     */
    public void generate(SudokuBoard board)  {
        board.clear();
        create(board,1);
        finished = false;
        //removeCells(board, (size*size)/2);
        int diff = 0;
        Boolean done;
        switch (difficulty) {
            case EASY : diff = easy;
                break;
            case NORMAL: diff = normal;
                break;
            case HARD: diff = hard;
                break;
            default: break;
        }
        try {
            removeCells(board, diff);
        } catch (OutOfRangeException e) {
            e.printStackTrace();
        }

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

    /**
     * Remove a Number of Cells that we specify and refresh the values of zones.
     * @param board
     * @param number
     * @throws OutOfRangeException
     */
    public void removeCells(SudokuBoard board, int number) throws OutOfRangeException {
        if(number > size*size) throw new OutOfRangeException();
        ArrayList<Integer> cells = new ArrayList<>();
        for(int i=1;i<=size*size;++i) cells.add(i);
        Collections.shuffle(cells);
        SudokuSolver2 solver2 = new SudokuSolver2();
        for(int i = 0; i<number; ++i) {
            if(cells.isEmpty()) throw new OutOfRangeException();
            Pair rc = rowColFromNum(cells.get(0));
            int old = board.getValueCell(rc.first-1, rc.second-1);
            board.erase(rc.first, rc.second);
            if(solver2.uniqueSolution(board) != 2) {
                board.setValueCell(old, rc.first, rc.second);
                --i;
            }
            cells.remove(0);
        }
    }

    /**
     * Create the finished Sudoku for Solve
     * @param board
     * @param rec
     */
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
