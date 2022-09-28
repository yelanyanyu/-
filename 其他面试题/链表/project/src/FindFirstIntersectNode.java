import org.junit.jupiter.api.Test;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class FindFirstIntersectNode {
    private static Node getIntersectNode(Node head1, Node head2) {
        Node loop1 = isLoop(head1);
        Node loop2 = isLoop(head2);
        if (loop1 == null && loop2 == null) {
            return noLoopIntersect(head1, head2);
        } else {
            return bothLoopIntersect(head1, loop1, head2, loop2);
        }
    }

    //相交返回相交的节点，否则返回null
    public static Node noLoopIntersect(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;

        //计算两条链表的长度差
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }

        //相交链表必有相同尾节点
        if (cur1 != cur2) {
            return null;
        }

        cur1 = (n > 0) ? head1 : head2;
        cur2 = (cur1 == head1) ? head2 : head1;
        n = Math.abs(n);
        //走差值步
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }

        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }
//只有一个有环的两个链表不可能相交
//    public static Node oneLoopIntersect(Node head1, Node head2) {
//
//    }

    public static Node bothLoopIntersect(Node head1, Node loop1, Node head2, Node loop2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        if (loop1 == loop2) {
            Node cur1 = head1;
            Node cur2 = head2;
            int n = 0;

            //计算两条链表的长度差
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }

            //相交链表必有相同尾节点
            if (cur1 != cur2) {
                return null;
            }

            cur1 = (n > 0) ? head1 : head2;
            cur2 = (cur1 == head1) ? head2 : head1;
            n = Math.abs(n);
            //走差值步
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }

            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            Node cur = loop1.next;
            while (cur != loop1) {//如果走回了自己都没有找到loop2，则不相交
                if (cur == loop2) {
                    return loop1;
                }
                cur = cur.next;
            }
            return null;
        }
    }


    private static int countListLen(Node head) {
        if (head == null) {
            return 0;
        }
        Node cur = head;
        int count = 0;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    private static Node isLoop(Node head) {
        if (head == null) {
            return null;
        }
        Node fast = head;
        Node low = head;
        if (fast.next == null || low.next == null) {
            return null;
        }
        fast = fast.next.next;
        low = low.next;
        while (fast.value != low.value) {
            if (fast.next == null || low.next == null) {
                return null;
            }
            fast = fast.next.next;
            low = low.next;
        }
        //此时快慢指针已经相遇
        fast = head;
        while (fast.value != low.value) {
            fast = fast.next;
            low = low.next;
        }
        return fast;

    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }

    @Test
    public void Test01_isLoop() {
        Node head1 = new Node(1);


        // 1->2->3->4->5->6->7->null
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);
//        System.out.println(isLoop(head1));
    }

    @Test
    public void Test02_countListLen() {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        System.out.println(countListLen(head1));
    }

    // 如果两个链表都无环，返回第一个相交节点，如果不想交，返回null
//    public static Node noLoop(Node head1, Node head2) {
//        if (head1 == null || head2 == null) {
//            return null;
//        }
//        Node cur1 = head1;
//        Node cur2 = head2;
//        int n = 0;
//        while (cur1.next != null) {
//            n++;
//            cur1 = cur1.next;
//        }
//        while (cur2.next != null) {
//            n--;
//            cur2 = cur2.next;
//        }
//        //n为正，1长，反之2短，另外相同
//        if (cur1 != cur2) {
//            return null;
//        }
//        // n  :  链表1长度减去链表2长度的值
//        cur1 = n > 0 ? head1 : head2; // 谁长，谁的头变成cur1
//        cur2 = cur1 == head1 ? head2 : head1; // 谁短，谁的头变成cur2
//        n = Math.abs(n);
//        while (n != 0) {
//            n--;
//            cur1 = cur1.next;
//        }
//        while (cur1 != cur2) {
//            cur1 = cur1.next;
//            cur2 = cur2.next;
//        }
//        return cur1;
//    }
//
//    // 两个有环链表，返回第一个相交节点，如果不想交返回null
//    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
//        Node cur1 = null;
//        Node cur2 = null;
//        if (loop1 == loop2) {
//            cur1 = head1;
//            cur2 = head2;
//            int n = 0;
//            while (cur1 != loop1) {
//                n++;
//                cur1 = cur1.next;
//            }
//            while (cur2 != loop2) {
//                n--;
//                cur2 = cur2.next;
//            }
//            cur1 = n > 0 ? head1 : head2;
//            cur2 = cur1 == head1 ? head2 : head1;
//            n = Math.abs(n);
//            while (n != 0) {
//                n--;
//                cur1 = cur1.next;
//            }
//            while (cur1 != cur2) {
//                cur1 = cur1.next;
//                cur2 = cur2.next;
//            }
//            return cur1;
//        } else {
//            cur1 = loop1.next;
//            //遇到了loop2，就相交
//            while (cur1 != loop1) {
//                //走回了自己的loop，都没有找到loop2，就不相交
//                if (cur1 == loop2) {
//                    return loop1;
//                }
//                cur1 = cur1.next;
//            }
//            return null;
//        }
//    }
//
//    // 找到链表第一个入环节点，如果无环，返回null
//    public static Node getLoopNode(Node head) {
//        if (head == null || head.next == null || head.next.next == null) {
//            return null;
//        }
//        // n1 慢  n2 快
//        Node slow = head.next; // n1 -> slow
//        Node fast = head.next.next; // n2 -> fast
//        while (slow != fast) {
//            if (fast.next == null || fast.next.next == null) {
//                return null;
//            }
//            fast = fast.next.next;
//            slow = slow.next;
//        }
//        // slow fast  相遇
//        fast = head; // n2 -> walk again from head
//        while (slow != fast) {
//            slow = slow.next;
//            fast = fast.next;
//        }
//        return slow;
//    }
//
//    public static Node getIntersectNode(Node head1, Node head2) {
//        if (head1 == null || head2 == null) {
//            return null;
//        }
//        Node loop1 = getLoopNode(head1);
//        Node loop2 = getLoopNode(head2);
//        if (loop1 == null && loop2 == null) {
//            return noLoop(head1, head2);
//        }
//        if (loop1 != null && loop2 != null) {
//            return bothLoop(head1, loop1, head2, loop2);
//        }
//        return null;
//    }
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }


}
