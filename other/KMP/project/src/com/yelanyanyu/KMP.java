package com.yelanyanyu;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class KMP {
    private static int getIndexOf(String s1, String s2) {
        // TODO: 2022/10/30
        //无效条件
        if (s1 == null || s2 == null || s2.length() < 1 || s1.length() < s2.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int x = 0;//s1 中第一个不匹配的位置
        int y = 0;//s2 中相应第一个不匹配的位置
        // O(M) m <= n
        int[] next = getNextArray(str2);//next数组
        // O(N)
        //若y越界了，就匹配到了（只有一直相等y才会跳出s2）
        //x越界了，说明尝试了所有的开头都没有找到匹配
        while (x < str1.length && y < str2.length) {
            //相等就往后移，直到找到不匹配的
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) { // y == 0，只有0位置的next值为-1
                //已经不能往左跳了
                x++;//相当于s2再次右移，将y与x对齐，此时y==0
            } else {//还能往左跳，就将s2“右移”，s1不动
                y = next[y];
            }
        }
        //x-y就代表开头的位置，因为y越界了就相当于y=s2的长度
        return y == str2.length ? x - y : -1;
    }

    public static int[] getNextArray(char[] str2) {
        if (str2.length == 1) {
            return new int[] { -1 };
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2; // 目前在哪个位置上求next数组的值
        int cn = 0; // 当前是哪个位置的值再和i-1位置的字符比较， cn就是前缀的后一个位置
        while (i < next.length) {
            if (str2[i - 1] == str2[cn]) { // 配成功的时候
                next[i++] = ++cn;
            } else if (cn > 0) {
                //cn还可以左移就左移
                cn = next[cn];
            } else {
                //cn移不动了，i位置next值就为0
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = StringTestUtils.getRandomString(possibilities, strSize);
            String match = StringTestUtils.getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) != str.indexOf(match)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
