package TAG.BackTracking;

import java.util.*;

public class _40__Combination_Sum_II {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        /*Set<List<Integer>> result1 = new HashSet<>();
        find1(result1, candidates, new ArrayList<>(), 0, target);
        return new ArrayList<>(result1);*/
        List<List<Integer>> result2 = new ArrayList<>();
        find2(result2, candidates, new ArrayList<>(), 0, target);
        return result2;
    }

    /**
     * 利用集合来去除重复项
     * @param result
     * @param candidates
     * @param had
     * @param inx
     * @param hopeNum
     */
    public void find1(Set<List<Integer>> result, int[] candidates, List<Integer> had, int inx, int hopeNum) {
        if(hopeNum < 0) {
            return;
        }
        if(hopeNum == 0) {
            result.add(new ArrayList<>(had));
            return;
        }
        for (int i = inx; i < candidates.length; i++) {
            had.add(candidates[i]);
            find1(result, candidates, had, i + 1, hopeNum - candidates[i]);
            had.remove(had.size() - 1);
        }
    }

    /**
     * 高大上的剪枝
     * @param result
     * @param candidates
     * @param had
     * @param inx
     * @param hopeNum
     */
    public void find2(List<List<Integer>> result, int[] candidates, List<Integer> had, int inx, int hopeNum) {
        if(hopeNum < 0) {
            return;
        }
        if(hopeNum == 0) {
            result.add(new ArrayList<>(had));
            return;
        }
        for (int i = inx; i < candidates.length; i++) {
            /*加粗标红 i > inx*/
            if(i > inx && candidates[i] == candidates[i - 1]) continue;
            had.add(candidates[i]);
            find2(result, candidates, had, i + 1, hopeNum - candidates[i]);
            had.remove(had.size() - 1);
        }
    }
}
