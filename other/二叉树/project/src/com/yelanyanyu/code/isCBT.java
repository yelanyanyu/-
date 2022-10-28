package com.yelanyanyu.code;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import java.util.LinkedList;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class isCBT {
//    1. 如果两颗子树有一颗不是完全二叉树，就不是；
//    2. 设左右子树的高度分别为 h1，与 h2，若 h1< h2，就不是；
//    3. h1> h2 ，且高度差不超过1。就是；
//    4. h1 == h2，若两颗子树都是满二叉树，就是，否则就不是；

    public static boolean isCBT2(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isCBT;
    }

    public static info process(Node x) {
        if (x == null) {
            return new info(true, true, 0);
        }
        info leftInfo = process(x.left);
        info rightInfo = process(x.right);
        //更新数据
        int h1 = leftInfo.heights;
        int h2 = rightInfo.heights;
        int newHeight = Math.max(h2, h1) + 1;

        boolean isFull = (h2 == h1) &&
                (leftInfo.isFull && rightInfo.isFull);
        //判断
        boolean isCBT = false;
        if (isFull) {
            isCBT = true;
        } else {
            if (h1 - h2 == 1) {
                if (leftInfo.isFull && rightInfo.isFull) {
                    isCBT = true;
                }
                if (leftInfo.isCBT && rightInfo.isFull) {
                    isCBT = true;
                }
            }
            if (h1 == h2 && leftInfo.isFull && rightInfo.isCBT) {
                isCBT = true;
            }
        }

        return new info(isCBT, isFull, newHeight);
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
            if (isCBT1(head) != isCBT2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    public static boolean isCBT1(Node head) {
        if (head == null) {
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;//开关
        Node l = null;
        Node r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if (
                // 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
                    (leaf && (l != null || r != null))
                            ||
                            (l == null && r != null)

            ) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {//碰到了左右孩子残缺的情况，开启开关
                leaf = true;
            }
        }
        return true;
    }

    public static class info {
        public boolean isCBT;
        public boolean isFull;
        public int heights;

        public info(boolean isCBT, boolean isFull, int heights) {
            this.isCBT = isCBT;
            this.isFull = isFull;
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
