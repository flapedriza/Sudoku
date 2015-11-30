package main.sudoku;

import java.util.ArrayList;
import java.util.TreeSet;
/**
 * Created by Adri on 12/11/15.
 */

/**
 * Abstract SudokuZone class used to make things easier later
 */
public abstract class SudokuZone {
    protected int valor; //a es el valor de la fila que busquem
    protected ArrayList<Boolean> usats;
    protected TreeSet<Integer> falten;
    protected ArrayList<SudokuCell> celes;

    /**
     * Construct an empty SudokuZone
     * @param size that's the size from a row col or reg
     * @param valor that's the value from a Cell
     */
    public SudokuZone(int size, int valor) {
        this.usats = new ArrayList<>();
        this.falten = new TreeSet<>();
        this.valor = valor;
        for(int i = 0; i<size; ++i) usats.add(false);
        for(int i = 0; i<size; ++i) falten.add(i+1);
    }
    /** Remove a value from a zone
     * @param value Number that we will remove
     */
    public void remove(int value) {
        usats.set(value-1,false);
        falten.add(value);
    }

    /** Add a value from a zone
     * @param value that's the new Cell value.
     */
    public void add(int value) {
        usats.set(value-1, true);
        falten.remove(value);
    }

}

/**
 * Row class is the Zone and extends SudokuZone
 */
class Row extends SudokuZone {
    public Row(int size, int valor) {
        super(size, valor);
    }

}
/**
 * Col class is the Zone and extends SudokuZone
 */
class Col extends SudokuZone{
    public Col(int size, int valor) {
        super(size, valor);
    }
}
/**
 * Reg class is the Zone and extends SudokuZone
 */
class Reg extends SudokuZone{
    public Reg(int size, int valor) {
        super(size, valor);
    }
}
