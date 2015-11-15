package main.sudoku;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by Adri on 12/11/15.
 */
class SudokuBoard extends Board{
    static ArrayList<ArrayList<SudokuCell>> board;
    static ArrayList<Row> rows;
    static ArrayList<Col> cols;
    static ArrayList<Reg> regs;

    public SudokuBoard(int size) {
        super(size); //Clase arreclada per afegir la regio a la cela.
        board = new ArrayList<>();
        rows = new ArrayList<>();
        cols = new ArrayList<>();
        regs = new ArrayList<>();
        for(int i = 1; i<=size;++i) {
            rows.add(new Row(size, i));
            cols.add(new Col(size, i));
            regs.add(new Reg(size, i));
        }
        for(int i=0;i<size;++i) {
            board.add(new ArrayList<SudokuCell>());
            for(int j=0;j<size;++j) {
                int reg = region(i+1,j+1);
                board.get(i).add(new SudokuCell(i+1,j+1,reg));
            }
        }
        /*double tam = Math.sqrt(size);
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
        }*/
    }

    @Override
    public void setValueCell(int value, int row, int column) {
        int reg = region(row, column);
        rows.get(row-1).usats.set(value-1, true);//true el valor a la row
        rows.get(row-1).falten.remove(value);
        cols.get(column-1).usats.set(value- 1, true);//Col
        cols.get(column-1).falten.remove(value);
        regs.get(reg-1).usats.set(value - 1, true);//Reg
        regs.get(reg-1).falten.remove(value);
    }

    public TreeSet<Integer> falten(int x, int y) {
        int reg = region(x, y);
        System.out.println("row: "+x);
        TreeSet<Integer> a = new TreeSet<>(rows.get(x-1).falten);
        TreeSet<Integer> b = new TreeSet<>(cols.get(y-1).falten);
        TreeSet<Integer> c = new TreeSet<>(regs.get(reg-1).falten);
        a.retainAll(b);
        a.retainAll(c);
        return a;

    }
    public void erase (int row,int col ){
        int value = board.get(row).get(col).getValue();
        int reg = region(row, col);
        rows.get(row-1).usats.set(value-1,false);
        rows.get(row-1).falten.add(value);
        cols.get(col-1).usats.set(value- 1, false);//Col
        cols.get(col-1).falten.add(value);
        regs.get(reg-1).usats.set(value - 1, false);//Reg
        regs.get(reg-1).falten.add(value);
        board.get(row).get(col).visible=false;
    }

    /*public int getValueCell(int x, int y) {
        return board.get(x-1).get(y-1).getValue();
    }*/

    public void print() {
        int bar = (int) (Math.sqrt(size));
        for(int i=0;i<size;++i) {
            System.out.print(getValueCell(i,0));
            for(int j=1;j<size;++j) {
                System.out.print(" "+getValueCell(i,j));
                if((j+1)%bar == 0 && j != size-1) System.out.print(" |");
            }
            System.out.println();
            if((i+1)%bar == 0 && i!= size-1) System.out.println("- - - + - - - + - - -");

        }
    }
    public static Reg getReg(int n){return regs.get(n-1);}
    public static Col getCol(int n) {return cols.get(n-1);}
    public static Row getRow(int n) {return rows.get(n-1);}

    private int region(int row, int column) {
        int tam = (int) (Math.sqrt(size));
        return 1 + (column-1)/tam + ((row-1)/tam)*tam;
    }

}
