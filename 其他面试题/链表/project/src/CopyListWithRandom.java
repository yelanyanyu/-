import java.util.HashMap;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class CopyListWithRandom {

    //容器法O(N)
    private static Node copyListWithRand2(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        Node newHead = map.get(cur);
        while (cur != null) {
            map.get(cur).next = map.get(cur.next) != null ? map.get(cur.next) : null;
            map.get(cur).rand = map.get(cur.rand) != null ? map.get(cur.rand) : null;
            cur = cur.next;
        }
        return newHead;
    }

    public static Node copyListWithRand3(Node head) {
        // key 老节点
        // value 新节点
        HashMap<Node, Node> map = new HashMap<Node, Node>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            // cur 老
            // map.get(cur) 新
            // 新.next ->  cur.next克隆节点找到
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    //不用容器法O(1)
    private static Node copyListWithRand1(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node nextNode = head.next;
        /**
         * 构建克隆链表
         */
        while (cur != null) {
            Node node = new Node(cur.value);
            cur.next = node;
            node.next = nextNode;
            cur = nextNode;
            nextNode = nextNode != null ? nextNode.next : null;
        }
        /**
         * 克隆rand
         */
        Node pre = head;
        cur = head.next;
        while (cur != null) {
            cur.rand = pre.rand != null ? pre.rand.next : null;
            pre = (pre.next != null && pre.next.next != null) ? pre.next.next : null;
            cur = (cur.next != null && cur.next.next != null) ? cur.next.next : null;
        }


        cur = head.next;
        Node newHead = head.next;
        pre = head;
        /**
         * 这种办法的更新节点方式出了问题，当pre.next指向cur.next的时候pre的后继指针就是cur.next，
         * 而不是原先的cur.next。
         */
//        while (cur != null) {
//            pre.next = cur.next;
//            pre = pre.next;
//            cur = cur.next;
//        }
//        pre = null;
        while (cur != null) {
            pre.next = cur.next;
            pre = cur;
            cur = cur.next;
        }
        pre = null;
        return newHead;
    }
//    private static Node copyListWithRand1(Node head) {
//        Node newHead = new Node(head.value);
//        Node cur = head;
//        Node newCur = newHead;
//        //构建非rand的链表
//        while (cur != null) {
//            newCur.next = new Node(cur.next.value);
//            cur = cur.next;
//            newCur = newCur.next;
//        }
//        cur = head;
//        newCur = newHead;
//        while (cur != null) {
//            //newCur.rand = cur.rand;//无法得到新链表对应的rand，需根据cur的rand来得到newCur的rand，所以想到构建HashMap
//            newCur = newCur.next;
//            cur = cur.next;
//        }
//        return newHead;
//    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        //正确方法
        System.out.println("原始链表：");
        printRandLinkedList(head);
        System.out.println("=========================");
        Node res3 = copyListWithRand3(head);
        System.out.println("方法3的拷贝链表：");
        printRandLinkedList(res3);
        System.out.println("=========================");
        System.out.println("经历方法3拷贝之后的原始链表：");
        printRandLinkedList(head);
        System.out.println("======================================================");


        //方法2
        res2 = copyListWithRand2(head);
        System.out.println("方法二的拷贝链表：");
        printRandLinkedList(res2);
        System.out.println("=========================");
        System.out.println("经历方法二拷贝之后的原始链表：");
        printRandLinkedList(head);
        System.out.println("========================================================");


        //方法1
        System.out.println("=========================");
        res1 = copyListWithRand1(head);
        System.out.println("方法一的拷贝链表：");
        printRandLinkedList(res1);
        System.out.println("=========================");
        System.out.println("经历方法1拷贝之后的原始链表：");
        printRandLinkedList(head);
        System.out.println("=======================================================");


    }

    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }

    //用容器
//    public static Node copyListWithRand1(Node head) {
//        // key 老节点
//        // value 新节点
//        HashMap<Node, Node> map = new HashMap<Node, Node>();
//        Node cur = head;
//        while (cur != null) {
//            map.put(cur, new Node(cur.value));
//            cur = cur.next;
//        }
//        cur = head;
//        while (cur != null) {
//            // cur 老
//            // map.get(cur) 新
//            // 新.next ->  cur.next克隆节点找到
//            map.get(cur).next = map.get(cur.next);
//            map.get(cur).rand = map.get(cur.rand);
//            cur = cur.next;
//        }
//        return map.get(head);
//    }


}
