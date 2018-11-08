package TAG.BackTracking;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _47__Permutations_II {
    public List<List<Integer>> permuteUnique_lower(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        backtrack(res, new ArrayList<Integer>(), nums);
        return new ArrayList<List<Integer>>(res);
    }

    public void backtrack(Set<List<Integer>> res, List<Integer> inxList, int[] nums) {
        if (inxList.size() == nums.length) {
            List<Integer> tmpList = new ArrayList<>();
            for (int i : inxList) {
                tmpList.add(nums[i]);
            }
            res.add(tmpList);
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (inxList.contains(i)) continue;
                inxList.add(i);
                backtrack(res, inxList, nums);
                inxList.remove(inxList.size() - 1);
            }
        }
    }

    public List<List<Integer>> permuteUnique_Higher(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return res;
        boolean[] used = new boolean[nums.length];
        List<Integer> list = new ArrayList<Integer>();
        Arrays.sort(nums);
        dfs(nums, used, list, res);
        return res;
    }

    public void dfs(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> res) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            /* when a number has the same value with its previous, we can use this number only if his previous is used */
            if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) continue;
            used[i] = true;
            list.add(nums[i]);
            dfs(nums, used, list, res);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }

    public List<List<Integer>> permuteUnique_Higher_(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        dfs(nums, list, res, 0);
        return new ArrayList<>(res);
    }

    public void dfs(int[] nums, List<Integer> list, Set<List<Integer>> res, int start) {
        if (start == nums.length) {
            // int[] -> List<Integer>() boxed()相当于是个装箱的操作，基本数据类型的数据不能直接collect
            res.add(new ArrayList<Integer>(Arrays.stream(nums).boxed().collect(Collectors.toList())));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            // i和start不相同，但是nums[i]和nums[start]相同的情况下跳过，继续下一个循环
            if(i != start && nums[i] == nums[start]) continue;
            int t = nums[i];
            nums[i] = nums[start];
            nums[start] = t;
            dfs(nums, list, res, start + 1);
            nums[start] = nums[i];
            nums[i] = t;
        }
    }

    public static void main(String[] args) {
        _47__Permutations_II permutationsII = new _47__Permutations_II();
        List<List<Integer>> lists = permutationsII.permuteUnique_Higher(new int[]{1,1, 2, 3, 4, 7});

        for (List<Integer> list : lists)
            System.out.println(list);
    }
}
