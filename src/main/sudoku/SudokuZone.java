package main.sudoku;
import java.util.ArrayList;
import java.util.List;

abstract class SudokuZone {
    protected int size = Board.getSize();//com que es protected ja podem ferho servir a les subclases
    protected int valor; //a es el valor de la fila que busquem
    abstract void SudZone(int valor); //la a es la fila la columna o la regio que volem
}
class Row extends SudokuZone {
    public ArrayList<Cell> Celes = new ArrayList<Cell>(size);//ficar totes les celes al array de celes
    public ArrayList<Boolean> usados = new ArrayList<>(size); //Per marcar les caselles que estan posades
    public List<Integer> falten = new ArrayList<>(); //ficar els valors que falten

    void SudZone(int valor) {
        for (int j = 0; j < size; ++j) {falten.add(j);}
        for (int i = 0; i < size; i++) {
            int reg = Cell.getReg();
            int k = Board.getValueCell(valor, i, reg); //el valorde la cela es k
            Celes.add(new Cell(valor, i, reg));
            if (k != -1 && falten.contains(k)) {
                usados.set(i, true);
                falten.remove(k);
            }
        }
    }
}
class Col extends SudokuZone{
    public ArrayList<Cell> Celes = new ArrayList<Cell>(size);//ficar totes les celes al array de celes
    public ArrayList<Boolean> usados = new ArrayList<>(size); //Per marcar les caselles que estan posades
    public ArrayList<Integer> falten = new ArrayList<>(); //ficar els valors que falten
    void SudZone(int valor){
        for (int i = 0; i < size; i++){falten.add(i);}
        for (int i = 0; i < size; i++) {
            int reg = Cell.getReg();
            int k  = Board.getValueCell(i, valor, reg);
            Celes.add(new Cell(valor,i,reg));
            if (k != -1 && falten.contains(k)){
                usados.set(i,true);
                falten.remove(k);
            }
        }
    }
}
class Reg extends SudokuZone{
    public ArrayList<Cell> Celes = new ArrayList<Cell>(size);//ficar totes les celes al array de celes
    public ArrayList<Boolean> usados = new ArrayList<>(size); //Per marcar les caselles que estan posades
    public ArrayList<Integer> falten = new ArrayList<>(); //ficar els valors que falten
    double tam = size/Math.sqrt(size);
    void SudZone(int valor){
        for (int i = 0; i < tam; ++i) {
            for (int j = 0; j < tam; ++i){
                int reg = Cell.getReg();
                int k = Board.getValueCell(i,j,valor);
                Celes.add(new Cell(valor,i,reg));
                if (k != -1 && falten.contains(k)) {
                    usados.set(i,true);
                    falten.remove(k);
                }

            }
        }
    }
}
/*public class SudokuZone {
    public List SudokuZone(boolean fila, boolean col, boolean reg, int x) { //pasem un boolea depen del que volguem obtenir
        List<Integer> listEstan = new ArrayList<Integer>(); //Declarem la llista
        List<Integer> listFalten = new ArrayList<Integer>();
        int size = Board.getSize();
        double tam = size/Math.sqrt(size);
        for (int i = 0; i < size; i++){listFalten.add(i);}
        if (fila == true) { //vull les files
            for (int i = 0; i < size; i++) {
                int k  = Board.getValueCell1(x,i); //el valorde la cela es k
                if (k != -1 && listFalten.contains(k)) {
                    listEstan.add(k);
                    listFalten.remove(k);
                }
            }
        }
        else if (col == true) { //vull les columnes
            for (int i = 0; i < size; i++) {
                int k  = Board.getValueCell1(i,x);
                if (k != -1 && listFalten.contains(k)){
                    listEstan.add(k);
                    listFalten.remove(k);
                }
            }
        }
        else if (reg == true) { //vull les regions
            for (int i = 0; i < tam; ++i) {
                for (int j = 0; j < tam; ++i){
                    int k = Board.getValueCell(i,j,x);
                    if (k != -1 && listFalten.contains(k)) {
                        listEstan.add(k);
                        listFalten.remove(k);
                    }

                }
            }
        }
        return listEstan,listFalten;
    }
}*/


