package com.yelanyanyu;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class MonotonousStack {
    // for test
    public static int[] getRandomArrayNoRepeat(int size) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            int swapIndex = (int) (Math.random() * arr.length);
            int tmp = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }

    // for test
    public static int[] getRandomArray(int size, int max) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    /**
     * 暴力方法
     *
     * @param arr
     * @return
     */
    public static int[][] rightWay(int[] arr) {
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            int cur = i - 1;
            while (cur >= 0) {
                if (arr[cur] < arr[i]) {
                    leftLessIndex = cur;
                    break;
                }
                cur--;
            }
            cur = i + 1;
            while (cur < arr.length) {
                if (arr[cur] < arr[i]) {
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }
            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[][] res1, int[][] res2) {
        if (res1.length != res2.length) {
            return false;
        }
        for (int i = 0; i < res1.length; i++) {
            if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
                return false;
            }
        }

        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int size = 10;
        int max = 20;
        int testTimes = 2000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = getRandomArrayNoRepeat(size);
            int[] arr2 = getRandomArray(size, max);
            if (!isEqual(getNearLessNoRepeat(arr1), rightWay(arr1))) {
                System.out.println("Oops!");
                System.out.println(Arrays.toString(arr1));
                break;
            }
            if (!isEqual(getNearLess(arr2), rightWay(arr2))) {
                System.out.println("Oops!2");
                System.out.println(Arrays.toString(arr2));
                break;
            }
        }
        System.out.println("测试结束");
    }

    /**
     * 支持重复值
     *
     * @param arr
     * @return
     */
    public static int[][] getNearLess(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            //弹出不符合单调条件的链表
            while (!stack.isEmpty() && arr[i] < arr[stack.peek().get(0)]) {
                List<Integer> list = stack.pop();
                for (Integer integer : list) {
                    //stack.peek().get(stack.peek().size() - 1) 代表栈顶链表的最后一个元素
                    res[integer][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                    res[integer][1] = i;
                }
            }
            //如果相同就加入同一个队列
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else { //若栈为空或者不同就直接入栈
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        //弹出剩余元素
        while (!stack.isEmpty()) {
            List<Integer> curList = stack.pop();
            for (Integer integer : curList) {
                res[integer][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                res[integer][1] = -1;
            }
        }
        return res;
    }

    /**
     * 只适用于无重复值的时候
     *
     * @param arr
     * @return
     */
    private static int[][] getNearLessNoRepeat(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                Integer index = stack.pop();
                res[index][0] = stack.isEmpty() ? -1 : stack.peek();
                res[index][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer index = stack.pop();
            res[index][0] = stack.isEmpty() ? -1 : stack.peek();
            res[index][1] = -1;
        }
        return res;
    }

    @Test
    public void Test01() {
        int[] arr = {-10, 10, 1, 10, 4, 3, 1};
        int[][] res = rightWay(arr);
        int[][] nearLess = getNearLess(arr);
    }

}
