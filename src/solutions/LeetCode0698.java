package solutions;

import java.util.Arrays;

public class LeetCode0698 {

    public static volatile LeetCode0698 instance = null;

    private LeetCode0698() {}

    public static synchronized LeetCode0698 getInstance() {
        if (instance == null) {
            instance = new LeetCode0698();
        }
        return instance;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        Arrays.sort(nums);
        System.out.println("After sort: " + Arrays.toString(nums));

        int sum = 0;
        for (int i = 0; i < nums.length; i ++) {
            sum += nums[i];
        }

        System.out.println("The average is: " + (sum / k));

        return false;
    }

    public static void main(String[] args) {
        LeetCode0698 leetCode0698 = LeetCode0698.getInstance();
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        int k = 4;
        leetCode0698.canPartitionKSubsets(nums, k);
    }
}
