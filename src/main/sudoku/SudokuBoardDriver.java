package main.sudoku;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by Francesc on 15/11/2015.
 */
public class SudokuBoardDriver {
    static SudokuBoard board;
    static Scanner reader = new Scanner(System.in);
    private static void createBoard()  {
        for (;;) {
            System.out.println("Tamany(4, 9, 16): ");
            int size = reader.nextInt();
            try {
                board = new SudokuBoard(size);
                break;
            } catch (OutOfRangeException e) {
                System.out.println("Tamany no vàlid, torneu-ho a intentar");
            }
        }
    }

    private static void setValue() {
        for (;;) {
            System.out.println("Valor: ");
            int val = reader.nextInt();
            System.out.println("Fila: ");
            int row = reader.nextInt();
            System.out.println("Columna: ");
            int col = reader.nextInt();
            try {
                for (;;) {
                    if(board.setValueCell(val, row, col)) break;
                    else {
                        System.out.println("Aquest valor no es pot assignar a la cel·la, ja que incompleix les restriccions del sudoku");
                        System.out.print("Seleccioneu un altre valor: ");
                        val = reader.nextInt();
                    }
                }
                break;
            } catch (OutOfRangeException e) {
                System.out.println("La fila/columna seleccionada o el valor  es trova fora del rang de valors possibles, torneu-ho a provar");
            }
        }
    }

    private static void falten() {
        for(;;) {
            System.out.println("Fila: ");
            int row = reader.nextInt();
            System.out.println("Columna: ");
            int col = reader.nextInt();
            try {
                TreeSet<Integer> set = board.falten(row, col);
                Iterator<Integer> it = set.iterator();
                System.out.print("La cel·la pot assolir els següents valors: ");
                while (it.hasNext()) System.out.print(" " + it.next());
                System.out.println();
                break;
            } catch (OutOfRangeException u) {
                System.out.print("Has introduit una fila o una columna erronea. ");
            }
        }
    }

    private static void erase() {
        for (; ; ) {
            System.out.println("Fila: ");
            int row = reader.nextInt();
            System.out.println("Columna: ");
            int col = reader.nextInt();
            try {
                board.erase(row, col);
                break;
            } catch (OutOfRangeException colrow) {
                    System.out.println("Fila o columna invalida, torna-ho a intentar.");
            }
        }
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
        System.out.println("Opcions:\n1.Llegir sudoku\n2.Assignar valor a cel·la\n3.Consultar possibles valors de la cel·la\n4.Eliminar valor de la cel·la\n5.Consultar valor de la cel·la\n6.Imprimir taulell\n7.Esborrar sudoku\n8.Sortir");
        int option;
        do {
            System.out.print("Opció: ");
            option = reader.nextInt();
            switch (option) {
                case 1: for(;;) {
                    try {
                        board.read();
                        break;
                    } catch(InvalidNumberInCellException | OutOfRangeException ex) {
                        System.out.println("S'ha assignat un valor il·legal o fora de rang a una cel·la, torneu a introduïr el sudoku");
                        board.clear();
                    }
                }
                    break;
                case 2: setValue();
                    break;
                case 3: falten();
                    break;
                case 4: erase();
                    break;
                case 5: valueCell();
                    break;
                case 6: board.print();
                    break;
                case 7: board.clear();
                    break;
                default: option = 8;
                    break;
            }
        } while(option != 8);
    }
}
