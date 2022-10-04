package com.yelanyanyu.code;

import java.util.ArrayList;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class MaxSubBSTSize {
    //    返回最大子搜索二叉树的节点数。整棵树可以不是 BST，但是子树可以使搜索二叉树，返回最大的子树节点个数即可。
    public static int maxSubBSTSize2(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxSubSize;
    }

    public static info process(Node x) {
        if (x == null) {
            return new info(true, 0, Integer.MAX_VALUE, -1, 0);
        }
        info leftInfo = process(x.left);
        info rightInfo = process(x.right);
        //更新isBST
        boolean isBST = (leftInfo.isBST && rightInfo.isBST) &&
                (x.value > leftInfo.max && x.value < rightInfo.min);
        //更新curTreeSize
        int newCurTreeSize = leftInfo.curTreeSize + rightInfo.curTreeSize + 1;
        //更新max与min
        int newMax = Math.max(Math.max(leftInfo.max, rightInfo.max), x.value);
        int newMin = Math.min(Math.min(leftInfo.min, rightInfo.min), x.value);
        //更新maxSubSize
        int newMaxSubSize = 0;
        if (isBST) {    //x为BST
            //若总树是BST，那么其子树必为BST
            newMaxSubSize = leftInfo.maxSubSize + rightInfo.maxSubSize + 1;
        } else {    //x不为BST
            newMaxSubSize = Math.max(leftInfo.maxSubSize, rightInfo.maxSubSize);
        }
        return new info(isBST, newMaxSubSize, newMin, newMax, newCurTreeSize);
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
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTSize1(head) != maxSubBSTSize2(head)) {
                System.out.println("Oops!" + maxSubBSTSize1(head) + " " + maxSubBSTSize2(head));
            }
        }
        System.out.println("finish!");
    }

    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static int maxSubBSTSize1(Node head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
    }

    public static class info {
        public boolean isBST;//是否是BST
        public int maxSubSize;//最大二叉搜索树的节点数量

        public int min;//最小节点值
        public int max;//最大节点值

        public int curTreeSize;//当前树的总节点树

        public info(boolean isBST, int maxSubSize, int min, int max, int curTreeSize) {
            this.isBST = isBST;
            this.maxSubSize = maxSubSize;
            this.min = min;
            this.max = max;
            this.curTreeSize = curTreeSize;
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
