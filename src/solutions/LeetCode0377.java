package solutions;

import java.util.HashMap;
import java.util.Map;

public class LeetCode0377 {

    public static volatile LeetCode0377 instance = null;

    private LeetCode0377() {}

    public static synchronized LeetCode0377 getInstance() {
        if (instance == null) {
            instance = new LeetCode0377();
        }
        return instance;
    }

    private int[] nums;
    private int target;
    private Map<Integer, Integer> hashMap = new HashMap<>();

    public int combinationSum4(int[] nums, int target) {
        this.nums = nums;
        this.target = target;
        return dfs(0);
    }

    private int dfs(int sum) {
        if (this.hashMap.containsKey(this.target - sum)) {
            return this.hashMap.get(this.target - sum);
        }

        if (sum == this.target) {
            return 1;
        }

        if (sum > this.target) {
            return 0; // terminate this path
        }

        int ans = 0;
        for (int i = 0; i < this.nums.length; i ++) {
            ans += dfs(sum + this.nums[i]);
        }

        /**
         * 使用哈希表存储中间过程值
         * 当其他路径遇到对应的数字时，直接调取哈希表中的存储的值即可，而不需要重复搜索了
         */
        this.hashMap.put(this.target - sum, ans);

        return ans;
    }

    public static void main(String[] args) {
        LeetCode0377 leetCode0377 = LeetCode0377.getInstance();
        int[] nums = {1,2,3,4};
        int target = 32;
        System.out.println(leetCode0377.combinationSum4(nums, target));
    }

}
