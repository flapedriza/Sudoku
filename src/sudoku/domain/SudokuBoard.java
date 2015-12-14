package sudoku.domain;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Clas SudokuBoard used to make sudokus and his functionalities
 */
class SudokuBoard {
    int size;
    ArrayList<ArrayList<SudokuCell>> board;
    TreeSet<Integer> buides;
    ArrayList<Row> rows;
    ArrayList<Col> cols;
    ArrayList<Reg> regs;

    /**
     * Make the boards and the Zones.
     * @param size
     * @throws OutOfRangeException
     */
    public SudokuBoard(int size)  throws OutOfRangeException  {
        this.size = size;
        if (size != 9 && size != 16 && size != 4) throw new OutOfRangeException();
        board = new ArrayList<>();
        rows = new ArrayList<>();
        cols = new ArrayList<>();
        regs = new ArrayList<>();
        buides = new TreeSet<>();
        for(int i = 1; i<=size;++i) {
            for(int j = 1; j<=size;++j) buides.add(numFromRowCol(new Pair(i, j)));
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

    SudokuBoard(String board, int size) throws OutOfRangeException {
        this(size);
        String[] temp = board.split("-");
        for(int i = 1; i<= size; ++i) {
            for(int j = 1; j<= size; ++j){
                setValueCell(Integer.parseInt(temp[numFromRowCol(new Pair(i, j))]), i, j);
            }
        }
    }

    /**
     * The cells takes values for a board.
     * @param board
     * @throws OutOfRangeException
     */
    public SudokuBoard(SudokuBoard board) throws OutOfRangeException {
        this(board.getSudokuSize());
        for(int i=1;i<=size;++i) {
            for(int j=1;j<=size;++j) {
                int val = board.getValueCell(i-1, j-1);
                setValueCell(val, i, j);
            }
        }
    }

    /**
     * Compare two Sudokus and return a bool if was the same
     * @param b2 was a SudokuBoard copy
     */
    public boolean equals(SudokuBoard b2) {
        if (this.size != b2.getSudokuSize()) return false;

        for (int fila = 1; fila <= size; ++fila)
            for (int columna = 1; columna <= size; ++columna)
                if (this.getValueCell(fila-1,columna-1) != b2.getValueCell(fila-1,columna-1)) return false;
        return true;
    }

    /**
     * Function to take value for a Cell
     * @param value was the value for the cell
     * @param row
     * @param column
     * @throws OutOfRangeException
     */
    public boolean setValueCell(int value, int row, int column) throws OutOfRangeException {
        if(row <= 0 || row > size || column <= 0 || column > size) throw new OutOfRangeException();
        if(value < 0 || value > size) throw new OutOfRangeException();
        if(value == 0) {
            erase(row, column);
            return true;
        }
        if(!falten(row, column).contains(value)) return false;
        else {
            buides.remove(numFromRowCol(new Pair(row, column)));
            board.get(row-1).get(column-1).setValue(value);
            int reg = region(row, column);
            Row rowz = rows.get(row-1);
            Col colz = cols.get(column-1);
            Reg regz = regs.get(reg-1);
            rowz.add(value);
            colz.add(value);
            regz.add(value);
            return true;
        }
    }

    public TreeSet<Integer> getBuides() {
        return buides;
    }

    /**
     * A function to make a intersection with diferent zones
     * @param x
     * @param y
     * @return
     * @throws OutOfRangeException
     */
    public TreeSet<Integer> falten(int x, int y) throws OutOfRangeException{
        int reg = region(x, y);
        TreeSet<Integer> a = new TreeSet<>(rows.get(x-1).falten);
        TreeSet<Integer> b = new TreeSet<>(cols.get(y-1).falten);
        TreeSet<Integer> c = new TreeSet<>(regs.get(reg-1).falten);
        a.retainAll(b);
        a.retainAll(c);
        return a;

    }

    /**
     * Function that actualize the Zones and the Board.
     * @param row
     * @param column
     * @throws OutOfRangeException
     */
    public void erase (int row,int column ) throws OutOfRangeException{
        buides.add(numFromRowCol(new Pair(row, column)));
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

    public String toString() {
        String ret = "";
        for(int i = 0; i<size; ++i) {
            for(int j = 0; j<size; ++j) {
                if(i == 0 && j == 0) ret += getValueCell(i, j).toString();
                else {
                    String temp = "-"+getValueCell(i, j).toString();
                    ret += temp;
                }
            }
        }
        return ret;
    }


    /**
     * Print the SudokuBoard and the table
     */
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

    /**
     * Print the Values of Cells
     * @param r
     * @param c
     * @return
     */
    private char printableCell(int r, int c) {
        int value = getValueCell(r,c);
        if (value == 0) return '·';
        if (value < 10) return (char) ('0'+value);
        else if (value == 16) return '0';
        else return (char) ('A' + (value - 10));
    }

    /**
     * Function to clear the board
     */
    public void clear() {
        for(int i=0;i<size;++i) {
            for(int j=0;j<size;++j)
                try {
                    erase(i+1, j+1);
                } catch (OutOfRangeException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * Read the board that was introduced
     * @throws InvalidNumberInCellException
     * @throws OutOfRangeException
     */
    public void read() throws InvalidNumberInCellException, OutOfRangeException {
        Scanner reader = new Scanner(System.in);
        for(int i=0;i<size;++i) {
            for(int j=0;j<size;++j) {
                int val = reader.nextInt();
                if(val == 0) erase(i+1, j+1);
                else if(!setValueCell(val, i+1, j+1)) throw new InvalidNumberInCellException();
            }
        }
    }

    /**
     * Returns the value of the cell
     * @param row
     * @param column
     */
    public Integer getValueCell(int row, int column) {
        return board.get(row).get(column).getValue();
    }

    /**
     * Function that calcule the Region for a Cell
     * @param row
     * @param column
     * @return
     */
    private int region(int row, int column) {
        int tam = (int) (Math.sqrt(size));
        return 1 + (column-1)/tam + ((row-1)/tam)*tam;
    }


    private int numFromRowCol(Pair rc) {
        int a = rc.first - 1;
        int b = rc.second -1;
        return a*size+b+1;
    }

    public int getSudokuSize() {return this.size;}
}