package main.sudoku;

import java.util.ArrayList;

public abstract class SudokuZone {
    //protected int size = Board.getSize();//com que es protected ja podem ferho servir a les subclases
    protected int valor; //a es el valor de la fila que busquem
    protected ArrayList<Boolean> usats;
    protected ArrayList<Integer> falten;
    protected ArrayList<SudokuCell> celes;

    public SudokuZone(int size, int valor) {
        this.usats = new ArrayList<>();
        this.falten = new ArrayList<>();
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
