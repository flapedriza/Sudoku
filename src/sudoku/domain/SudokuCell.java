package sudoku.domain;

/**
 * Created by Adri on 12/11/15.
 */

/** Class used to save the specific SudokuCell with Reg Col and Row
 */
public class SudokuCell extends Cell {
    Reg reg;
    Col col;
    Row row;
    public SudokuCell(Row fila, Col column, Reg regio) {
        super(column.valor,fila.valor);
        reg = regio;
        col = column;
        row = fila;
        value = 0;
    }
    /** Return Region Number*/
    public Reg getRegZone() {
        return reg;
    }
    /** Return Column Number*/
    public Col getColZone() {
        return col;
    }
    /** Return Row Number*/
    public Row getRowZone() {
        return row;
    }
}
