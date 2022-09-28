package com.yelanyanyu.code;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class TreeMaxWidth {
    private static int maxWidthNoMap(Node head) {
        if (head == null) {
            return 0;
        }
        int max = 0;
        int curNodes = 0;
        Node curEnd = head;
        Node nextEnd = null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            if (curNode != null) {
                curNodes++;
            }
            if (curNode.left != null) {
                nextEnd = curNode.left;
                queue.add(curNode.left);
            }
            if (curNode.right != null) {
                nextEnd = curNode.right;
                queue.add(curNode.right);
            }
            if (curNode == curEnd) {
                curEnd = nextEnd;
                max = Math.max(max, curNodes);
                curNodes = 0;
            }
        }
        return max;
    }


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
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxWidthUseMap(head) != maxWidthNoMap(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }

    private static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // key 在 哪一层，value
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1; // 当前你正在统计哪一层的宽度
        int curLevelNodes = 0; // 当前层curLevel层，宽度目前是多少
        int max = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (cur.left != null) {
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
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
