import org.junit.jupiter.api.Test;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class MyTest {

    @Test
    public void Test01_() {
        IsPalindromeList.Node head ;
        head = new IsPalindromeList.Node(1);
        head.next = new IsPalindromeList.Node(2);
        head.next.next = new IsPalindromeList.Node(3);
        IsPalindromeList.printLinkedList(head);
        IsPalindromeList.Node node = IsPalindromeList.reverseList(head);
        IsPalindromeList.printLinkedList(node);
    }
}
