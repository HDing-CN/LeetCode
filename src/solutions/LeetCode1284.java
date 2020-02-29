package solutions;

public class LeetCode1284 {

    public static volatile LeetCode1284 instance = null;

    private LeetCode1284() {}

    public static synchronized LeetCode1284 getInstance() {
        if (instance == null) {
            instance = new LeetCode1284();
        }
        return instance;
    }

    public int minFlips(int[][] mat) {
        return 0;
    }

    private boolean dfs(int[][] mat, int numOfZero, int[] previousFlipIndex) {
        return false;
    }

    public static void main(String[] args) {

    }

}
