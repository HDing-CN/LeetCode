package solutions;

public class LeetCode0142 {

    public static volatile LeetCode0142 instance = null;

    private LeetCode0142() {}

    public static synchronized LeetCode0142 getInstance() {
        if (instance == null) {
            instance = new LeetCode0142();
        }
        return instance;
    }



    public static void main(String[] args) {
        LeetCode0142 leetCode0142 = LeetCode0142.getInstance();

    }

}
