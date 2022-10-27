package com.yelanyanyu;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class MaximalRectangle {
    //    https://leetcode.cn/problems/maximal-rectangle/
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int[] heights = new int[matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                heights[j] = matrix[i][j] == '0' ? 0 : heights[j] + 1;
            }
            max = Math.max(max, maxRectangleByRows(heights));
        }
        return max;
    }


    private int maxRectangleByRows(int[] heights) {
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
