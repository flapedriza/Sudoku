package main.sudoku;

import java.util.Scanner;

/**
 * Created by Francesc on 14/11/2015.
 */
public class SudokuCellDriver {
    static SudokuCell cell;

    private static void printValue() {
        System.out.println("El valor de la cel·la és: "+cell.getValue());
    }

    private static void setValue() {
        Scanner reader = new Scanner(System.in);
        int value = reader.nextInt();
        cell.setValue(value);
    }

    private static void printColumn() {
        System.out.println("La cel·la es troba a la columna: "+cell.getColumn());
    }

    private static void printRow() {
        System.out.println("La cel·la es troba a la fila: "+cell.getRow());
    }

    private static void isWritten() {
        String Message;
        if(cell.getWritten()) Message = "La cel·la està escrita";
        else Message = "La cel·la no està escrita";
        System.out.println(Message);
    }

    private static void setWritten() {
        cell.switchWritten();
    }

    private static void setVisible() {
        cell.switchVisible();
    }

    private static void isVisible() {
        String Message;
        if(cell.getVisible()) Message = "La cel·la és visible";
        else Message = "La cel·la no és visible";
        System.out.println(Message);
    }

    private static void printReg() {
        System.out.println("La cel·la està a la regió: "+cell.getRegZone().valor);
    }

    public static void main(String[] args) {
        /*cell = new SudokuCell(1, 1, 1);
        setValue();
        printColumn();
        printReg();
        printRow();
        printValue();*/

    }
}
