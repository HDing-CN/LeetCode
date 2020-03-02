package solutions.medium.problem0500to1000;

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

    public boolean canPartitionKSubsets1(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        // 如果sum除不尽k，就说明无法平均分成k份，因为数组内的元素都是整数
        if (sum % k > 0) {
            return false;
        }
        // 平均值，也就是每个group的总和
        int average = sum / k;

        // 将数组排序，从最大数字开始遍历
        Arrays.sort(nums);
        int numIndex = nums.length - 1;
        int groupIndex = 0;

        // 初始化groups数组，一开始每个group都是0
        int[] groups = new int[k];

        // 如果有数字刚好是average，就直接放到当前group里，这个group就完成了，跳到下一个去
        while (numIndex >= 0 && nums[numIndex] == average) {
            groups[groupIndex] += nums[numIndex];
            numIndex -= 1;
            groupIndex += 1;
        }

        System.out.println("num and group index: " + numIndex + " " + groupIndex);
        return dfs1(nums, groups, numIndex, groupIndex, average);
    }

    private boolean dfs1(int[] nums, int[] groups, int numIndex, int groupIndex, int average) {
        // 如果所有数组内的数字都被放入group中，说明找到了正确的组合，返回true
        if (numIndex < 0) {
            return true;
        }

        for (int i = groupIndex; i < groups.length; i ++) {
            if (groups[i] + nums[numIndex] <= average) {
                groups[i] += nums[numIndex];
                // 如果当前数字能够放进group里，就把数字放进去，然后遍历后面的数字
                if (dfs1(nums, groups, numIndex - 1, groupIndex, average)) {
                    return true;
                }
                groups[i] -= nums[numIndex];
            }

            /**
             * 代码运行到这里有两种情况：
             * 1、当前数字的大小无法装入下标为i的group
             * 这种情况下，如果当前group的大小为0，说明当前数字过大，已经超出average，因此无需再往下遍历搜索，直接跳出循环即可
             * 2、当前数字成功放入下标为i的group，且以此状态为起始状态向下检索，并最终返回false
             * 这种情况下，如果当前group的大小为0，后面几个group的值都是0，因为我们是按照group的下标顺序从小到大遍历的，
             * 因此如果下标为i的group大小为0，那么后面的group大小一定还为0，并没有被遍历到。
             * 设当前数字为X，那就说明 ... X 0 0 ... 0 这个状态行不通，而在后面的group大小都是0的情况下，实际上将X放到哪一个group后的状态其实都是一样的，
             * 如果 ... X 0 0 ... 0 这个状态最终返回了false，那将X放到后面group的状态也就没有必要遍历了，
             * 因为本质上这些都是同一个状态，只不过是放置的group的下标不同而已
             */
            if (groups[i] == 0) {
                break;
            }
        }

        return false;
    }

    public boolean canPartitionKSubsets2(int[] nums, int k) {
        int sum = 0, max = 0;
        for (int i = 0; i < nums.length; i ++) {
            sum += nums[i];
            max = Math.max(max, nums[i]);
        }

        if (sum % k != 0 || max > sum / k) {
            return false;
        }

        boolean[] numUsed = new boolean[nums.length];
        Arrays.sort(nums);
        return dfs2(nums, k, 0, numUsed, 0, 0, sum / k);
    }

    /**
     * curGroup指的是当前的group的值，
     * 上一种方法是对每一个数字，寻找合适的group；
     * 而这一种方法是对每一个group，寻找合适的数字
     * @param nums
     * @param k
     * @param curGroup
     * @param numUsed
     * @param groupIndex
     * @param average
     * @return
     */
    public boolean dfs2(int[] nums, int k, int curGroup, boolean[] numUsed, int groupIndex, int numIndex, int average) {
        if (groupIndex == k) {
            return true;
        }

        if (curGroup == average) {
            return dfs2(nums, k, 0, numUsed, groupIndex + 1, 0, average);
        }

        for (int i = numIndex; i < nums.length; i ++) {
            if (!numUsed[i] && curGroup + nums[i] <= average) {
                numUsed[i] = true;
                if (dfs2(nums, k, curGroup + nums[i], numUsed, groupIndex, i + 1, average)) {
                    return true;
                }
                numUsed[i] = false;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        LeetCode0698 leetCode0698 = LeetCode0698.getInstance();

        int[] nums = {1,2,2,3,3,4,5};
        int k = 4;
        // 方法1更快一些，剪枝剪得更加简洁
        System.out.println(leetCode0698.canPartitionKSubsets2(nums, k));
    }
}
