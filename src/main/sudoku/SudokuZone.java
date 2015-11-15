package main.sudoku;

import java.util.ArrayList;
import java.util.TreeSet;
/**
 * Created by Adri on 12/11/15.
 */
public abstract class SudokuZone {
    //protected int size = Board.getSize();//com que es protected ja podem ferho servir a les subclases
    protected int valor; //a es el valor de la fila que busquem
    protected ArrayList<Boolean> usats;
    protected TreeSet<Integer> falten;
    protected ArrayList<SudokuCell> celes;

    public SudokuZone(int size, int valor) {
        this.usats = new ArrayList<>();
        this.falten = new TreeSet<>();
        this.valor = valor;
        for(int i = 0; i<size; ++i) usats.add(false);
        for(int i = 0; i<size; ++i) falten.add(i+1);
    }

}
class Row extends SudokuZone {
    public Row(int size, int valor) {
        super(size, valor);
    }

}
class Col extends SudokuZone{
    public Col(int size, int valor) {
        super(size, valor);
    }
}
class Reg extends SudokuZone{
    public Reg(int size, int valor) {
        super(size, valor);
    }
}
