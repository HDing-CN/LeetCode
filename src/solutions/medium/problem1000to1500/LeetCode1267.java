package solutions.medium.problem1000to1500;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LeetCode1267 {

    public static volatile LeetCode1267 instance = null;

    private LeetCode1267() {}

    public static synchronized LeetCode1267 getInstance() {
        if (instance == null) {
            instance = new LeetCode1267();
        }
        return instance;
    }

    public int countServers(int[][] grid) {
        Map<Integer, ArrayList<Integer>> rowHashMap = new HashMap<>();
        Map<Integer, ArrayList<Integer>> colHashMap = new HashMap<>();

        int ans = 0;
        for (int i = 0; i < grid.length; i ++) {
            for (int j = 0; j < grid[0].length; j ++) {
                if (grid[i][j] == 1) {
                    ArrayList<Integer> rowList = rowHashMap.computeIfAbsent(i, key -> new ArrayList<Integer>());
                    ArrayList<Integer> colList = colHashMap.computeIfAbsent(j, key -> new ArrayList<Integer>());

                    if (rowList.size() > 0 || colList.size() > 0) {
                        ans += 1;

                        // 重点关注每一行和每一列的第一个元素，检测其是否已经被count过

                        /**
                         * 检查元素所在行是否只有一个元素，如果是，则检查这个单独的元素所在列是否有其他元素
                         * 如果有其他元素，说明该元素已经被统计 counted；如果没有，则将该元素统计到server总数中
                         */
                        if (rowList.size() == 1 && colHashMap.get(rowList.get(0)).size() == 1) {
                            ans += 1;
                        }

                        /**
                         * 同上，不过这里是检查该元素所在的列，如果所在列只有一个元素，就检查这个元素所在的行是否有其他元素
                         * 如果有，则说明该元素已经被统计；如果没有，则将该元素统计到server总数中
                         */
                        if (colList.size() == 1 && rowHashMap.get(colList.get(0)).size() == 1) {
                            ans += 1;
                        }
                    }

                    rowList.add(j); rowHashMap.put(i, rowList);
                    colList.add(i); colHashMap.put(j, colList);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        LeetCode1267 leetCode1267 = LeetCode1267.getInstance();
        int[][] grid = {{1,1,0,0},{0,0,1,0},{0,0,1,0},{0,0,0,1}};
        System.out.println(leetCode1267.countServers(grid));
    }
}
