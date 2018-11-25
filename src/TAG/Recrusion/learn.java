package TAG.Recrusion;

import java.util.ArrayList;
import java.util.List;

public class learn {
    List<List<Integer>> lists;
    public int step(int total) {
        lists = new ArrayList<>();
        List<Integer> hadGone = new ArrayList<>();
        go(total, hadGone);
        return lists.size();
    }

    public void go(int left, List<Integer> hadGone) {
        if(left == 0)
            lists.add(hadGone);
        if(left < 0)
            return;
        List<Integer> tmp1 = hadGone;
        tmp1.add(1);
        go(left - 1, tmp1);
        List<Integer> tmp2 = hadGone;
        tmp2.add(1);
        go(left - 2, tmp2);
    }
    public int f(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return f(n - 1) + f(n - 2);
    }

    public static void main(String[] args) {
        learn l = new learn();
        System.out.println(l.f(7));
        System.out.println(l.step(7));
    }
}
