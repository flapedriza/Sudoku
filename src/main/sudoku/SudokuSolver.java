package main.sudoku;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by victor on 16/11/2015.
 */

public class SudokuSolver {
    private SudokuBoard board;
    private int size, sqroot;
    private ArrayList<ArrayList<Integer>> reservedRows;     // [row,value] -> region
    private ArrayList<ArrayList<Integer>> reservedColumns;  // [column,value] -> region

    public static void solve(SudokuBoard board)
    {
        new SudokuSolver(board);
    }

    public SudokuSolver(SudokuBoard board)
    {
        this.board = board;
        initialize();                   // podria ser simplemente actualize/process... y agrupar el solve+resolve.
        solve();
    }

    private void solve()
    {
        process();

        //ArrayList<Integer> order = order_cells();

        ArrayList<Integer> order = new ArrayList<>();
        for (int i = 0; i < size*size; ++i)
            order.add(i);

        boolean progress = false;

        for (int coordinate : order) {
            int i = (coordinate-1)/size + 1;
            int j = coordinate%size;

            TreeSet<Integer> possibles = prune(i-1,j-1,board.falten(i,j));

            if (possibles.size() == 1) {
                board.setValueCell(possibles.first(), i, j);
                order.remove(coordinate);
                progress = true;
            }
        }

        /*if (! progress) throw "Multiples solucions";
        else*/ if (! order.isEmpty()) solve();
    }

    private TreeSet<Integer> prune(int row, int column, TreeSet<Integer> resten)
    {
        int region = (row/sqroot)*sqroot + (column%sqroot)*sqroot;

        TreeSet<Integer> pruned = new TreeSet<>();
        for (int value : resten) {
            if (reservedRows.get(row).get(value-1) == region
                    && reservedColumns.get(column).get(value-1) == region) {
                pruned.clear();
                pruned.add(value);
                return pruned;
            }
            else if (reservedRows.get(row).get(value-1) == 0
                    && reservedColumns.get(column).get(value-1) == 0) pruned.add(value);
            else if (reservedRows.get(row).get(value-1) == region
                    || reservedColumns.get(column).get(value-1) == region) pruned.add(value);
        }
        return pruned;
    }

    private void initialize()
    {
        this.size = board.getSudokuSize();
        this.sqroot = (int) Math.sqrt(size + 0.0);

        this.reservedColumns = new ArrayList<>();
        this.reservedRows = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            reservedColumns.add(new ArrayList<Integer>());
            reservedRows.add(new ArrayList<Integer>());
            for (int j = 0; j < size; ++j) {
                reservedColumns.get(i).add(0);
                reservedRows.get(i).add(0);
            }
        }
    }

    private void process()
    {
        for (int region = 0; region < size; ++region) {
            int firstRow = (region/sqroot)*sqroot;
            int lastRow = firstRow + sqroot - 1;
            int firstColumn = (region%sqroot)*sqroot;
            int lastColumn = firstColumn + sqroot - 1;

            ArrayList<TreeSet<Integer>> lines = new ArrayList<>();

            for (int row = firstRow; row <= lastRow; ++row) {
                ArrayList<TreeSet<Integer>> cells = new ArrayList<>();
                for (int column = firstColumn; column <= lastColumn; ++column)
                    cells.add(board.falten(row+1,column+1));
                lines.add(include(cells));
            }
            exclude(lines);
            for (int row = firstRow; row <= lastRow; ++row)
                for (int value : lines.get(row))reservedRows.get(row).set(value-1,region);

            lines.clear();
            for (int column = firstColumn; column <= lastColumn; ++column) {
                ArrayList<TreeSet<Integer>> cells = new ArrayList<>();
                for (int row = firstRow; row <= lastRow; ++row)
                    cells.add(board.falten(row+1,column+1));
                lines.add(include(cells));
            }
            exclude(lines);
            for (int column = firstColumn; column <= lastColumn; ++column)
                for (int value : lines.get(column))reservedRows.get(column).set(value-1,region);
        }
    }

    // això es una OR inclusiva dels sets de l'array.
    private TreeSet<Integer> include(ArrayList<TreeSet<Integer>> cells)
    {
        TreeSet<Integer> t = new TreeSet<>();
        for (int i = 0; i < sqroot; ++i)
            for (int faltant : cells.get(i)) t.add(faltant);
        return t;
    }

    // això es una OR exlusiva de tots els sets de l'array. els resultats, pero, es guarden als mateixos sets.
    // per a tots els sets (menys l'ultim) busca coincidencies entre els seguents i les elimina als dos sets.
    private void exclude(ArrayList<TreeSet<Integer>> lines)
    {
        for (int i = 0; i < sqroot-1; ++i) {
            Iterator<Integer> restant = lines.get(i).iterator();
            while (restant.hasNext())
                for (int j = i + 1; j < sqroot; ++j)
                    if (lines.get(j).remove(restant.next())) {
                        for (int l = j + 1; l < sqroot; ++l)
                            lines.get(l).remove(restant.next());
                        lines.get(i).remove(restant.next());
                    }
        }
    }

    /*public void reSolve()
    {
        actualize();
        solve();
    }
    */

}