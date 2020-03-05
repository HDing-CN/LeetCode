package solutions.easy.problem1000to1500;

import java.util.Arrays;

public class LeetCode1103 {

    public static volatile LeetCode1103 instance = null;

    private LeetCode1103() {}

    public static synchronized LeetCode1103 getInstance() {
        if (instance == null) {
            instance = new LeetCode1103();
        }
        return instance;
    }

    public int[] distributeCandies(int candies, int num_people) {
        int[] ans = new int[num_people];
        int counter = 1;
        while (candies > 0) {
            // 如果当前剩余的糖果数量不够counter，就有多少给多少
            if (counter <= candies) {
                ans[(counter - 1) % num_people] += counter;
                candies -= counter;
            } else {
                ans[(counter - 1) % num_people] += candies;
                candies = 0;
            }

            counter += 1;
        }

        return ans;
    }

    public static void main(String[] args) {
        LeetCode1103 leetCode1103 = LeetCode1103.getInstance();
        System.out.println(Arrays.toString(leetCode1103.distributeCandies(7, 4)));
    }
}
