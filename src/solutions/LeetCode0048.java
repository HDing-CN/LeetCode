package solutions;

import java.util.Arrays;

public class LeetCode0048 {

    public static volatile LeetCode0048 instance = null;

    private LeetCode0048() {}

    public static synchronized LeetCode0048 getInstance() {
        if (instance == null) {
            instance = new LeetCode0048();
        }
        return instance;
    }

    /**
     * 原理解释：
     * 设方形矩阵的正中心点为坐标原点，矩阵中的每一个元素坐标为(x, y)，换用极坐标表示法，则为：
     * (x, y) = (r * cos θ, r * sin θ)，
     * 如果将矩阵顺时针旋转90°，那么在极坐标上就是：
     * (r * cos (θ - 90°), r * sin (θ - 90°))
     * = (r * cos (θ + 90° - 180°), r * sin (θ + 90° - 180°))
     * = (-r * cos (θ + 90°), -r * sin (θ + 90°))
     * = (r * sin θ, -r * cos θ)
     * = (y, -x)
     * 因此，顺时针旋转90°后坐标的变化为(x, y) -> (y, -x)，我们可以分两步变换：
     * 第一步，沿左上至右下对角线翻转，将(x, y)变为(-y, -x)，
     * 第二步，将矩阵沿垂直中线左右翻转，将(y, x)变为(y, -x)
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 第一步：沿左上至右下对角线翻转
        for (int i = 0; i < n; i ++) {
            for (int j = i + 1; j < n ;j ++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // 第二步：沿垂直中线左右翻转
        for (int i = 0; i < n; i ++) {
            int left = 0, right = n - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left += 1;
                right -= 1;
            }
        }

        for (int i = 0; i < n; i ++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    public static void main(String[] args) {
        LeetCode0048 leetCode0048 = LeetCode0048.getInstance();
        int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        leetCode0048.rotate(matrix);
    }

}
