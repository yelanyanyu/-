package com.yelanyanyu;

import org.junit.jupiter.api.Test;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class _775GlobalAndLocalInversions {
    //https://leetcode.cn/problems/global-and-local-inversions/description/

    /**
     * 暴力解,该方法在单调递增的数据情况下，效果最差
     *
     * @param nums
     * @return
     */
    private static boolean right(int[] nums) {
        int countG = 0;
        int countL = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    countG++;
                }
            }
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                countL++;
            }
        }
        return countG == countL;
    }

    private static boolean judge(int[] nums) {
        int len = nums.length;
        int min = nums[len - 1];
        for (int i = len - 3; i >= 0; i--) {
            min = Math.min(min, nums[i + 2]);
            if (nums[i] > min) {
                return false;
            }
        }
        return true;
    }

    public boolean isIdealPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return true;
        }
        return judge(nums);
    }

    @Test
    public void Test01() {
        int[] nums = {1,2,0,3};
        System.out.println(isIdealPermutation(nums));
    }
}
