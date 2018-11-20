package Ordered;

/**
 * 往两个篮子里放水果，每个篮子里水果的种类是相同的；
 * 面对新的种类的水果，要么停止搜索，要么放进某个篮子中一个；
 * 那么对于一行树，求两个篮子最多能放的水果数目
 */
public class _904__Fruit_Into_Baskets {
    public int totalFruit(int[] tree) {
        int max = 0;
        int len = tree.length;
        /*按照一种模型：...aaabbbc...
        * 对于一种新的类型c，分为三种情况
        * 1. c == a
        * 2. c == b
        * 3. c != a && c != b
        * */
        int a = -1, b = -1;
        // 笼统地知道a,b的数目是没有用的，因为题目虽然要求a, b可以不连续，但是一旦出现一个新的type（c），你根本不知到a和c之间的b有多少，
        // 所以，要有一个统计b的数目的变量
        int cur = 0, cb = 0;

        for(int i = 0; i < len; i ++) {
            if(tree[i] == a) {
                a = b;
                b = tree[i];
                cb = 1;
                cur ++;
            }
            else if(tree[i] == b) {
                cur ++;
                cb ++;
            }
            else if(tree[i] != a && tree[i] != b) {
                a = b;
                b = tree[i];

                cur = cb + 1;
                cb = 1;
            }
            max = cur > max ? cur : max;
        }
        return max;
    }

    public static void main(String[] args) {
        _904__Fruit_Into_Baskets fruit_into_baskets = new _904__Fruit_Into_Baskets();
        fruit_into_baskets.totalFruit(new int[]{1,0,1,4,1,4,1,2,3});
    }
}
