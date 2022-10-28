package com.yelanyanyu.code;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class isFull {

    public static boolean isFull2(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isFull;
    }

    /**
     * 判断x是否为满二叉树，返回信息info
     *
     * @param x
     * @return
     */
    public static info process(Node x) {
        if (x == null) {
            return new info(0, 0, true);
        }
        info left = process(x.left);
        info right = process(x.right);
        boolean isFull = true;
        if (left.isFull == false || right.isFull == false) {
            isFull = false;
        }
        int leftNodes = left.nodes;
        int rightNodes = right.nodes;
        int curHeight = Math.max(left.heights, right.heights) + 1;
        if ((Math.pow(2, curHeight) - 1) != (double) (leftNodes + rightNodes + 1)) {
            isFull = false;
        }
        return new info(curHeight, leftNodes + rightNodes + 1, isFull);

    }

    public static boolean isFull1(Node head) {
        if (head == null) {
            return true;
        }
        int height = h(head);
        int nodes = n(head);
        return (1 << height) - 1 == nodes;
    }

    public static int h(Node head) {
        if (head == null) {
            return 0;
        }
        return Math.max(h(head.left), h(head.right)) + 1;
    }

    public static int n(Node head) {
        if (head == null) {
            return 0;
        }
        return n(head.left) + n(head.right) + 1;
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
            if (isFull1(head) != isFull2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    public static class info {
        public int heights; //树深度（高度）
        public int nodes;   //节点数
        public boolean isFull;

        public info(int heights, int nodes, boolean isFull) {
            this.heights = heights;
            this.nodes = nodes;
            this.isFull = isFull;
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
