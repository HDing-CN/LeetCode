package solutions;

import java.util.Arrays;

public class LeetCode0416 {

    public static volatile LeetCode0416 instance = null;

    private LeetCode0416() {}

    public static synchronized LeetCode0416 getInstance() {
        if (instance == null) {
            instance = new LeetCode0416();
        }
        return instance;
    }

    public boolean canPartition_DFS(int[] nums) {
        // 先排序，然后从最大的数字开始遍历，在最短时间内填满group，能够缩短搜索时间
        Arrays.sort(nums);

        int sum = 0, max = 0;
        for (int i = 0; i < nums.length; i ++) {
            sum += nums[i];
            max = Math.max(nums[i], max);
        }

        // 如果最大值大于总和的一半或者总和是奇数，就说明不可能做到平均分，直接return false
        if (max * 2 > sum || sum % 2 != 0) return false;

        return dfs(sum / 2, nums.length - 1, nums);
    }

    private boolean dfs(int cur, int numIndex, int[] nums) {
        // 如果当前group的值<0，说明大于sum的一半，数字过大，返回false
        // 或者数字已经遍历完，结果当前group没有填满，同样返回false
        if (cur < 0 || numIndex < 0) return false;

        // 刚好一半，平均分成功，返回true
        if (cur == 0) return true;

        // 思路很简单，不放到group1就放到group2，只需要知道一个group的值即可，也就是cur
        return dfs(cur - nums[numIndex], numIndex - 1, nums) || dfs(cur, numIndex - 1, nums);
    }

    public boolean canPartition_2DDP(int[] nums) {
        Arrays.sort(nums);

        int sum = 0, max = 0;
        for (int i = 0; i < nums.length; i ++) {
            sum += nums[i];
            max = Math.max(max, nums[i]);
        }

        if (sum % 2 != 0 || max > sum / 2) return false;

        /**
         * 这里的dp[i][j]表示的意思是，nums数组的前i个数字能否恰好填满大小为j的group
         * 因此dp[i][0]一定为true
         */
        boolean[][] dp = new boolean[nums.length + 1][sum / 2 + 1];
        dp[0][0] = true;
        for (int i = 1; i < nums.length + 1; i ++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < nums.length + 1; i ++) {
            for (int j = 1; j < sum / 2 + 1; j ++) {
                /**
                 * 第一种情况：如果前i-1个数字能填满容量为j的group，那么前i个也一定行
                 * 第二种情况：如果前i - 1个数字能填满容量为j - nums[i]的group，那么
                 * 前i个数字就能填满容量为j的group
                 */
                if (dp[i - 1][j]) {
                    dp[i][j] = true;
                } else if (j - nums[i - 1] >= 0 && dp[i - 1][j - nums[i - 1]]) {
                    dp[i][j] = true;
                }
            }
        }

        for (int i = 0; i < dp.length; i ++) {
            System.out.println(Arrays.toString(dp[i]));
        }

        return dp[nums.length][sum / 2];
    }

    /**
     * 因为二维DP中每一行只使用了上一行的信息，所以可以进行状态压缩，使用一维数组即可表示每一次循环用到的状态
     * @param nums
     * @return
     */
    public boolean canPartition_1DDP(int[] nums) {
        Arrays.sort(nums);

        int sum = 0, max = 0;
        for (int i = 0; i < nums.length; i ++) {
            sum += nums[i];
            max = Math.max(max, nums[i]);
        }

        if (sum % 2 != 0 || max > sum / 2) return false;

        /**
         * 这里的dp[i][j]表示的意思是，nums数组的前i个数字能否恰好填满大小为j的group
         * 因此dp[i][0]一定为true
         */
        boolean[] dp = new boolean[sum / 2 + 1];
        dp[0] = true;

        for (int i = 1; i < nums.length + 1; i ++) {
            /**
             * 因为每一次循环用到的上一行数字一定在当前数字的左侧，
             * 所以从后向前遍历就不会存在上一行元素被新值覆盖的问题
             */
            for (int j = sum / 2; j >= 0; j --) {
                /**
                 * 第一种情况：如果前i-1个数字能填满容量为j的group，那么前i个也一定行
                 * 第二种情况：如果前i - 1个数字能填满容量为j - nums[i]的group，那么
                 * 前i个数字就能填满容量为j的group
                 */
                if (dp[j]) {
                    dp[j] = true;
                } else if (j - nums[i - 1] >= 0 && dp[j - nums[i - 1]]) {
                    dp[j] = true;
                }
            }
            System.out.println(Arrays.toString(dp));
        }

        return dp[sum / 2];
    }

    public static void main(String[] args) {
        LeetCode0416 leetCode0416 = LeetCode0416.getInstance();
        int[] nums = {2,2,3,5};
        System.out.println(leetCode0416.canPartition_1DDP(nums));
    }
}
