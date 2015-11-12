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
            int k = Board.getValueCell(valor, i); //el valorde la cela es k
            Celes.add(new Cell(valor, i));
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
            int k  = SudokuBoard.getValueCell(i, valor);
            Celes.add(new Cell(valor,i));
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
                int k = Board.getValueCell(i,j);
                Celes.add(new SudokuCell(i,j,valor));
                if (k != -1 && falten.contains(k)) {
                    usados.set(i,true);
                    falten.remove(k);
                }

            }
        }
    }
   public void retornusats(ArrayList<Boolean> used){
       //VOLS Que usats sigui int i falten bool? o ho deixo aixi?? mirat sudoku board perque hi ha algo que no li agrada al IntelIJ
   }
}

