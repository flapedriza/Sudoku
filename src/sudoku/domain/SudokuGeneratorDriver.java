package sudoku.domain;

import java.util.Scanner;

/**
 * Created by Francesc on 15/11/15.
 */
public class SudokuGeneratorDriver {
    static SudokuBoard board;
    static SudokuGenerator gen;
    static Scanner reader = new Scanner(System.in);

    private static void createBoard(){

        System.out.print("Tamany(4, 9, 16): ");
        int size = reader.nextInt();
                board = new SudokuBoard(size);
        System.out.print("Dificultat(Facil, Normal, Dificil): ");
        String dif = reader.next();
        SudokuGenerator.Difficulty difficulty;
        switch (dif) {
            case "Facil" : difficulty = SudokuGenerator.Difficulty.EASY;
                break;
            case "Normal": difficulty = SudokuGenerator.Difficulty.NORMAL;
                break;
            case "Dificil": difficulty = SudokuGenerator.Difficulty.HARD;
                break;
            default: difficulty = SudokuGenerator.Difficulty.NORMAL;
                break;
        }
        gen = new SudokuGenerator(difficulty, size);
    }

    private static void generate() {
        board.clear();
        gen.generate(board);
    }

    private static void setDifficulty()
    {
        String dif = reader.next();
        SudokuGenerator.Difficulty difficulty;
        switch (dif) {
            case "Facil" : difficulty = SudokuGenerator.Difficulty.EASY;
                break;
            case "Normal": difficulty = SudokuGenerator.Difficulty.NORMAL;
                break;
            case "Dificil": difficulty = SudokuGenerator.Difficulty.HARD;
                break;
            default: difficulty = SudokuGenerator.Difficulty.NORMAL;
                break;
        }
        gen.setDifficulty(difficulty);

    }

    private static void removeCells() {
        System.out.println("Quantes caselles cal esborrar?");
        int val = reader.nextInt();
        gen.removeCells(board, val);
    }


    private static void create() {
        board.clear();
        gen.create(board, 1);
    }

    public static void main(String[] args) {
        System.out.println("Creeu el taulell: ");
        createBoard();
        System.out.println("Opcions:\n1.Generar Taulell\n2.Seleccionar la dificultat\n3.esborrar caselles\n4.Crear el sudoku\n5.Imprimir taulell\n6.Sortir");
        int option;
        do {
            System.out.print("Opció: ");
            option = reader.nextInt();
            switch (option) {
                case 1:
                    generate();
                    break;
                case 2:
                    setDifficulty();
                    break;
                case 3:
                    removeCells();
                    break;
                case 4:
                    create();
                    break;
                case 5: board.print();
                    break;
                default:
                    option = 6;
                    break;
            }
        } while (option != 6);
    }
}


