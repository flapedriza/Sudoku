package main.sudoku;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by Adri on 12/11/15.
 */
class SudokuBoard extends Board{
    static int size;
    static int max_annotations = 9; //màxim nombre de marques que poden tenir les celes del taulell
    static ArrayList<ArrayList<SudokuCell>> board = new ArrayList<>(size);
    static ArrayList<Row> rows;
    static ArrayList<Col> cols;
    static ArrayList<Reg> regs;

    public SudokuBoard(int size) {
        super(size); //Clase arreclada per afegir la regio a la cela.
        setSize(size);
        rows = new ArrayList<>(size);
        cols = new ArrayList<>(size);
        regs = new ArrayList<>(size);
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
            board.add(new ArrayList<SudokuCell>(size));
            for (int j = 0; j < size; ++j) {
                ++contFil;
                ++contSeg;
                if (contFil >= tam) {
                    ++reg;
                    contFil = 0;
                    if (reg > maxReg) maxReg = reg;
                }
                board.get(i).add(new SudokuCell(i,j,reg));
            }
        }
    }
    public enum Difficulty { EASY, NORMAL, HARD}

    @Override
    public void setValueCell(int value, int row, int column) {
        int reg = (int) (1 + ((column-1)/Math.sqrt(size)) + ((row-1)/Math.sqrt(size))*Math.sqrt(size));
        rows.get(row-1).usats.set(value-1, true);//true el valor a la row
        rows.get(row-1).falten.remove(value);
        cols.get(column-1).usats.set(value- 1, true);//Col
        cols.get(column-1).falten.remove(value);
        regs.get(reg-1).usats.set(value - 1, true);//Reg
        regs.get(reg-1).falten.remove(value);
    }

    public TreeSet<Integer> falten(int x, int y) {
        int reg = (int) (1 + ((y-1)/Math.sqrt(size)) + ((x-1)/Math.sqrt(size))*Math.sqrt(size));
        TreeSet<Integer> a = new TreeSet<>(rows.get(x-1).falten);
        TreeSet<Integer> b = new TreeSet<>(cols.get(y-1).falten);
        TreeSet<Integer> c = new TreeSet<>(cols.get(reg).falten);
        a.retainAll(b);
        a.retainAll(c);
        return a;

    }
    public void erase (int row,int col ){
        int value = board.get(row).get(col).getValue();
        int reg = (int) (1 + ((col-1)/Math.sqrt(size)) + ((row-1)/Math.sqrt(size))*Math.sqrt(size));
        rows.get(row-1).usats.set(value-1,false);
        rows.get(row-1).falten.add(value);
        cols.get(col-1).usats.set(value- 1, false);//Col
        cols.get(col-1).falten.add(value);
        regs.get(reg-1).usats.set(value - 1, false);//Reg
        regs.get(reg-1).falten.add(value);
        board.get(row).get(col).visible=false;
    }
    public static Reg getReg(int n){return regs.get(n-1);}
    public static Col getCol(int n) {return cols.get(n-1);}
    public static Row getRow(int n) {return rows.get(n-1);}

}
