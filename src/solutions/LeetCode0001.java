package solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeetCode0001 {

    private static volatile LeetCode0001 instance = null;    //保证 instance 在所有线程中同步

    private LeetCode0001(){}    //private 避免类在外部被实例化

    public static synchronized LeetCode0001 getInstance()
    {
        //getInstance 方法前加同步
        if(instance == null)
        {
            instance = new LeetCode0001();
        }
        return instance;
    }

    public int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i ++) {
            if (hashMap.containsKey(target - nums[i])) {
                ans[0] = hashMap.get(target - nums[i]);
                ans[1] = i;
                return ans;
            }
            hashMap.put(nums[i], i);
        }

        return ans;
    }

    public static void main(String[] args) {
        LeetCode0001 leetCode0001 = LeetCode0001.getInstance();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(leetCode0001.twoSum(nums, target)));
    }
}
