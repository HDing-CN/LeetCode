package solutions.easy.problem0500to1000;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LeetCode0994 {

    public static volatile LeetCode0994 instance = null;

    private LeetCode0994() {}

    public static synchronized LeetCode0994 getInstance() {
        if (instance == null) {
            instance = new LeetCode0994();
        }
        return instance;
    }

    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int numOfFreshOrange = 0;
        // 预处理，将所有腐烂橘子的位置存入队列中
        // 队列中数组的前两个数字是腐烂橘子的位置，第三个数字代表时刻，起始时刻为0
        for (int i = 0; i < grid.length; i ++) {
            for (int j = 0; j < grid[0].length; j ++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j, 0});
                } else if (grid[i][j] == 1) {
                    numOfFreshOrange += 1;
                }
            }
        }

        // 如果一开始就没有新鲜橘子，就直接返回0时刻
        if (numOfFreshOrange == 0) return 0;

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] curRottenOrange = queue.poll();

            for (int[] dir : directions) {
                int x = curRottenOrange[0] + dir[0];
                int y = curRottenOrange[1] + dir[1];
                if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) continue;

                // 如果邻居中有新鲜橘子，就将他也变成腐烂橘子，然后将位置放入队列中
                if (grid[x][y] == 1) {
                    grid[x][y] = 2;
                    numOfFreshOrange -= 1;

                    // 如果这一步操作之后发现所有的新鲜橘子都腐烂了，就直接返回下一时刻
                    if (numOfFreshOrange == 0) return curRottenOrange[2] + 1;

                    queue.offer(new int[]{x, y, curRottenOrange[2] + 1});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        LeetCode0994 leetCode0994 = LeetCode0994.getInstance();
        int[][] grid = {{1},{2},{2}};
        System.out.println(leetCode0994.orangesRotting(grid));
    }

}
