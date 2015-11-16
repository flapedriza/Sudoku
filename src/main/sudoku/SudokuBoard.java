package main.sudoku;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by Adri on 12/11/15.
 */

class InvalidNumberInCellException extends Exception {
    public InvalidNumberInCellException() { super(); }
}
class SudokuBoard extends Board{
    ArrayList<ArrayList<SudokuCell>> board;
    ArrayList<Row> rows;
    ArrayList<Col> cols;
    ArrayList<Reg> regs;

    public SudokuBoard(int size) {
        super(size);
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
                board.get(i).add(new SudokuCell(rows.get(i),cols.get(j),regs.get(reg-1)));
            }
        }
    }

    @Override
    public boolean setValueCell(int value, int row, int column) {
        int reg = region(row, column);
        Row rowz = rows.get(row-1);
        Col colz = cols.get(column-1);
        Reg regz = regs.get(reg-1);

        if(!rowz.usats.get(value-1) && !colz.usats.get(value-1) && !regz.usats.get(value-1)) {
            board.get(row-1).get(column-1).setValue(value);
            rowz.add(value);
            colz.add(value);
            regz.add(value);
            return true;
        }
        else return false;
    }

    public TreeSet<Integer> falten(int x, int y) {
        int reg = region(x, y);
        TreeSet<Integer> a = new TreeSet<>(rows.get(x-1).falten);
        TreeSet<Integer> b = new TreeSet<>(cols.get(y-1).falten);
        TreeSet<Integer> c = new TreeSet<>(regs.get(reg-1).falten);
        a.retainAll(b);
        a.retainAll(c);
        return a;

    }
    public void erase (int row,int column ){
        int reg = region(row, column);
        Row rowz = rows.get(row-1);
        Col colz = cols.get(column-1);
        Reg regz = regs.get(reg-1);
            int valold = board.get(row-1).get(column-1).getValue();
            board.get(row-1).get(column-1).setValue(0);
            if(valold != 0) {
                rowz.remove(valold);
                colz.remove(valold);
                regz.remove(valold);
            }
    }


    public void print() {
        int sqroot = (int) (Math.sqrt(size));
        //System.out.println();

        for (int i = 0; i < size; ++i) {
            if (i%sqroot == 0 && i != 0) {
                for (int j = 0; j < size; ++j) {
                    if (j % sqroot == 0 && j != 0)
                        System.out.print(" +");
                    System.out.print(" -");
                }
                System.out.println();
            }
            for (int j = 0; j < size; ++j) {
                if (j%sqroot == 0 && j != 0)
                    System.out.print(" |");
                System.out.print(" "+printableCell(i,j));
            }
            System.out.println();
        }
        System.out.println();
    }

    public char printableCell(int r, int c) {
        int value = getValueCell(r,c);
        if (value == 0) return '·';
        if (value < 10) return (char) ('0'+value);
        else if (value == 16) return '0';
        else return (char) ('A' + (value - 10));
    }

    public void clear() {
        for(int i=0;i<size;++i) {
            for(int j=0;j<size;++j) erase(i+1, j+1);
        }
    }

    public void read() throws InvalidNumberInCellException {
        Scanner reader = new Scanner(System.in);
        for(int i=0;i<size;++i) {
            for(int j=0;j<size;++j) {
                int val = reader.nextInt();
                if(!setValueCell(val, i+1, j+1)) throw new InvalidNumberInCellException();
            }
        }
    }





    @Override
    public int getValueCell(int row, int column) {
        return board.get(row).get(column).getValue();
    }
    public Reg getReg(int n){return regs.get(n-1);}
    public Col getCol(int n) {return cols.get(n-1);}
    public Row getRow(int n) {return rows.get(n-1);}

    private int region(int row, int column) {
        int tam = (int) (Math.sqrt(size));
        return 1 + (column-1)/tam + ((row-1)/tam)*tam;
    }


    public int getSudokuSize() {return regs.size();}

}
