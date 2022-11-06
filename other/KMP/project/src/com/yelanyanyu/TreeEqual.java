package com.yelanyanyu;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class TreeEqual {
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
        int bigTreeLevel = 7;
        int smallTreeLevel = 4;
        int nodeMaxValue = 5;
        int testTimes = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node big = generateRandomBST(bigTreeLevel, nodeMaxValue);
            Node small = generateRandomBST(smallTreeLevel, nodeMaxValue);
            boolean ans1 = containsTree1(big, small);
            boolean ans2 = containsTree2(big, small);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                System.out.println(big.value);
                System.out.println(small.value);
                System.out.println("ans1= " + ans1);
                System.out.println("ans2= " + ans2);
                break;
            }
        }
        System.out.println("test finish!");

    }

    private static ArrayList<String> enCode(Node head) {
        ArrayList<String> strings = new ArrayList<>();
        if (head == null) {
            strings.add("error");
            return strings;
        }
        process(strings, head);
        return strings;
    }

    private static void process(List<String> list, Node cur) {
        if (cur == null) {
            list.add("null");
            return;
        }
        list.add(String.valueOf(cur.value));
        process(list, cur.left);
        process(list, cur.right);
    }

    public static boolean containsTree2(Node big, Node small) {
        if (small == null) {
            return true;
        }
        if (big == null) {
            return false;
        }
        if (isSameValueStructure(big, small)) {
            return true;
        }
        return containsTree2(big.left, small) || containsTree2(big.right, small);
    }

    public static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left)
                && isSameValueStructure(head1.right, head2.right);
    }

    //for test
    private static boolean containsTree1(Node big, Node small) {
        if (small == null) {
            return true;
        }
        if (big == null) {
            return false;
        }
        ArrayList<String> bigList = enCode(big);
        ArrayList<String> smallList = enCode(small);
        return KMP(bigList, smallList) != -1;
    }

    private static int KMP(ArrayList<String> bigList, ArrayList<String> smallList) {
        if (bigList.size() == 0 && smallList.size() == 0) {
            return -1;
        }
        int[] nextArray = getNextArray(smallList);
        int x = 0;
        int y = 0;
        while (x < bigList.size() && y < smallList.size()) {
            if (bigList.get(x).equals(smallList.get(y))) {
                x++;
                y++;
            } else if (nextArray[y] == -1) {
                x++;
            } else {
                y = nextArray[y];
            }
        }
        return y == smallList.size() ? x - y : -1;
    }

    private static int[] getNextArray(ArrayList<String> smallList) {
        int[] nextArray = new int[smallList.size()];
        int pn = 0;
        int i = 2;
        nextArray[0] = -1;
        nextArray[1] = 0;
        while (i < nextArray.length) {
            if (smallList.get(i - 1).equals(smallList.get(pn))) {
                nextArray[i++] = ++pn;
            } else if (pn > 0) {
                pn = nextArray[pn];
            } else {
                nextArray[i++] = 0;
            }
        }
        return nextArray;
    }

    @Test
    public void Test01() {
        Node head = generateRandomBST(5, 9);
        ArrayList<String> list = enCode(head);
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.print(next + " ");
        }
    }

    @Test
    public void Test02() {
        Node big = generateRandomBST(7, 5);
        Node small = generateRandomBST(4, 5);
        System.out.println(containsTree1(big, small));
        System.out.println(containsTree2(big, small));
    }

    /*
	判断两个树是否有某颗子树一样
	 */
    private static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }
}
