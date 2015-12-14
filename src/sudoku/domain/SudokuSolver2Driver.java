package sudoku.domain;

import java.util.Scanner;

/**
 * Created by Adri on 26/11/15.
 */
public class SudokuSolver2Driver {
    static SudokuBoard board;
    static SudokuSolver2 solv;
    static Scanner reader = new Scanner(System.in);
    private static void createBoard()  {
            System.out.println("Tamany(4, 9, 16): ");
            int size = reader.nextInt();
            board = new SudokuBoard(size);
            System.out.println("Introdueix el Sudoku");
            board.read();


    }
    private static void es_solucio_unica(){
        int n = solv.uniqueSolution(board);
        switch(n) {
            case 0:
                System.out.println("No te solucio ");
                break;
            case 1:
                System.out.println("Te solució pero no es unica");
                break;
            case 2:
                System.out.println("Te solucio unica");
                break;
            default: break;

        }
    }
    public static void main(String[] args) {
        System.out.println("Creeu el taulell: ");
        createBoard();
        System.out.println("Opcions:\n1.Resoldre taulell\n2.Comprovar si te solucio unica\n3.Sortir");
        int option;
        do {
            System.out.print("Opció: ");
            option = reader.nextInt();
            switch (option) {
                case 1:
                    solv.solve(board).print();
                    break;
                case 2:
                    es_solucio_unica();
                    break;
                default:
                    option = 3;
                    break;
            }
        } while (option != 3);
    }
}
