package com.example.demo.day01;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @className: Solution
 * @description: TODO
 * @author: wangzb01
 * @version: V1.0
 * @since: V1.0
 * @date: 2019/9/18 9:24
 */
@Component
public class Solution {

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     * 示例:
     * 给定 nums = [2, 7, 11, 15], target = 9
     * <p>
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     */
    public int[] twoSum(int[] nums, int target) {
        int[] indexs = new int[2];
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i])) {
                indexs[0] = hashMap.get(nums[i]);
                indexs[1] = i;
                return indexs;
            }
            hashMap.put(target - nums[i], i);
        }
        return indexs;
    }

    /**
     * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
     * 示例 1:
     * 输入: "abab"
     * 输出: True
     * <p>
     * 解释: 可由子字符串 "ab" 重复两次构成。
     * <p>
     * 示例 2:
     * 输入: "aba"
     * 输出: False
     */
    public boolean repeatedSubstringPattern(String s) {
        // 1.将原字符串给出拷贝一遍组成新字符串； 2.掐头去尾留中间； 3.如果还包含原字符串，则满足题意。
        return (s + s).substring(1, s.length() * 2 - 1).contains(s);
    }


    /**
     * 给你两个数组，arr1 和 arr2，
     * arr2 中的元素各不相同
     * arr2 中的每个元素都出现在 arr1 中
     * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。
     * 未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
     * <p>
     * 示例：
     * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
     * 输出：[2,2,2,1,4,3,3,9,6,7,19]
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        //todo
        return null;
    }

    /**
     * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
     * <p>
     * 示例 1：
     * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
     * 输出： True
     * <p>
     * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        // 注意nums[i] > 0
        int sum = 0, maxNum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (maxNum < nums[i]) {
                maxNum = nums[i];
            }
        }
        if (sum % k != 0 || maxNum > sum / k) {
            return false;
        }
        boolean[] used = new boolean[nums.length];
        return backtracking(nums, k, sum / k, 0, 0, used);

    }

    private boolean backtracking(int[] nums, int k, int target, int cur, int start, boolean[] used) {
        // 返回条件
        if (k == 0) {
            return true;
        }
        if (cur == target) {
            // 构建下一个集合
            return backtracking(nums, k - 1, target, 0, 0, used);
        }
        for (int i = start; i < nums.length; i++) {
            if (!used[i] && cur + nums[i] <= target) {
                used[i] = true;
                if (backtracking(nums, k, target, cur + nums[i], i + 1, used)) {
                    return true;
                }
                used[i] = false;
            }
        }
        return false;
    }

    /**
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     * 示例:
     * 现有矩阵 matrix 如下：
     * [
     * [1,   4,  7, 11, 15],
     * [2,   5,  8, 12, 19],
     * [3,   6,  9, 16, 22],
     * [10, 13, 14, 17, 24],
     * [18, 21, 23, 26, 30]
     * ]
     * <p>
     * 给定 target = 5，返回 true。
     * 给定 target = 20，返回 false。
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }
        //起点为最右上角的元素
        int row = 0, col = matrix[0].length - 1;
        //判断当前数组元素和target，如果当前大于target，往左走；小与target，往下走
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] < target) {
                row++;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                return true;
            }
        }
        //走出边界了还没找到，说明不存在，返回false
        return false;
    }

    /**
     * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 
     * 示例 1:
     * 输入: amount = 5, coins = [1, 2, 5]
     * 输出: 4
     * <p>
     * 解释: 有四种方式可以凑成总金额:
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     * <p>
     * 示例 2:
     * 输入: amount = 3, coins = [2]
     * 输出: 0
     * 解释: 只用面额2的硬币不能凑成总金额3。
     */
    public int change(int amount, int[] coins) {
        if (coins == null) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    /**
     * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
     * （这里，平面上两点之间的距离是欧几里德距离。）
     * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
     * 示例 1：
     * 输入：points = [[1,3],[-2,2]], K = 1
     * 输出：[[-2,2]]
     *
     * 解释：
     * (1, 3) 和原点之间的距离为 sqrt(10)，
     * (-2, 2) 和原点之间的距离为 sqrt(8)，
     * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
     * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
     *
     * 示例 2：
     * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
     * 输出：[[3,3],[-2,4]]
     *
     * （答案 [[-2,4],[3,3]] 也会被接受。）
     */
    public int[][] kClosest(int[][] points, int K) {
        int n = points.length;
        int[] dists = new int[n];
        for (int i = 0; i < n; ++i) {
            dists[i] = dist(points[i]);
        }

        Arrays.sort(dists);
        int distK = dists[K-1];

        int[][] ans = new int[K][2];
        int t = 0;
        for (int[] point : points) {
            if (dist(point) <= distK) {
                ans[t++] = point;
            }
        }
        return ans;
    }

    private int dist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    /**
     * 在一个仓库里，有一排条形码，其中第 i 个条形码为 barcodes[i]。
     * 请你重新排列这些条形码，使其中两个相邻的条形码 不能 相等。 你可以返回任何满足该要求的答案，此题保证存在答案。
     * 示例 1：
     * 输入：[1,1,1,2,2,2]
     * 输出：[2,1,2,1,2,1]
     *
     * 示例 2：
     * 输入：[1,1,1,1,2,2,3,3]
     * 输出：[1,3,1,3,2,1,2,1]
     */
    public int[] rearrangeBarcodes(int[] barcodes) {
        /* 存在特殊情况结果类似 2, 1, 2, 1, 2
         * 因此优先使用出现次数最多的元素填充奇数位
         */
        /* 统计每个数据的出现次数 */
        int len = barcodes.length;
        int[] count = new int[10001];
        for (int i = 0; i < len; i++) {
            count[barcodes[i]]++;
        }
        /* 得到出现次数最多的数字 */
        int maxCnt = 0;
        int maxNum = 0;
        for (int i = 1; i < 10001; i++) {
            if (count[i] > maxCnt) {
                maxCnt = count[i];
                maxNum = i;
            }
        }
        /* 先填充奇数位 */
        int[] result = new int[len];
        // result 填充位置
        int pos = 0;
        // count 使用位置
        int idx = 0;
        /* 先使用出现次数最多的数字填充奇数位, 最多恰好填满 */
        while (pos < len) {
            if (count[maxNum] <= 0) {
                break;  // 填充完毕
            } else {
                count[maxNum]--;
                result[pos] = maxNum;
                pos += 2;
            }
        }
        /* 尝试继续填充奇数位 */
        filling(len, count, result, pos, idx);
        /* 继续填充偶数位 */
        pos = 1;
        filling(len, count, result, pos, idx);
        return result;
    }

    private void filling(int len, int[] count, int[] result, int pos, int idx) {
        while (pos < len) {
            if (count[idx] <= 0) {
                idx++;
                continue;
            } else {
                count[idx]--;
                result[pos] = idx;
                pos += 2;
            }
        }
    }


}
