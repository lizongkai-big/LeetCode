package TAG.Sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 规模较小用归并，规模较大用快排
 */
public class UniSort {


    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.remove(list.lastIndexOf(2));
        int[] num = new int[10];
        Arrays.sort(num);
    }
}
