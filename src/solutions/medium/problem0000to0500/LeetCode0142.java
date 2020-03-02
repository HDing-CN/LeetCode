package solutions.medium.problem0000to0500;

import utils.ListNode;

import java.util.HashSet;

public class LeetCode0142 {

    public static volatile LeetCode0142 instance = null;

    private LeetCode0142() {}

    public static synchronized LeetCode0142 getInstance() {
        if (instance == null) {
            instance = new LeetCode0142();
        }
        return instance;
    }

    public ListNode detectCycle_HashSet(ListNode head) {
        HashSet<ListNode> hashSet = new HashSet<>();
        ListNode p = head;
        while (p != null) {
            if (hashSet.contains(p)) {
                return p;
            }
            hashSet.add(p);
            p = p.next;
        }

        return null;
    }

    /**
     * 原理解释：
     * 如果链表中没有环，那么快指针一定会走到尽头，从而跳出while循环，最终函数返回null；
     * 如果链表中有环，定义链表中相邻两个节点之间为一条线段，那么链表可以看成是由多条线段组成，慢指针一次走一条线段，快指针一次走两条线段
     * 设链表非环部分有a条线段，环内共有b条线段，head在左边，链表的环沿顺时针方向，
     * 那么当快指针和慢指针在环内相遇时，设入环点到相遇点的顺时针距离为c条线段（即从入环点沿顺时针方向走c条线段走到相遇点），
     * 设此时慢指针绕环M整周，快指针绕环N整周，M和N均为非负整数，则：
     * 慢指针走过的路径长度为：a + M * b + c 条线段
     * 快指针走过的路径长度为：a + N * b + c 条线段
     * 由于快指针的速度为慢指针的两倍，因此走过的路径长度也为两倍，有：
     * 2 * (a + M * b + c) = a + N * b + c
     * 可得：
     * a + c = (N - 2 * M) * b，(N - 2 * M)一定为非负整数，因为如果其为负整数，那么a + c就是负数，而a和c为线段的数量，不可能为负数，因此(N - 2 * M)为非负整数不成立
     * 所以我们有：
     * a = (N - 2 * M) * b - c，为了方便表示，我们设 P = N - 2 * M，P >= 0，
     * 因此有：
     * a = P * b - c
     * 如果P为0，那么a = -c，因为两个数字都是非负整数，那么只有一种可能，a = c = 0，也就是说链表中没有非环部分，相遇点就是入环点
     * 如果P为1，那么a = b - c，也就是说，相遇点到入环点的顺时针距离（即从相遇点沿顺时针方向走到入环点的距离） = 链表头到入环点的距离
     * 如果P>1，那么a = (P - 1) * b + (b - c)，也就是说，相遇点到入环点的顺时针距离 + (P - 1)个整环的长度 = 链表头到入环点的距离
     * 综上所述，设指针p1从链表头出发，指针p2从相遇点出发，那么他们在走过了a条线段的路程后，都会到达入环点，在那里相遇。
     * @param head 链表头
     * @return 入环点
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        LeetCode0142 leetCode0142 = LeetCode0142.getInstance();
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next;

//        ListNode p = head;
//        int n = 10;
//        while (n > 0) {
//            System.out.println(p.val);
//            p = p.next;
//            n -= 1;
//        }

        System.out.println(leetCode0142.detectCycle(head).val);
    }

}
