package TAG.BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 子集合
 * 时间复杂度都是O(n * 2^n) https://www.jiuzhang.com/qa/1667/
 */
public class _78__Subsets {
    /**
     * 递归
     * 子集合生成顺序：
     * []
     * [1], [2], [3]
     * [1,2], [1,3], [2,3]
     * [1,2,3]
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets_Recursive_1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        // 依次生成i大小的子集合
        for(int i = 0; i <= nums.length; i ++) {
            backtrack(res, new ArrayList<>(), 0, nums, i);
        }
        return res;
    }

    /**
     * 递归函数，递归生成needLen大小的子集合
     * @param res 结果集
     * @param part 子集合
     * @param inx 子集合 nums的起始下标
     * @param nums 数组
     * @param needLen 子集合的目标大小
     */
    public void backtrack(List<List<Integer>> res, List<Integer> part, int inx, int[] nums, int needLen) {
        // 如果part的大小以及和needLen相同，则放入结果集
        if(part.size() == needLen) {
            res.add(new ArrayList(part));
            return;
        }
        // 往结果集中依次放入nums[inx...end)
        for(int i = inx; i < nums.length; i++) {
            part.add(nums[i]);
            backtrack(res, part, i+1, nums, needLen);
            part.remove(part.size()-1);
        }
    }

    /**
     * 原集合中每一个元素在子集中有两种状态：要么存在、要么不存在。
     * 这样构造子集的过程中每个元素就有两种选择方法：选择、不选择，因此可以构造一颗二叉树
     * 子集合生成顺序
     * [1,2,3],[1,2],[1,3],[1],[2,3],[2],[3],[]
     *                         []
     *                    /          \
     *                   /            \
     *                  /              \
     *               [1]                []
     *            /       \           /    \
     *           /         \         /      \
     *        [1 2]       [1]       [2]     []
     *       /     \     /   \     /   \    / \
     *   [1 2 3] [1 2] [1 3] [1] [2 3] [2] [3] []
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets_Recursive_2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(res, new ArrayList<>(), nums, 0);
        return res;
    }
    public void backtrack(List<List<Integer>> res, List<Integer> list, int[] nums, int inx) {
        if(inx == nums.length)
            res.add(new ArrayList<>(list));
        // 要nums[inx]
        list.add(nums[inx]);
        backtrack(res, list, nums, inx+1);
        list.remove(list.size() - 1);
        // 不要nums[inx]
        backtrack(res, list, nums, inx+1);
    }

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
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets_Recursive_3(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(res, new ArrayList<>(), nums, 0);
        return res;
    }
    public void dfs(List<List<Integer>> res, List<Integer> list, int[] nums, int inx) {
        res.add(new ArrayList<>(list));
        for (int i = inx; i < nums.length; i++) {
            list.add(nums[inx]);
            dfs(res, list, nums, inx+1);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 迭代
     * 公式：当前层集合 = 上一层的结果集 + 往上一层的结果集中分别加入新的元素得到的新集合
     * 如[1,2,3]
     * 首先往结果集中放入空集合[]；之后往空集合中的每个集合依次放入数组中的数字，得到新的集合，放入结果集中
     * 先往结果集中依次放入1，得到[1]，此时结果集为[], [1]
     * 再往结果集中依次放入2，得到[2], [1,2]，此时结果集为[], [1], [2], [1,2]
     * 再往结果集中依次放入3，得到[3], [1,3], [2,3], [1,2,3]，此时结果集为[], [1], [2], [1,2], [3], [1,3], [2,3], [1,2,3]
     * 至此结束
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets_Iteration(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        result.add(list);
        for(int i = 0; i < nums.length; i ++) {
            int size = result.size();
            for(int j = 0; j < size; j ++) {
                List<Integer> newList = new ArrayList(result.get(j));
                newList.add(nums[i]);
                result.add(newList);
            }
        }
        return result;
    }

    /**
     * 位操作
     * 观察迭代生成的结果集：[], [1], [2], [1,2], [3], [1,3], [2,3], [1,2,3]
     * Using [1, 2, 3] as an example, 1 appears once in every two consecutive subsets, 2 appears twice in every four
     * consecutive subsets, and 3 appears four times in every eight subsets (initially all subsets are empty):
     * 数字1每2个连续的子集合出现1次
     * 数字2每4个连续的子集合出现2次
     * 数字3每8个连续的子集合出现4次
     * 这分别与0-7的二进制形式（000 001 010 011 100 101 110 111）中最低位、中间位、最高位中1出现的频率是相同的
     * 因此利用这种性质来生成子集合
     *
     * 另一种解释（相同的实现）：
     * 根据二进制的思想，比如对于3个元素的集合，000表示一个元素都不选择，001表示选择第一个元素，101表示选择第一个和第三个元素...。
     * 因此如果集合大小为n，我们只需要让一个整数从0逐渐增加到2^n-1, 每个整数的二进制形式可以表示一个集合。
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets_bitManipulation(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = (int)(Math.pow(2, nums.length));
        // 往结果集中放入所有的子集合，此时为空
        for(int i = 0; i < n; i ++) result.add(new ArrayList<>());
        // 对于nums中的每一个数字
        for(int i = 0; i < nums.length; i ++) {
            // 确定该数字应放入的位置
            for(int j = 0; j < n; j ++) {
                if((j >> i & 1) == 1) {
                    result.get(j).add(nums[i]);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

    }
}
