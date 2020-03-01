package solutions;

import java.util.LinkedList;
import java.util.Queue;

class MyStack {
    private Queue<Integer> queue;

    /** Initialize your data structure here. */
    public MyStack() {
        this.queue = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        this.queue.offer(x);
        int size = this.queue.size();

        /**
         * 每一次填入新元素，就将前面的元素挪到后面，这样就保证后放入的元素始终在先放入的元素前面
         * 例如：1 -> 1 2 -> 2 1 -> 2 1 3 -> 3 2 1 -> 3 2 1 4 -> 4 3 2 1 -> ......
         */
        for (int i = 0; i < size - 1; i ++) {
            int temp = this.queue.poll();
            this.queue.offer(temp);
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return this.queue.poll();
    }

    /** Get the top element. */
    public int top() {
        return this.queue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return this.queue.isEmpty();
    }
}

public class LeetCode0225 {

    public static volatile LeetCode0225 instance = null;

    public MyStack stack;

    private LeetCode0225(MyStack stack) {
        this.stack = stack;
    }

    public static synchronized LeetCode0225 getInstance(MyStack stack) {
        if (instance == null) {
            instance = new LeetCode0225(stack);
        }
        return instance;
    }

    public void execute() {
        this.stack.push(1);
        this.stack.push(2);
        this.stack.push(3);
        this.stack.push(4);
        this.stack.push(5);
        int param_1 = this.stack.pop();
        int param_2 = this.stack.top();
        boolean param_3 = this.stack.empty();
        System.out.println(param_1 + " " + param_2 + " " + param_3);
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        LeetCode0225 leetCode0225 = LeetCode0225.getInstance(stack);
        leetCode0225.execute();
    }
}