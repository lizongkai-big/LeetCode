package TAG.DP;

import java.util.Arrays;

public class LEARN {
    public static void main(String[] args) {
        Zero_One_Package zeroOnePackage = new Zero_One_Package();
        zeroOnePackage.knapsack_withValue(new int[]{2,2,4,6,3}, new int[]{3,4,8,9,6}, 5, 9);
        System.out.println(zeroOnePackage.lowestEditDistance_DP1(
                new String("1234673mitcnu").toCharArray(),
                6,
                new String("1234673mitcnu").toCharArray(),
                6));
    }
}

class Zero_One_Package {
    private int maxW = Integer.MIN_VALUE; // 结果放到 maxW 中
    private int[] weight = {2, 2, 4, 6, 3};  // 物品重量
    private int n = 5; // 物品个数
    private int w = 9; // 背包承受的最大重量

    /**
     *
     * @param i 将要决策的第i个物品
     * @param cw 背包中已有重量
     */
    public void f_Recrucive(int i, int cw) {
        if (cw == w || i == n) {
            if (cw > maxW) maxW = cw;
            return;
        }
        f_Recrucive(i + 1, w);
        if (cw + weight[i] <= w) {
            f_Recrucive(i + 1, cw + weight[i]);
        }
    }

    private boolean[][] mem = new boolean[5][10]; // 备忘录，默认值 false
    /**
     * 使用数组记录重复状态，避免冗余计算
     * @param i 将要决策的第i个物品
     * @param cw 背包中已有重量
     */
    public void f_Recrusive_Memo(int i, int cw) { // 调用 f(0, 0)
        if (cw == w || i == n) { // cw==w 表示装满了，i==n 表示物品都考察完了
            if (cw > maxW) maxW = cw;
            return;
        }
        if (mem[i][cw]) return; // 重复状态
        mem[i][cw] = true; // 记录 (i, cw) 这个状态
        f_Recrusive_Memo(i + 1, cw); // 选择不装第 i 个物品
        if (cw + weight[i] <= w) {
            f_Recrusive_Memo(i + 1, cw + weight[i]); // 选择装第 i 个物品
        }
    }

