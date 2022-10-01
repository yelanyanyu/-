import java.util.Scanner;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class JudgmentTriangle_2316 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        boolean flag = ((a + b) > c && (a + c) > b && (b + c) > a) ? true : false;
        System.out.println((flag)?"Is a triangle":"Not a triangle");
    }
}
