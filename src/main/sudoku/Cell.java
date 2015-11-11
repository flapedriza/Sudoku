package main.sudoku;

import java.util.ArrayList;

/**
 * Created by Adri on 6/11/15.
 */
public class Cell extends ArrayList<Cell> {
    private static int Reg;
    int value;
    int column; //numero de columna
    int row; //numero de fila
    boolean[] annotations = new boolean[Board.consult_max_annotations()]; //marques
    boolean visible; //per veure si la casella és visible
    boolean written; //per veure si el valor ja venia donat en el joc o no

    public Cell(int row, int column, int reg) {
        this.column = column;
        this.row = row;
        this.Reg = reg;
        written = false;
        visible = false;
        value = -1;

        for (int i = 0; i < annotations.length; i++) annotations[i] = false;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getColumn() {
        return column;
    }
    static int getReg(){
        return Reg;
    }
    public int getRow() {
        return row;
    }

    public boolean getWritten(){
        return written;
    }

    public void switchWritten(){  //Aclarar perquè = written.
        this.written ^= true;
    }

    public boolean getVisible(){
        return visible;
    }

    public void switchVisible(){  //Aclarar perquè = visible.
        this.visible ^= true;
    }

    //NO ÉS COMÚ PERÒ AQUÍ TENIU PER FER-HO SIMILAR (Ho tenim dins CellHidato)
    /*public boolean getAnnotation(int value) {
        return annotations[value - 1];
    }
    public void switchAnnotation(int value) {
        this.annotations[value - 1] ^= true;
    }
    public void setAnnotation(int value, boolean annotation) {
        this.annotations[value - 1] = annotation;
    }*/

}
