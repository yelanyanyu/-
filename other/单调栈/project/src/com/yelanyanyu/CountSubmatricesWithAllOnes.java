package com.yelanyanyu;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class CountSubmatricesWithAllOnes {
    //    https://leetcode.cn/problems/count-submatrices-with-all-ones

    /**
     * @return 以第i层为地基的子矩形个数
     */
    private static int countSubmatrixByRows(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                Integer pop = stack.pop();
                int x = stack.isEmpty() ? -1 : stack.peek();
                int y = i;
                count += count(heights, x, y, heights[pop]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            int x = stack.isEmpty() ? -1 : stack.peek();
            int y = -1;
            count += count(heights, x, y, heights[pop]);
        }
        return count;
    }

    /**
     * 计算个数
     *
     * @param left  左边最近的小于值
     * @param right 右边最近的小于值
     * @return
     */
    private static int count(int[] heights, int left, int right, int x) {
        //需要长度L
        //需要两侧最近小于值的最大值
        int L = 0;
        int max = 0;
        /*
        优化逻辑
         */
        max = Math.max(left == -1 ? 0 : heights[left], right == -1 ? 0 : heights[right]);
        right = right == -1 ? heights.length : right;
        L = right - left - 1;
        /*
        基础逻辑
         */
//        if (left == -1 && right == -1) {
//            L = heights.length;
//        } else if (left == -1 && right != -1) {
//            L = right - left - 1;
//            max = heights[right];
//        } else if (left != -1 && right == -1) {
//            L = heights.length - left - 1;
//            max = heights[left];
//        } else if (left != -1 && right != -1) {
//            L = right - left - 1;
//            max = Math.max(heights[right], heights[left]);
//        }
        return ((L + 1) * L / 2) * (x - max);
    }


    public int numSubmat(int[][] mat) {
        int[] heights = new int[mat[0].length];
        int count = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                heights[j] = mat[i][j] == 0 ? 0 : heights[j] + 1;
            }
            count += countSubmatrixByRows(heights);
        }
        return count;
    }

    @Test
    public void Test01() {
        int[][] mat = {
                {1, 0, 1},
                {1, 1, 0},
                {1, 1, 0}
        };
        System.out.println(numSubmat(mat));
    }

    @Test
    public void Test02() {
        int[][] mat = {
                {0, 1, 1, 0},
                {0, 1, 1, 1},
                {1, 1, 1, 0}
        };
        System.out.println(numSubmat(mat));
    }

}
