package com.yelanyanyu.code;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class isBalanced {

    public static boolean isBalanced2(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isBalanced;
    }

    public static info process(Node x) {
        if (x == null) {
            return new info(true, 0);
        }
        info leftInfo = process(x.left);
        info rightInfo = process(x.right);
        boolean isBalanced = true;
        //更新heights
        int height = 1;
        height = Math.max(leftInfo.heights, rightInfo.heights) + 1;

        //子树必须是平衡树
        if (!(leftInfo.isBalanced && rightInfo.isBalanced)) {
            isBalanced = false;
        }

        //子树的高度差不超过1
        if (!(Math.abs(leftInfo.heights - rightInfo.heights) <= 1)) {
            isBalanced = false;
        }

        return new info(isBalanced, height);

    }

    public static boolean isBalanced1(Node head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    public static int process1(Node head, boolean[] ans) {
        if (!ans[0] || head == null) {
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBalanced1(head) != isBalanced2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    public static class info {
        public boolean isBalanced;
        public int heights;

        public info(boolean isBalanced, int heights) {
            this.isBalanced = isBalanced;
            this.heights = heights;
        }
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }


}
