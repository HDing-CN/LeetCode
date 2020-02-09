import java.util.HashMap;

public class LeetCode0246 {

    public static volatile LeetCode0246 instance = null;

    private LeetCode0246() {}

    public static synchronized LeetCode0246 getInstance() {
        if (instance == null) {
            instance = new LeetCode0246();
        }
        return instance;
    }

    public boolean isStrobogrammatic(String num) {
        HashMap<Character, Character> hashMap = new HashMap<>();
        hashMap.put('6', '9');
        hashMap.put('9', '6');
        hashMap.put('0', '0');
        hashMap.put('1', '1');
        hashMap.put('8', '8');

        int left = 0, right = num.length() - 1;
        while (left <= right && hashMap.containsKey(num.charAt(left))
                && hashMap.get(num.charAt(left)) == num.charAt(right)) {
            left += 1;
            right -= 1;
        }

        return left > right;
    }

    public static void main(String[] args) {
        LeetCode0246 leetCode0246 = LeetCode0246.getInstance();
        String num = "96";
        System.out.println(leetCode0246.isStrobogrammatic(num));
    }

}
