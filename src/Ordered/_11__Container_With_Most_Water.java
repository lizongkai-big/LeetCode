package Ordered;

public class _11__Container_With_Most_Water {
    /**
     * https://leetcode.com/problems/container-with-most-water/discuss/6099/Yet-another-way-to-see-what-happens-in-the-O(n)-algorithm
     * 容器容积计算公式：Math.min(height[i], height[j]) * (j-i)
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int len = height.length;
        int res = 0;
        // 两个指针，分别代表容器的两边；初始状态使底长最大
        int i=0, j = len-1;
        while(i < j) {
            // 高度 * 底长
            res = Math.max(res, Math.min(height[i], height[j]) * (j-i));
            // 为了Math.min(height[i], height[j])更大些，就让小的边寻求大些
            // 如果高度一致，就都向内靠拢咯
            if (height[i] == height[j]) {i++; j--;}
            else if (height[i] < height[j]) i ++;
            else j--;
        }
        return res;
    }
}
