package solutions.hard.problem1000to1500;

import java.util.ArrayList;
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

    private HashMap<String, Integer> hashMap = new HashMap<>();

    public int minFlips(int[][] mat) {
        // 统计一开始1的个数
        int numOfOne = 0;
        for (int i = 0; i < mat.length; i ++) {
            for (int j = 0; j < mat[0].length; j ++) {
                if (mat[i][j] == 1) numOfOne += 1;
            }
        }
        if (numOfOne == 0) return 0;

        int ans = dfs(mat, numOfOne, new int[]{-1, -1}, new ArrayList<String>());
        if (ans > 0) ans -= 1;
        return ans;
    }

    private int dfs(int[][] mat, int numOfOne, int[] previousFlipIndex, ArrayList<String> list) {
        // 终止条件1：达到目标状态
        if (numOfOne == 0) {
            list.add(matrixToString(mat));
            System.out.println(list.toString());
            list.remove(matrixToString(mat));
            return 1;
        }

        String matStr = matrixToString(mat);
        // 终止条件2：发现自己陷入死循环
        if (this.hashMap.containsKey(matStr) && this.hashMap.get(matStr) == -1) {
            return -1;
        }

        this.hashMap.put(matStr, -1);
        // 跟二叉树深度那道题一样，计算路径深度，也就是计算state list中的节点数量，
        // 因此，如果最终有解，路径深度减去1才是中间翻转的次数
        int depth = 1;
        int minStep = Integer.MAX_VALUE;
        boolean existValidSolution = false;
        for (int i = 0; i < mat.length; i ++) {
            for (int j = 0; j < mat[0].length; j ++) {
                // 上一步刚刚翻转过的这一步不再翻转
                if (i == previousFlipIndex[0] && j == previousFlipIndex[1]) continue;

                // 翻转mat[i][j]和其邻居
                numOfOne = flip(mat, i, j, numOfOne);
                // 从这里开始 向下搜索
                list.add(matStr);
                int step = dfs(mat, numOfOne, new int[]{i, j}, list);
                list.remove(matStr);
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

        depth += minStep;
        this.hashMap.put(matStr, depth);

        return depth;
    }

    /**
     * 翻转mat[i][j]，返回操作之后的矩阵中1的个数
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
     * 测试数组作为参数，其值会不会被修改
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
//        int[][] mat = {{1,1,1},{1,0,1},{0,0,0}};
//        int[][] mat = {{0,0},{0,1}};
        int[][] mat = {{1,1,0},{0,1,1},{1,0,1}};
        int x = 0;
        LeetCode1284 leetCode1284 = LeetCode1284.getInstance();
        System.out.println(leetCode1284.minFlips(mat));
    }

}
