package TAG.BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _39__Combination_Sum {
    List<List<Integer>> result;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        result = new ArrayList<List<Integer>>();
        List<Integer> had = new ArrayList<Integer>();
        find(candidates, had, 0, target);
        return result;
    }

    public void find(int[] candidates, List<Integer> had, int inx, int hopeNum) {
        if(hopeNum < 0) return;
        if(hopeNum == 0) {
            result.add(new ArrayList<>(had));
            return;
        }
        // hopeNum > 0
        for(int i = inx; i < candidates.length; i ++) {
            int num = candidates[i];
            had.add(num);
            find(candidates, had, i, hopeNum - num);
            /*optimization*/
            /* had.remove(had.lastIndexOf(num)); */
            had.remove(had.size() - 1);
        }
    }
}
