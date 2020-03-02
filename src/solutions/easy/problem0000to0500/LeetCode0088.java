package solutions.easy.problem0000to0500;

import java.util.Arrays;

public class LeetCode0088 {

    public static volatile LeetCode0088 instance = null;

    private LeetCode0088() {}

    public static synchronized LeetCode0088 getInstance() {
        if (instance == null) {
            instance = new LeetCode0088();
        }
        return instance;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1, p3 = nums1.length - 1;
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[p3] = nums1[p1];
                p1 -= 1;
                p3 -= 1;
            } else if (nums1[p1] <= nums2[p2]) {
                nums1[p3] = nums2[p2];
                p2 -= 1;
                p3 -= 1;
            }
        }

        // 只检测p2就可以，因为数字都存在nums1中，所以如果是p1>0就不需要再进行赋值了
        if (p2 >= 0) {
            for (int i = 0; i <= p2; i ++) {
                nums1[i] = nums2[i];
            }
        }

        System.out.println(Arrays.toString(nums1));
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        LeetCode0088 leetCode0088 = LeetCode0088.getInstance();
        leetCode0088.merge(nums1, 3, nums2, 3);
    }

}
