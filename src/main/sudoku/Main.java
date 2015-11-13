package main.sudoku;

import java.util.HashMap;
import java.util.Map;

public class Main {

    private static int getmin(HashMap<Integer, Integer> map) {
        Map.Entry<Integer, Integer> min = null;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(min == null || min.getValue() > entry.getValue()) min = entry;
        }
        return min.getKey();
    }

    public static void main(String[] args) {
	// write your code here
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<10; ++i) map.put(i+1, 10);
        for(int i = 1; i<=10;++i) System.out.println(map.get(i));
        int val = map.get(2);
        map.put(1, val-3);
        map.put(2, val-1);
        System.out.println("------------");
        for(int i = 1; i<=10;++i) System.out.println(map.get(i));
        map.put(3, val-2);
        System.out.println("------------");
        for(int i = 1; i<=10;++i) System.out.println(map.get(i));
        System.out.print("MINIMUM: ");
        int min = getmin(map);
        System.out.println(min);





    }
}
