package main.sudoku;

import java.util.Iterator;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
	// write your code here
        SudokuBoard board = new SudokuBoard(9);
        TreeSet<Integer> set = board.falten(1, 1);
        Iterator<Integer> it = set.iterator();
        while(it.hasNext()) System.out.println(it.next());
    }
}
