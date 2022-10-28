package com.yelanyanyu.code;

import javax.swing.plaf.InsetsUIResource;
import java.util.ArrayList;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class isBST {

    public static boolean isBST2(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }

    public static info process(Node x) {
        if (x == null) {
            return null;
        }
        info leftInfo = process(x.left);
        info rightInfo = process(x.right);
        boolean isBST = true;
        /**
         * 假如任何一个子树的信息为null，就应该是合法的
         */
        //更新max和min
        int max = x.value;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
        }
        int min = x.value;
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
        }
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
        }
        if (leftInfo != null && leftInfo.isBST == false) {
            isBST = false;
        }
        if (rightInfo != null && rightInfo.isBST == false) {
            isBST = false;
        }
        if (leftInfo != null && leftInfo.max >= x.value) {
            isBST = false;
        }
        if (rightInfo != null && rightInfo.min <= x.value) {
            isBST = false;
        }
        return new info(max, min, isBST);
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
            if (isBST1(head) != isBST2(head)) {
                System.out.println("Oops!");
//                break;
            }
        }
        System.out.println("finish!");
    }

    public static boolean isBST1(Node head) {
        if (head == null) {
            return true;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return false;
            }
        }
        return true;
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static class info {
        public int max;
        public int min;
        public boolean isBST;

        public info(int max, int min, boolean isBST) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
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
