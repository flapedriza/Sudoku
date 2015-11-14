package main.sudoku;

/**
 * Created by Adri on 12/11/15.
 */
public class SudokuCell extends Cell {
    Reg reg;
    Col col;
    Row row;
    public SudokuCell(int fila, int column, int regio) {
        super(column,fila);
        reg = SudokuBoard.getReg(regio);
        col = SudokuBoard.getCol(column);
        row = SudokuBoard.getRow(fila);
        for (int i = 0; i < annotations.length; i++) annotations[i] = false;
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