    /**
     * 0-1背包，使用n维数组记录状态
     * @param weight 物品重量
     * @param n 物品个数
     * @param w 背包可承载重量
     * @return
     */
    public int knapsack(int[] weight, int n, int w) {
        // 在阶段i，放入背包中物品总的重量的最大值j，存在与否
        boolean[][] states = new boolean[n][w + 1]; // 默认值 false
        states[0][0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        states[0][weight[0]] = true;
        for (int i = 1; i < n; ++i) { // 动态规划状态转移
            for (int j = 0; j <= w; ++j) {// 不把第 i 个物品放入背包
                if (states[i - 1][j] == true) states[i][j] = states[i - 1][j];
            }
            // 把第 i 个物品放入背包，
            // j: 背包中已有物品的总重量（要放得进去）
            for (int j = 0; j <= w - weight[i]; ++j) {
                // 在上一个阶段，这个背包
                if (states[i - 1][j] == true) states[i][j + weight[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[n - 1][i] == true) return i;
        }
        return 0;
    }

    /**
     * 0-1背包，使用一维数组记录状态
     * @param items
     * @param n
     * @param w
     * @return
     */
    public static int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w + 1]; // 默认值 false
        states[0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        states[items[0]] = true;
        for (int i = 1; i < n; ++i) { // n 个阶段
            // 把第 i 个物品放入背包
            // j: 背包中已有物品的总重量（要放得进去）
            for (int j = w - items[i]; j >= 0; --j) {
                if (states[j] == true) states[j + items[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[i] == true) return i;
        }
        return 0;
    }

    int[] weighs = {2,2,4,6,3};
    int[] values = {3,4,8,9,6};
    int count = weighs.length;
    int bagWeigh = 9;
    int maxV = Integer.MIN_VALUE;
    /**
     * 0-1背包 一个最大承重为w的背包，一堆标有重量和价值的物品，如何选择物品使得背包中物品的价值最大
     * 回溯解法
     * 判断第n个物品，背包中已有物品重量w，价值v
     * @param i
     * @param w
     * @param v
     * @return
     */
    public void knapsack_backtracking(int i, int w, int v) {
        if(i == count || w == bagWeigh) {
            if(v > maxV) maxV = v;
            return;
        }
        knapsack_backtracking(i+1, w, v);
        if(w + weighs[i] <= bagWeigh) {
            knapsack_backtracking(i+1, w+weighs[i], v+values[i]);
        }
    }

    /**
     * 0-1背包 一个最大承重为w的背包，一堆标有重量和价值的物品，如何选择物品使得背包中物品的价值最大
     * n维数组记录状态
     * @param weight
     * @param value
     * @param n
     * @param w
     * @return
     */
    public static int knapsack_withValue(int[] weight, int[] value, int n, int w) {
        // n个阶段，w+1种背包重量，值为在i阶段，背包重量为j的情况下，背包中物品的总价值
        int[][] states = new int[n][w + 1];
        for (int i = 0; i < n; ++i) { // 初始化 states
            Arrays.fill(states[i], -1);
        }
        // 初始化
        states[0][0] = 0; states[0][weight[0]] = value[0];
        for (int k = 1; k < n; k++) { // n个阶段
            // 决策1: 不要这个物品
            for (int l = 0; l <= w; l++) {
                // 在上一个状态的基础上
                if(states[k-1][l] >= 0)
                    // 重量不变，价值也不变
                    states[k][l] = states[k-1][l];
            }
            // 决策2：要这个物品
            // 背包中能放得下这个物品
            for (int l = 0; l <= w-weight[k]; l++) {
                // 在上一个状态的基础上
                if(states[k-1][l] >= 0) {
                    // 新的背包价值怎么来的？之后判断背包中有哪些物品就这么判断
                    int v = states[k-1][l] + value[k];
                    // 如果该物品放进背包的话，此时背包重量为l+weigh[k]，
                    // 背包中物品的总价值大于现在背包中的价值，也就是背包中重量相同，放进去比不放价值要大，干嘛不放呢？
                    if(v > states[k][l+weight[k]]) {
                        states[k][l+weight[k]] = v;
                    }
                    // 能放进背包，但不放，因为这个物品的价值较小
                    else {}
                }
            }
        }
        // 找出最大值
        int maxvalue = -1;
        for (int j = 0; j <= w; ++j) {
            if (states[n - 1][j] > maxvalue) maxvalue = states[n - 1][j];
        }
        // 用于确定背包中有哪些物品
        int maxVlaue = -1;
        int maxWeight = 0;
        for (int k = 0; k <= w; k++) {
            if(states[n-1][k] > maxVlaue) {
                maxWeight = k;
                maxVlaue =states[n-1][k];
            }
        }
        System.out.println(maxVlaue + "  " + maxWeight);
        // 那么，都是选择了哪些物品装进背包了呢？
        // 找出背包中都放了哪些物品，从后往前判断
        for (int k = n-1; k >= 1; k--) {
            // 因为背包中能放下这个物品，so可能有这个物品
            // && 从上一个阶段来看，正是因为放入这个物品，才使得背包达到价值最大的情况
            if(maxWeight-weight[k] >= 0
                    && states[k-1][maxWeight-weight[k]] == maxVlaue-value[k]) {
                System.out.println("第"+(k+1)+"个物品，价值："+value[k] + "，重量：" + weight[k]);
                maxVlaue -= value[k];
                maxWeight -= weight[k];
            }
        }
        if(maxVlaue != 0) System.out.println("第"+1+"个物品，价值："+value[0] + "，重量：" + weight[0]);
        return maxvalue;
    }

    public static int knapsack_withValue2(int[] weight, int[] value, int n, int w) {
        int[] states = new int[w + 1];
        for (int i = 0; i <= w; ++i) { // 初始化 states
            states[i] = -1;
        }
        states[0] = 0;
        states[weight[0]] = value[0];
        for (int i = 1; i < n; ++i) { // 动态规划，状态转移
            for (int j = w - weight[i]; j >= 0; ++j) { // 选择第 i 个物品
                if (states[j] >= 0) {
                    int v = states[j] + value[i];
                    if (v > states[j + weight[i]]) {
                        states[j + weight[i]] = v;
                    }
                }
            }
        }
        // 找出最大值
        int maxvalue = -1;
        for (int j = 0; j <= w; ++j) {
            if (states[j] > maxvalue) maxvalue = states[j];
        }
        return maxvalue;
    }

    public void count(int[][] triangles) {

    }

    /**
     * 计算从[0,0]-->[n-1, n-1]的最小距离，只能向右或者下移动
     * @param matrix matrix[i][j]存储的是到达该点的步长
     * @param n matrix 的行数、列数
     * @return
     */
    public int minDistDP(int[][] matrix, int n) {
        // 二维状态表
        int[][] states = new int[n][n];
        int sum = 0;
        for (int j = 0; j < n; ++j) { // 初始化 states 的第一行数据
            sum += matrix[0][j];
            states[0][j] = sum;
        }
        sum = 0;
        for (int i = 0; i < n; ++i) { // 初始化 states 的第一列数据
            sum += matrix[i][0];
            states[i][0] = sum;
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < n; ++j) {
                states[i][j] =
                        matrix[i][j] + Math.min(states[i][j-1], states[i-1][j]);
            }
        }
        return states[n-1][n-1];
    }

    private char[] a = "mitcmu".toCharArray();
    private char[] b = "mtacnu".toCharArray();
    private int len1 = 6;
    private int len2 = 6;
    private int minDist = Integer.MAX_VALUE; // 存储结果
    // 调用方式 lowestEditDistance_Recruive(0, 0, 0);
    public void lowestEditDistance_Recruive(int i, int j, int edist) {
        if(i == len1 || j == len2) {
            if(i < len1) edist += (len1-i);
            if(j < len2) edist += (len2-j);
            if(minDist > edist) edist = minDist;
            return;
        }
        // 两个字符匹配
        if(a[i] == b[j])
            lowestEditDistance_Recruive(i+1, j+1, edist);
        // 两个字符不匹配
        else {
            // 删除 a[i] 或者 b[j] 前添加一个字符
            lowestEditDistance_Recruive(i+1, j, edist+1);
            // 删除 b[j] 或者 a[i] 前添加一个字符
            lowestEditDistance_Recruive(i, j+1, edist+1);
            // 将 a[i] 和 b[j] 替换为相同字符
            lowestEditDistance_Recruive(i+1, j+1, edist+1);
        }
    }

    public int lowestEditDistance_DP1(char[] a, int len1, char[] b, int len2) {
        int[][] status = new int[len1][len2];
        // 初始化第0列
        for (int i = 0; i < len1; i++) {
            if(a[i] == b[0]) {
                status[i][0] = i;
            }
            else if(i != 0)
                status[i][0] = status[i-1][0] + 1;
            else
                status[i][0] = 1;
        }
        // 初始化第0行
        for (int j = 0; j < len2; j++) {
            if(a[0] == b[j]) {
                status[0][j] = j;
            }
            else if(j != 0)
                status[0][j] = status[0][j] + 1;
            else
                status[0][j] = 1;
        }
        for (int i = 1; i < len1; i++) {
            for (int j = 1; j < len2; j++) {
                if(a[i] == b[j]) {
                    status[i][j] = Math.min(Math.min(status[i-1][j]+1, status[i][j-1]+1),
                            status[i-1][j-1]);
                    if(status[i][j-1]+1 < status[i-1][j-1] || status[i-1][j]+1 < status[i-1][j-1]) {
                        System.out.println("a[i] = " + a[i]);
                        System.out.println("i = " + i);
                        System.out.println("j = " + j);
                    }
                }
                else {
                    status[i][j] = Math.min(Math.min(status[i-1][j]+1, status[i][j-1]+1),
                            status[i-1][j-1]+1);
                }
            }
        }
        return status[len1-1][len2-1];
    }

}