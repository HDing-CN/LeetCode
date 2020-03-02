package solutions.medium.problem0500to1000;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode0655 {

    public static volatile LeetCode0655 instance = null;

    private LeetCode0655() {}

    public static synchronized LeetCode0655 getInstance() {
        if (instance == null) {
            instance = new LeetCode0655();
        }
        return instance;
    }

    private List<List<String>> ans = new ArrayList<>();
    private int height;
    private int size;

    public List<List<String>> printTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        this.height = getHeight(root);
        this.size = (int) Math.pow(2, height - 1) * 2 - 1;

        for (int i = 0; i < height; i ++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < size; j ++) {
                row.add("");
            }
            this.ans.add(row);
        }

        dfs(root, 1, 1);

        return this.ans;
    }

    /**
     * Node root is the i-th element on current level
     * @param root
     * @param level
     * @param i
     */
    private void dfs(TreeNode root, int level, int i) {
        if (root == null) {
            return ;
        }

        int numOfNode = (int) Math.pow(2, level - 1);
        int sizeOfNode = this.size / numOfNode;
        int rightBoundary = sizeOfNode * i + (i - 1) - 1;
        int leftBoundary = rightBoundary - (sizeOfNode - 1);
        this.ans.get(level - 1).set((leftBoundary + rightBoundary) / 2, String.valueOf(root.val));

        dfs(root.left, level + 1, i * 2 - 1);
        dfs(root.right, level + 1, i * 2);
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    public static void main(String[] args) {
        LeetCode0655 leetCode0655 = LeetCode0655.getInstance();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
//        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
//        root.right.right = new TreeNode(7);
        System.out.println(leetCode0655.printTree(root).toString());
    }
}
