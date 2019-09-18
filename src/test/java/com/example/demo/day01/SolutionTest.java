package com.example.demo.day01;

import com.example.demo.AlgorithmApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * @className: SolutionTest
 * @description: TODO
 * @author: wangzb01
 * @version: V1.0
 * @since: V1.0
 * @date: 2019/9/18 14:10
 */
@Slf4j
public class SolutionTest extends AlgorithmApplicationTests{

    @Autowired
    private Solution solution;

    @Test
    public void twoSum() {
        int[] nums = {2, 7, 11, 15};
        int[] twoSum = solution.twoSum(nums, 18);
        System.out.println(Arrays.toString(twoSum));
    }

    @Test
    public void repeatedSubstringPattern() {
        boolean b = solution.repeatedSubstringPattern("qwerqwerqwerqwer");
        System.out.println(b);
    }

    @Test
    public void relativeSortArray() {
    }

    @Test
    public void canPartitionKSubsets() {
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        boolean b = solution.canPartitionKSubsets(nums, 4);
        System.out.println(b);
    }

    @Test
    public void searchMatrix() {
        int[][] matrix = {
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        boolean b = solution.searchMatrix(matrix, 20);
        System.out.println(b);
    }

    @Test
    public void change() {
        int[] coins = {2};
        int change = solution.change(3, coins);
        System.out.println(change);
    }
}