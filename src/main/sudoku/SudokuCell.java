package main.sudoku;

/**
 * Created by Adri on 12/11/15.
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

    public Reg getRegZone() {
        return reg;
    }

    public Col getColZone() {
        return col;
    }

    public Row getRowZone() {
        return row;
    }
}
