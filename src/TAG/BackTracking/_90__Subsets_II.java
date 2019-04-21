package TAG.BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _90__Subsets_II {
    /**
     * 递归生成顺序 [], [1], [1 2], [1 2 3], [1 3], [2], [2 3], [3]
     *                         []
     *                    /     |     \
     *                   /      |      \
     *                  /       |       \
     *               [1]       [2]       [3]
     *            /     \       \
     *           /       \      \
     *        [1 2]     [1 3]  [2 3]
     *       /
     *   [1 2 3]
     *
     *   [1, 2, 2] 的生成顺序
     *                         []
     *                    /     |
     *                   /      |
     *                  /       |
     *               [1]       [2]
     *            /             \
     *           /              \
     *        [1 2]           [2 2]
     *       /
     *   [1 2 2]
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup_dfs(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result; // edge case

        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();

        // dfs with depth = 0
        result.add(new ArrayList<>(list));
        dfs(result, list, nums, 0);
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> list, int[] nums, int depth) {
        for (int i = depth; i < nums.length; i++) {
            // 剪枝 是i > depth，而不是i>0
            if (i > depth && nums[i] == nums[i - 1]) continue; // IMPORTANT, skip duplicate: i > depth && nums[i] == nums[i - 1]
            list.add(nums[i]);
            result.add(new ArrayList<>(list));
            dfs(result, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 迭代
     * 公式：当前层集合 = 上一层的结果集 +
     * (当前元素不是重复的 ? 往上一层的结果集中分别加入新的元素得到的新集合 :
     * 只往上一层新产生的结果集加入新元素;)
     *
     * 如[1,2,2]
     * 首先往结果集中放入空集合[]；之后往空集合中的每个集合依次放入数组中的数字，得到新的集合，放入结果集中
     * 先往结果集中依次放入1，得到[1]，此时结果集为[], [1]
     * 再往结果集中依次放入2，得到[2], [1,2]，此时结果集为[], [1], [2], [1,2]
     * （因为2是重复的，如果直接往结果集加入2，则有[2] [1 2] [2 2] [1 2 2]，产生重复）
     * 因此只往上层新产生的子集合中加入2，得到[2 2], [1 2 2]，此时结果集为[], [1], [2], [1,2], [2,2], [1,2,2]
     * 至此结束
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup_Iteration(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        result.add(list);
        int last = 0;
        int oldSize = 0;
        for(int i = 0; i < nums.length; i ++) {
            if(i == 0 || nums[i] != last) {
                last = nums[i];
                oldSize = result.size();
            }
            int newSize = result.size();
            for(int j = oldSize-newSize; j < newSize; j ++) {
                List<Integer> newList = new ArrayList(result.get(j));
                newList.add(nums[i]);
                result.add(newList);
            }
        }
        return result;
    }
}
