package sudoku.domain;


/**
 * Created with IntelliJ IDEA.
 * Date: 29/10/15
 * Time: 17:50
 * To change this template use File | Settings | File Templates.
 */


public class Cell {
    int value;
    int column; //numero de columna
    int row; //numero de fila
    boolean visible; //per veure si la casella és visible
    boolean written; //per veure si el valor ja venia donat en el joc o no

    public Cell(int row, int column) {
        this.column = column;
        this.row = row;
        written = false;
        visible = false;
        value = -1;
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
