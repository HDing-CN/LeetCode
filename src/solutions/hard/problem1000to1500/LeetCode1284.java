package solutions.hard.problem1000to1500;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class LeetCode1284 {

    public static volatile LeetCode1284 instance = null;

    private LeetCode1284() {}

    public static synchronized LeetCode1284 getInstance() {
        if (instance == null) {
            instance = new LeetCode1284();
        }
        return instance;
    }

    private HashSet<String> hashSet = new HashSet<>();
    private HashMap<String, Integer> hashMap = new HashMap<>();

    public int minFlips(int[][] mat) {
        // 统计一开始1的个数
        int numOfOne = 0;
        for (int i = 0; i < mat.length; i ++) {
            for (int j = 0; j < mat[0].length; j ++) {
                if (mat[i][j] == 1) numOfOne += 1;
            }
        }

        return dfs(mat, numOfOne, new int[]{-1, -1});
    }

    private int dfs(int[][] mat, int numOfOne, int[] previousFlipIndex) {
        // 终止条件1：达到目标状态
        if (numOfOne == 0) return 1;

        String matStr = matrixToString(mat);
        // 终止条件2：发现自己陷入循环
        if (this.hashMap.containsKey(matStr)) {
            return this.hashMap.get(matStr);
        }

        this.hashMap.put(matStr, -1);
        int ans = 0, minStep = Integer.MAX_VALUE;
        boolean existValidSolution = false;
        for (int i = 0; i < mat.length; i ++) {
            for (int j = 0; j < mat[0].length; j ++) {
                // 上一步刚刚翻转过的这一步不再翻转
                if (i == previousFlipIndex[0] && j == previousFlipIndex[1]) continue;

                // 翻转mat[i][j]和其邻居
                numOfOne = flip(mat, i, j, numOfOne);
                // 从这里开始 向下搜索
                int step = dfs(mat, numOfOne, new int[]{i, j});
                if (step != -1) {
                    existValidSolution = true;
                    minStep = Math.min(step, minStep);
                }
                // 翻转回来，回复当前状态，准备遍历下一个位置
                numOfOne = flip(mat, i, j, numOfOne);
            }
        }

        // 如果不存在可行解，直接返回-1
        if (!existValidSolution) return -1;

        ans += minStep;
        this.hashMap.put(matStr, ans);

        return ans;
    }

    /**
     * 翻转mat[i][j]，返回操作之后的矩阵中0的个数
     * @param mat
     * @param i
     * @param j
     * @param numOfOne
     * @return
     */
    private int flip(int[][] mat, int i, int j, int numOfOne) {
        // 翻转mat[i][j]
        mat[i][j] = 1 - mat[i][j];
        numOfOne -= (int)(Math.pow(-1, mat[i][j]));

        // 翻转mat[i][j]四个方向的邻居（如果没有越界）
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] dir : directions) {
            int x = i + dir[0];
            int y = j + dir[1];

            if (x >= 0 && x < mat.length && y >= 0 && y < mat[0].length) {
                mat[x][y] = 1 - mat[x][y];
                numOfOne -= (int)(Math.pow(-1, mat[x][y]));
            }
        }

        return numOfOne;
    }

    /**
     * test function
     * @return
     */
    private void test(int[][] mat, int x) {
        for (int i = 0; i < mat.length; i ++) {
            for (int j = 0; j < mat[0].length; j ++) {
                mat[i][j] = i * j;
            }
        }
        x += 1;
    }

    private String matrixToString(int[][] mat) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < mat.length; i ++) {
            for (int j = 0; j < mat[0].length; j ++) {
                stringBuilder.append(mat[i][j]);
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(1);
        stringBuilder.append('a');
        stringBuilder.append("hello");
        System.out.println(stringBuilder.toString());
        System.out.println((int)(Math.pow(-1, 1)));

        int[][] mat = {{1,1,1},{1,1,1},{1,1,1}};
        int x = 0;
        LeetCode1284 leetCode1284 = LeetCode1284.getInstance();
        leetCode1284.test(mat, x);
        for (int[] row : mat) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("x = " + x);
    }

}
