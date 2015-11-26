package main.sudoku;


import java.util.ArrayList;
import java.util.Collections;
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
        Boolean b = backtrack(posicions, this.board);
        if(!b) return null;
        return this.board;
    }

    //Retorna 0 si no té solució, 1 si en té però no és única i 2 si té solució única.
    public int uniqueSolution(SudokuBoard board) {
        SudokuBoard b1 = null;
        try {
            b1 = new SudokuBoard(board);
        } catch (OutOfRangeException e) {
            e.printStackTrace();
        }
        ArrayList<Integer> posicions = new ArrayList<>();
        for(Integer i : board.getBuides()) posicions.add(i);
        ArrayList<Integer> posicions2 = new ArrayList<>(posicions);
        if(!backtrack(posicions, b1)) return 0;
        Collections.shuffle(posicions2);
        SudokuBoard b2 = null;
        try {
            b2 = new SudokuBoard(board);
        } catch (OutOfRangeException e) {
            e.printStackTrace();
        }
        if(!backtrack(posicions2, b2)) return 0;
        if(!b1.equals(b2)) return 1;
        return 2;



    }

    private Boolean backtrack(ArrayList<Integer> posicions, SudokuBoard board) {
        if(posicions.isEmpty()) return true;
        int act = posicions.get(0);
        posicions.remove(0);
        Pair rc = rowColFromNum(act, board.getSudokuSize());
        TreeSet<Integer> set = null;
        try {
            set = board.falten(rc.first, rc.second);
        } catch (OutOfRangeException e) {
            e.printStackTrace();
        }
        for(int val : set) {
            try {
                board.setValueCell(val, rc.first, rc.second);
            } catch (OutOfRangeException e) {
                e.printStackTrace();
            }
            if(backtrack(posicions, board)) return true;
            try {
                board.erase(rc.first, rc.second);
            } catch (OutOfRangeException e) {
                e.printStackTrace();
            }
        }
        posicions.add(0,act);
        return false;
    }

    private Pair rowColFromNum(int n, int size) {
        int m = n-1;
        int row = m/size;
        int col = m%size;
        return new Pair(row+1, col+1);
    }
}
