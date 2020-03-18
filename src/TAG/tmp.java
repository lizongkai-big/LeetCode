package TAG;

import utils.ListNode;
import utils.TreeNode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class tmp {
    @Deprecated
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0)
            return res;
        String[] strings = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        loop(strings, digits, 0, res, "");
        return res;
    }

    public void loop(String[] strings, String digits, int inx, List<String> list, String sb) {
        if (inx == digits.length()) {
            list.add(sb);
            return;
        }
        String s = strings[digits.charAt(inx) - '0'];
        for (int i = 0; i < s.length(); i++) {
            loop(strings, digits, inx + 1, list, sb + s.charAt(i));
        }
    }

    /**
     * @param nums      字符集
     * @param res       结果集
     * @param list      部分
     * @param inx       下一步开始的下标
     * @param targetLen 目标长度
     */
    public void backtrack(int[] nums, List<List<Integer>> res, List<Integer> list, int inx, int targetLen) {
        if (list.size() == targetLen) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = inx; i < nums.length; i++) {
            list.add(nums[i]);
            backtrack(nums, res, list, i + 1, targetLen);
            list.remove(list.size() - 1);
        }
    }


    public static void main(String[] args) {
        tmp t = new tmp();
        int[] num = new int[]{-1, 1, -1, -3, -4, 5, 4, 1, 0, -5, 1, 2};

        System.out.println(Arrays.stream(num).boxed().collect(Collectors.toList()));


    }

}
