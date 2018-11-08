package TAG.BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _46__Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(res, new ArrayList<Integer>(), nums);
        return res;
    }

    public void backtrack(List<List<Integer>> res, List<Integer> tmpList, int[] nums) {
        if (tmpList.size() == nums.length) {
            res.add(new ArrayList<>(tmpList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (tmpList.contains(nums[i])) continue;
                tmpList.add(nums[i]);
                backtrack(res, tmpList, nums);
                tmpList.remove(tmpList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        _46__Permutations permutations = new _46__Permutations();
        List<List<Integer>> lists = (permutations.permute(new int[]{1, 2, 3, 4, 7}));
        for (List<Integer> list : lists)
            System.out.println(list);
    }
}
