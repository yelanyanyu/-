package com.yelanyanyu;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class MaximalRectangle {
    //    https://leetcode.cn/problems/maximal-rectangle/
    public int maximalRectangle(char[][] matrix) {
        int[] heights = dealMatrix(matrix);
        Stack<Integer> stack = new Stack<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                Integer pop = stack.pop();
                int x = stack.isEmpty() ? -1 : stack.peek();
                int y = i;
                max = Math.max(max, countS(x + 1, y - 1, heights[pop]));
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            int x = stack.isEmpty() ? -1 : stack.peek();
            int y = heights.length;
            max = Math.max(max, countS(x + 1, y - 1, heights[pop]));
        }
        return max;
    }

    private int countS(int x, int y, int height) {
        return (y - x + 1) * height;
    }

    private int[] dealMatrix(char[][] matrix) {
        int[] res = new int[matrix[0].length];
        /*
        初始化第一行
         */
        for (int i = 0; i < res.length; i++) {
            res[i] = matrix[0][i] == '0' ? 0 : 1;
        }
        /*
        得到heights数组
         */
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') {
                    res[j] = 0;
                    continue;
                }
                res[j] += 1;
            }
        }
        return res;
    }

    @Test
    public void Test01() {
        char[][] matrix = {
                {'1', '1', '0', '1'},
                {'1', '0', '1', '1'},
                {'1', '1', '1', '0'},
                {'0', '1', '1', '0'}
        };
        System.out.println(maximalRectangle(matrix));
    }
}
