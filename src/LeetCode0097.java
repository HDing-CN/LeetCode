import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class LeetCode0097 {

    public static volatile LeetCode0097 instance = null; // 保证 instance 在所有线程中同步

    private LeetCode0097() {} // private 避免在外部被实例化

    // getInstance 方法前加同步
    public static synchronized LeetCode0097 getInstance() {
        if (instance == null) {
            return new LeetCode0097();
        }

        return instance;
    }

    // 记忆化搜索 BFS
    public boolean isInterleave_BFS(String s1, String s2, String s3) {
        Queue<int[]> queue = new LinkedList<>();
        HashSet<String> hashSet = new HashSet<>();
        queue.offer(new int[]{0, 0, 0});
        while (!queue.isEmpty()) {
            int[] pp = queue.poll(); // double pointers

            // remove duplicate
            if (hashSet.contains(Arrays.toString(pp))) {
                continue;
            }
            hashSet.add(Arrays.toString(pp));

            if (pp[0] == s1.length() && pp[1] == s2.length() && pp[2] == s3.length()) {
                return true;
            }

            // 下标不可能小于0，因此不需要检测是否越过左边界
            if (pp[0] < s1.length() && pp[2] < s3.length() && s1.charAt(pp[0]) == s3.charAt(pp[2])) {
                queue.offer(new int[]{pp[0] + 1, pp[1], pp[2] + 1});
            }

            if (pp[1] < s2.length() && pp[2] < s3.length() && s2.charAt(pp[1]) == s3.charAt(pp[2])) {
                queue.offer(new int[]{pp[0], pp[1] + 1, pp[2] + 1});
            }
        }

        return false;
    }

    // 二维DP
    public boolean isInterleave_2DDP(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;

        for (int i = 0; i <= s1.length(); i ++) {
            for (int j = 0; j <= s2.length(); j ++) {
                if (i == 0 && j == 0) continue;

                System.out.println(i + " " + j + " " + (i + j - 1));

                if (i + j - 1 >= s3.length()) {
                    continue;
                }

                boolean s1EqualS3 = (i > 0 && (s1.charAt(i - 1) == s3.charAt(i + j - 1)));
                boolean s2EqualS3 = (j > 0 && (s2.charAt(j - 1) == s3.charAt(i + j - 1)));

                if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2EqualS3;
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1EqualS3;
                } else if (i > 0 && j > 0) {
                    dp[i][j] = (dp[i - 1][j] && s1EqualS3) || (dp[i][j - 1] && s2EqualS3);
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }

    // 一维DP，状态压缩，在二维DP中只用到了当前行和上一行，因此可以将二维DP压缩为一维
    public boolean isInterleave_1DDP(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        boolean[] dp = new boolean[s2.length() + 1];
        dp[0] = true;

        for (int i = 0; i <= s1.length(); i ++) {
            for (int j = 0; j <= s2.length(); j ++) {
                if (i == 0 && j == 0) continue;

                if (i + j - 1 >= s3.length()) {
                    dp[j] = false;
                }

                boolean s1EqualS3 = (i > 0 && (s1.charAt(i - 1) == s3.charAt(i + j - 1)));
                boolean s2EqualS3 = (j > 0 && (s2.charAt(j - 1) == s3.charAt(i + j - 1)));

                if (i == 0) {
                    dp[j] = dp[j - 1] && s2EqualS3;
                } else if (j == 0) {
                    dp[j] = dp[j] && s1EqualS3;
                } else if (i > 0 && j > 0) {
                    dp[j] = (dp[j] && s1EqualS3) || (dp[j - 1] && s2EqualS3);
                }
            }
        }

        return dp[s2.length()];
    }

    public static void main(String[] args) {
        LeetCode0097 leetCode0097 = LeetCode0097.getInstance();
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        System.out.println(leetCode0097.isInterleave_1DDP(s1, s2, s3));
    }

}
