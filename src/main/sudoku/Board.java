package main.sudoku;
import java.util.*;
/**
 * Created by Adri on 6/11/15.
 */
public class Board {
    static int size;
    static int max_annotations = 9; //màxim nombre de marques que poden tenir les celes del taulell
    static ArrayList<ArrayList<Cell>> board = new ArrayList<>(size);

    public Board(int size) { //Clase arreclada per afegir la regio a la cela.
        setSize(size);
        double tam = Math.sqrt(size);
        int reg = 1;
        int contFil = 0;
        int maxReg = 0;
        int contSeg = 0; //tamany de una fila de regions
        for (int i =0;i < size;++i) {
            if (contSeg >= size*tam) {
                contSeg = 0;
                reg = maxReg+1;
            }
            if (contSeg < size* tam) reg -= tam-1;
            board.add(new ArrayList<Cell>(size));
            for (int j = 0; j < size; ++j) {
                ++contFil;
                ++contSeg;
                if (contFil >= tam) {
                    ++reg;
                    contFil = 0;
                    if (reg > maxReg) maxReg = reg;
                }
                board.get(i).add(new Cell(i,j,reg));
            }
        }
    }

    public static int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static int getValueCell(int row, int column, int reg) {
        return board.get(row).get(column).get(reg).getValue();
    }

    public int getValue() {
        return this.getValue();
    }
    public void setValueCell(int value, int row, int column, int reg) {
        board.get(row).get(column).get(reg).setValue(value);
    }

    public static int consult_max_annotations(){
        return max_annotations;
    }
}
