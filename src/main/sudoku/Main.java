package main.sudoku;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        SudokuBoard sudoku = new SudokuBoard(9);
        ArrayList<Integer> list = sudoku.falten(1, 1);
        for(int i : list) System.out.println(i+' ');
        sudoku.setValueCell(3, 1, 1);
        list = sudoku.falten(1, 1);
        System.out.println("After");
        for(int i : list) System.out.println(i+' ');


    }
}
