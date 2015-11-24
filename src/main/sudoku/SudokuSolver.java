package main.sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class SudokuSolver {
    private SudokuBoard sudoku;
    private int size;

    public static SudokuBoard solve(SudokuBoard board) {
        return new SudokuSolver(board).solve();
    }

    public SudokuSolver(SudokuBoard inicial)
    {
        //this.sudoku = inicial.copia();
        this.sudoku = inicial;
        size = inicial.getSudokuSize();
        //sudoku.print();
    }

    public SudokuBoard solve()
    {
        ArrayList<Integer> posicions = new ArrayList<>();
        sort(posicions);
        Boolean b = solve_BT(posicions);
        if (!b) System.out.println("Sudoku sense solució");
        return sudoku;
    }

    public boolean solucio_unica()
    {
        ArrayList<Integer> posicions = new ArrayList<>();
        sort(posicions);
        Boolean b = solve_BT(posicions);
        if (!b) System.out.println("Sudoku sense solució");
        else {
            SudokuBoard solucio1 = sudoku.copia();
            Collections.swap(posicions, 0, posicions.size()-1);
            solve_BT(posicions);
            b = sudoku.iguals(solucio1);
        }
        return b;
    }

    private void sort(ArrayList<Integer> posicions)
    {
        ArrayList<Integer> sizeFalten = new ArrayList<>();
        sizeFalten.add(null);
        for (int i = 1; i <= size*size; ++i) {
            if (sudoku.getValueCell(fila(i)-1,columna(i)-1) == 0) sizeFalten.add(0);
            else sizeFalten.add(sudoku.falten(fila(i),columna(i)).size());
        }

        posicions = new ArrayList<>();
        posicions.add(null);
        for (int i = 1; i < sizeFalten.size(); ++i) {
            int j = i - 1;
            while (j > 0 && sizeFalten.get(posicions.get(j)) > sizeFalten.get(i)) --j;
            posicions.add(j + 1, i);
        }
        posicions.remove(0);

        if (sizeFalten.get(posicions.get(posicions.size()-1)) > 0)
            while (sizeFalten.get(posicions.get(0)) == 0) posicions.remove(0);
        else posicions.clear();
    }

    private Boolean solve_BT(ArrayList<Integer> posicions)
    {
        if (posicions.isEmpty()) return true;

        int posicio = posicions.get(0);
        posicions.remove(0);
        int fila = fila(posicio);
        int columna = columna(posicio);
        TreeSet<Integer> values = sudoku.falten(fila,columna);
        for (int value : values) {
            System.out.println(value);
            sudoku.setValueCell(value,fila,columna);
            if (solve_BT(posicions)) return true;
            sudoku.erase(fila,columna);
        }
        posicions.add(0,posicio);
        return false;
    }

    private int fila(int posicio)
    {
        return (posicio-1)/size + 1;
    }

    private int columna(int posicio)
    {
        int columna = posicio%size;
        if (columna == 0) columna = size;
        return columna;
    }
}
