package com.yelanyanyu;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class LargestRectangleInHistogram {
    //    https://leetcode.com/problems/largest-rectangle-in-histogram
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                Integer pop = stack.pop();
                int x = stack.isEmpty() ? -1 : stack.peek();
                max = Math.max(max, countS(heights, heights[pop], x + 1, i - 1));
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            int x = stack.isEmpty() ? -1 : stack.peek();
            max = Math.max(max, countS(heights, heights[pop], x + 1, heights.length - 1));
        }
        return max;
    }

    public static int largestRectangleArea1(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int curArea = (i - k - 1) * height[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }

    private int countS(int[] heights, int height, int x, int y) {
        return (y - x + 1) * height;
    }

    @Test
    public void Test01() {
        int[] heights = {2, 2, 2, 2, 2, 2};
        System.out.println(largestRectangleArea(heights));
    }
}
