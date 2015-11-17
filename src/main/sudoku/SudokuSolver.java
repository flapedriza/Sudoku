package main.sudoku;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by victor on 16/11/2015.
 */

public class SudokuSolver {
    private SudokuBoard board;
    private SudokuBoard solucio;
    private int size;

    public static void solve(SudokuBoard board) {
        new SudokuSolver(board);
    }

    public SudokuSolver(SudokuBoard board)
    {
        this.board = board;
    }

    public SudokuBoard solve() {
        ArrayList<Integer> posicions = new ArrayList<>();
        for (int i = 1; i <= size*size; ++i) posicions.add(i);
        sort(posicions);

        Boolean b = solve_BT(posicions,0);
        if (!b) System.out.println("Sudoku sense solució");
        return solucio;
    }

    public boolean solucio_unica() {
        ArrayList<Integer> posicions = new ArrayList<>();
        for (int i = 1; i <= size*size; ++i) posicions.add(i);
        //sort(posicions);

        Boolean b = solve_BT(posicions,0);
        if (!b) System.out.println("Sudoku sense solució");
        else {
            SudokuBoard solucio1 = solucio.clone();
            int aux = posicions.get(size);
            posicions.set(size,posicions.get(0));
            posicions.set(0,aux);
            solve_BT(posicions,0);
            b = solucio.iguals(solucio1);
        }
        return b;
    }

    private void sort(ArrayList<Integer> posicions)
    {
        for (int i = 1; i < posicions.size();++i) {
            int isize = board.falten(fila(i),columna(i)).size();

            int j = i-1;
            int jsize = board.falten(fila(j),columna(j)).size();

            while (j > 0 && posicions.get(j) > posicions.get(i)) {

            }
            int aux = posicions.get(j+1);
            posicions.set(j+1,posicions.get(i));
            posicions.set(i,aux);
        }
    }

    private Boolean solve_BT(ArrayList<Integer> posicions, int i)
    {
        if (i == posicions.size()) {
            solucio = board.clone();
            return true;
        }

        int fila = fila(posicions.get(i));
        int columna = columna(posicions.get(i));
        TreeSet<Integer> values = board.falten(fila,columna);
        Iterator<Integer> value = values.iterator();
        while (value.hasNext()) {
            int actual = value.next();
            board.setValueCell(actual,fila,columna);
            boolean b = solve_BT(posicions,i+1);
            board.erase(fila,columna);
            return b;
        }
        return false;
    }

    private int fila(int posicio) {
        return (posicio-1)/size + 1;
    }

    private int columna(int posicio) {
        int columna = posicio%size;
        if (columna == 0) columna = size;
        return columna;
    }
}