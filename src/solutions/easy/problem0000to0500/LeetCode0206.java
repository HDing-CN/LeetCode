package solutions.easy.problem0000to0500;

import utils.ListNode;

public class LeetCode0206 {

    public static  volatile LeetCode0206 instance = null;

    private LeetCode0206() {}

    public static synchronized LeetCode0206 getInstance() {
        if (instance == null) {
            instance = new LeetCode0206();
        }
        return instance;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        while (head.next != null) {
            ListNode temp = head.next;

            head.next = temp.next;
            temp.next = dummy.next;
            dummy.next = temp;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        LeetCode0206 leetCode0206 = LeetCode0206.getInstance();
        head = leetCode0206.reverseList(head);
        ListNode p = head;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println('\n');
    }

}
