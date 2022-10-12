package com.yelanyanyu;

import org.junit.jupiter.api.Test;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class Football {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String posStr = scanner.nextLine();
        System.out.println(isDangerous(posStr) ? "YES" : "NO");
    }

    private static boolean isDangerous(String posStr) {
        String regExp = "1111111|0000000";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(posStr);
        while (matcher.find()) {
            return true;
        }
        return false;
    }

    @Test
    public void Test03() {
        String content="011010000051011111";
        System.out.println(isDangerous(content));
    }
}
