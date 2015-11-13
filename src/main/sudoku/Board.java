package main.sudoku;
import java.util.*;


/**
 * Created with IntelliJ IDEA.
 * Date: 29/10/15
 * Time: 17:48
 * To change this template use File | Settings | File Templates.
 */


public class Board {
    static int size;
    static int max_annotations = 9; //màxim nombre de marques que poden tenir les celes del taulell
    static ArrayList<ArrayList<Cell>> board = new ArrayList<ArrayList<Cell>>(size);

    public Board(int size) {
        setSize(size);
        for (int i=0;i < size;++i) {
            board.add(new ArrayList<Cell>(size));
            for (int j = 0; j < size; ++j) {
                board.get(i).add(new Cell(i,j));
            }
        }
    }

    public static int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static int getValueCell(int row, int column) {return (board.get(row).get(column).getValue());}

    public int getValue() {
        return this.getValue();
    }

    public void setValueCell(int value, int row, int column) {
        board.get(row).get(column).setValue(value);
    }

    public static int consult_max_annotations(){
        return max_annotations;
    }
}
