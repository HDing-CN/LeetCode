package solutions.medium.problem0500to1000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode0967 {

    public static volatile LeetCode0967 instance = null;

    private LeetCode0967() {}

    public static synchronized LeetCode0967 getInstance() {
        if (instance == null) {
            instance = new LeetCode0967();
        }
        return instance;
    }

    private List<Integer> list = new ArrayList<>();
    private int N;
    private int K;

    public int[] numsSameConsecDiff(int N, int K) {
        this.N = N;
        this.K = K;
        dfs(1, 0);

        // 当数字长度为1时，0也可以作为首位，且答案唯一
        if (N == 1) {
            return new int[]{0,1,2,3,4,5,6,7,8,9};
        }

        int[] ans = new int[this.list.size()];
        for (int i = 0; i < this.list.size(); i ++) {
            ans[i] = this.list.get(i);
        }
        return ans;
    }

    private void dfs(int nthNum, int num) {
        if (nthNum > this.N) {
            this.list.add(num);
            return ;
        }

        if (nthNum == 1) {
            for (int i = 1; i < 10; i ++) {
                dfs(2, i);
            }
        } else {
            if (num % 10 + this.K < 10) {
                dfs(nthNum + 1, num * 10 + (num % 10 + this.K));
            }

            // 注意这里的K为0时，会重复添加已有的数字，因此需要加上条件判断
            if (this.K != 0 && num % 10 - this.K >= 0) {
                dfs(nthNum + 1, num * 10 + (num % 10 - this.K));
            }
        }
    }

    public static void main(String[] args) {
        LeetCode0967 leetCode0967 = LeetCode0967.getInstance();
        System.out.println(Arrays.toString(leetCode0967.numsSameConsecDiff(2,0)));
    }

}
