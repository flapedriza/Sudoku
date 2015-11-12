package main.sudoku;
import java.util.*;

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


        @Override
        public void setValueCell(int value, int row, int column,int reg) {
            rows.get(row-1).usados.set(value-1, true);//true el valor a la row
            rows.get(row-1).falten.add(value);
            //rows.get(row-1).Celes.get; CELES HOFEM SERVIR O NO?????
            cols.get(column-1).usados.set(value-1, true);//Col
            cols.get(column-1).falten.add(value);
            //cols.get(column-1).Celes.;
            regs.get(reg-1).usados.set(value-1, true);//Reg
            regs.get(reg-1).falten.add(value);
            //regs.get(reg-1).Celes.
        }

        public static Reg getReg(int n){return regs.get(n-1);}
        public static Col getCol(int n) {return cols.get(n-1);}
        public static Row getRow(int n) {return rows.get(n-1);}
}

