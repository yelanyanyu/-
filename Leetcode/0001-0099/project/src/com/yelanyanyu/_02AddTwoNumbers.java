package com.yelanyanyu;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class _02AddTwoNumbers {

    private static String listToString(ListNode head) {
        ListNode cur = head;
        StringBuilder builder = new StringBuilder();
        while (cur != null) {
            builder.append(cur.val);
            cur = cur.next;
        }
        return builder.reverse().toString();
    }

    private static void printList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + "->");
            cur = cur.next;
        }
        System.out.println("null");
    }

    //常规做法
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode cur_l1 = l1;
        ListNode cur_l2 = l2;
        ListNode head = null;
        ListNode cur = null;
        int carry = 0;
        int sum = 0;
        while (cur_l1 != null || cur_l2 != null) {
            sum = (cur_l1 != null ? cur_l1.val : 0) + (cur_l2 != null ? cur_l2.val : 0)
                    + carry;
            carry = 0;
            // TODO: 2022/11/6
            if (sum >= 10) {
                carry = 1;
                sum -= 10;
            }
            if (cur_l1 == l1) {
                head = new ListNode(sum);
                cur = head;
            } else {
                cur.next = new ListNode(sum);
                cur = cur.next;
            }
            cur_l1 = cur_l1 == null ? null : cur_l1.next;
            cur_l2 = cur_l2 == null ? null : cur_l2.next;
        }
        return head;
    }

    //api
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        String s1 = listToString(l1);
        String s2 = listToString(l2);
        String res = new BigInteger(s1).add(new BigInteger(s2)).toString();
        String resStr = String.valueOf(res);
        resStr = new StringBuilder(resStr).reverse().toString();
        ListNode head = new ListNode(resStr.charAt(0) - '0');
        ListNode cur = head;
        for (int i = 1; i < resStr.length(); i++) {
            cur.next = new ListNode(resStr.charAt(i) - '0');
            cur = cur.next;
        }
        return head;
    }

    @Test
    public void Test01() {
        ListNode head1 = new ListNode(2);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(3);
        ListNode head2 = new ListNode(5);
        head2.next = new ListNode(6);
        head2.next.next = new ListNode(4);
        printList(addTwoNumbers1(head1, head2));
    }

    @Test
    public void Test02() {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(1);
        head1.next.next = new ListNode(1);
        head1.next.next.next = new ListNode(1);

        ListNode head2 = new ListNode(2);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(2);
        printList(addTwoNumbers1(head1, head2));
        printList(addTwoNumbers2(head1, head2));
    }

    @Test
    public void Test03() {

    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
