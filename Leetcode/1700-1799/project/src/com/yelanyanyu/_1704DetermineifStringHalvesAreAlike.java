package com.yelanyanyu;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class _1704DetermineifStringHalvesAreAlike {
    public boolean halvesAreAlike(String s) {
        HashSet<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('e');
        set.add('I');
        set.add('O');
        set.add('U');
        int i = 0;
        int countL = 0;
        int j = s.length() - 1;
        int countR = 0;
        while (i < j) {
            if (set.contains(s.charAt(i++))) {
                countL++;
            }
            if (set.contains(s.charAt(j--))) {
                countR++;
            }
        }
        return countL == countR;
    }

    @Test
    public void Test01() {
        String s = "textbook";
        System.out.println(halvesAreAlike(s));
    }
}
