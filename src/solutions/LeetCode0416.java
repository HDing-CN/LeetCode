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

    public boolean canPartition(int[] nums) {


        return false;
    }

    public static void main(String[] args) {
        LeetCode0416 leetCode0416 = LeetCode0416.getInstance();
        int[] nums = {1,5,11,5};
        System.out.println(leetCode0416.canPartition(nums));
    }

}
