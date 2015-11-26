package main.sudoku;


import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by Francesc on 22/11/2015.
 */
public class SudokuSolver2 {
    private SudokuBoard board;

    public SudokuSolver2() {
        board = null;
    }

    public SudokuBoard solve(SudokuBoard board) {
        try {
            this.board = new SudokuBoard(board);
        } catch (OutOfRangeException e) {
            e.printStackTrace();
        }
        ArrayList<Integer> posicions = new ArrayList<>();
        for(Integer i : board.getBuides()) posicions.add(i);
        System.out.println(posicions);
        Boolean b = backtrack(posicions);
        if(!b) return null;
        return this.board;
    }

    private Boolean backtrack(ArrayList<Integer> posicions) {
        if(posicions.isEmpty()) return true;
        int act = posicions.get(0);
        posicions.remove(0);
        Pair rc = rowColFromNum(act);
        TreeSet<Integer> set = null;
        try {
            set = board.falten(rc.first, rc.second);
        } catch (OutOfRangeException e) {
            e.printStackTrace();
        }
        for(int val : set) {
            try {
                if(board.setValueCell(val, rc.first, rc.second)) return true;
            } catch (OutOfRangeException e) {
                e.printStackTrace();
            }
            if(backtrack(posicions)) return true;
            try {
                board.erase(rc.first, rc.second);
            } catch (OutOfRangeException e) {
                e.printStackTrace();
            }
        }
        posicions.add(0,act);
        return false;
    }

    private Pair rowColFromNum(int n) {
        int m = n-1;
        int row = m/board.getSudokuSize();
        int col = m%board.getSudokuSize();
        return new Pair(row+1, col+1);
    }
}
