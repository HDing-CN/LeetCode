package solutions.medium.problem0000to0500;

public class LeetCode0200 {

    public static volatile LeetCode0200 instance = null;

    private LeetCode0200() {}

    public static synchronized LeetCode0200 getInstance() {
        if (instance == null) {
            instance = new LeetCode0200();
        }
        return instance;
    }

    private char[][] grid;

    public int numIslands(char[][] grid) {
        this.grid = grid;
        int ans = 0;
        for (int i = 0; i < this.grid.length; i ++) {
            for (int j = 0; j < this.grid[0].length; j ++) {
                if (this.grid[i][j] == '1') {
                    dfs(i, j);
                    ans += 1;
                }
            }
        }

        return ans;
    }

    private int dfs(int x, int y) {
        if (x < 0 || x >= this.grid.length || y < 0 || y >= this.grid[0].length || this.grid[x][y] != '1') {
            return 0;
        }

        int serverNum = 1;
        this.grid[x][y] = '2';
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        for (int[] dir : dirs) {
            int nextX = x + dir[0];
            int nextY = y + dir[1];
            serverNum += dfs(nextX, nextY);
        }

        return serverNum;
    }

    public static void main(String[] args) {
        LeetCode0200 leetCode0200 = LeetCode0200.getInstance();
        char[][] grid = {{'1','1','0','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        System.out.println(leetCode0200.numIslands(grid));
    }

}
