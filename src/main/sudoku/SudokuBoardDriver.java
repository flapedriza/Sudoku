package main.sudoku;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by Francesc on 16/11/2015.
 */
public class SudokuBoardDriver {
    static SudokuBoard board;
    static Scanner reader = new Scanner(System.in);
    private static void createBoard() {
        System.out.println("Tamany: ");
        int size = reader.nextInt();
        board = new SudokuBoard(size);
    }

    private static void setValue() {
        System.out.println("Valor: ");
        int val = reader.nextInt();
        System.out.println("Fila: ");
        int row = reader.nextInt();
        System.out.println("Columna: ");
        int col = reader.nextInt();
        board.setValueCell(val, row, col);
    }

    private static void falten() {
        System.out.println("Fila: ");
        int row = reader.nextInt();
        System.out.println("Columna: ");
        int col = reader.nextInt();
        TreeSet<Integer> set = board.falten(row, col);
        Iterator<Integer> it = set.iterator();
        System.out.print("La cel·la pot assolir els següents valors: ");
        while (it.hasNext()) System.out.print(" "+it.next());
    }

    private static void erase() {
        System.out.println("Fila: ");
        int row = reader.nextInt();
        System.out.println("Columna: ");
        int col = reader.nextInt();
        board.erase(row, col);
    }

    private static void valueCell() {
        System.out.println("Fila: ");
        int row = reader.nextInt();
        System.out.println("Columna: ");
        int col = reader.nextInt();
        int val = board.getValueCell(row,col);
        System.out.println("La cel·la té el valor: "+val);
    }

    public static void main(String[] args) {
        System.out.println("Creeu el taulell: ");
        createBoard();
        System.out.println("Opcions:\n1.Assignar valor a cel·la\n2.Consultar possibles valors de la cel·la\n3.Eliminar valor de la cel·la\n4.Consultar valor de la cel·la\n5.Imprimir taulell\n6.Sortir");
        int option;
        do {
            option = reader.nextInt();
            switch (option) {
                case 1: setValue();
                    break;
                case 2: falten();
                    break;
                case 3: erase();
                    break;
                case 4: valueCell();
                    break;
                case 5: board.print();
                    break;
                default: option = 6;
                    break;
            }
        } while(option != 6);
    }
}
