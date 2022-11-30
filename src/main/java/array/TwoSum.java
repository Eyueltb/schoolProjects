package array;

import java.util.*;

/**
 * Q1. Given a list of numbers and a number k, return whether any two numbers from the list add up to k.
 * For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.
 * Bonus: Can you do this in one pass?
 *
 * Q2. Find the first two numbers in "nums", that when added gives "total"
 *  where nums = array of numbers, total = total sum
 *  return [n1, n2]
 *
 * Q3- Given an array of integers nums and an integer target,
 *   return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 * Example 1:
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * Example 2:
 *
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * Example 3:
 *
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 *
 * Constraints:
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * Only one valid answer exists.
 * Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
 */
public class TwoSum {
    /**
     * Given a list of numbers and a number k, return whether any two numbers from the list add up to k.
     * Given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.
     */
    private static boolean twoSumFound(int [] A, int K) {
        List<Integer> found = new ArrayList<>();
        for (int num : A){
            if(found.contains(num))
                return true;
            else
                found.add(K-num);
       }
        return false;
    }

    /**
     * Given a list of numbers and a number k, return two numbers from the list add up to k.
     * [10, 15, 3, 7] and K = 17 then return [10, 7]
     */
    private static int [] twoSumElement(int [] A, int K) {
        List<Integer> found = new ArrayList<>();
        for (int num : A) {
            if (found.contains(num))
                return new int[]{K - num, num};
            else
                found.add(K-num);
        }
        throw new IllegalArgumentException("No match");
    }
    /**
     * Given a list of numbers and a number k, return the index from the list add up to k.
     * [10, 15, 3, 7] and K = 17 then return [0, 3]
     */
    private static int [] twoSumIndex(int [] A, int K) {
        Map<Integer, Integer> result = new HashMap<>();
        for(int i = 0; i < A.length ; i++) {
            if(result.containsKey(K-A[i])){
                return new int[] { result.get(K-A[i]), i};
            }
            result.put(A[i], i);
        }
        throw new IllegalArgumentException("No match");
    }

    public static void main(String[] args) {

        System.out.println(twoSumFound(new int[] { 10, 15, 3, 7}, 17)); //true-> [10, 7]
        System.out.println(twoSumFound(new int[] { 3, 1, 5, 7, 5, 9  }, 10)); //true->[3, 7] or [5 ,5] or [9,1]
        System.out.println(twoSumFound(new int[] {20, 2, 9, 1, 10, 2, 6, 8, 5, 7, 4, -7, 12, 14, 15 }, 18)); //true -> [10, 8]

        Arrays.stream(twoSumElement(new int[] { 10, 15, 3, 7 }, 17)).forEach(i-> System.out.print(i + " "));
        System.out.println();
        Arrays.stream(twoSumElement(new int[] { 3, 1, 5, 7, 5, 9  }, 10)).forEach(i-> System.out.print(i + " "));
        System.out.println();
        Arrays.stream(twoSumElement(new int[] {20, 2, 9, 1, 10, 2, 6, 8, 5, 7, 4, -7, 12, 14, 15 }, 18)).forEach(i-> System.out.print(i + " "));

        System.out.println("...");

        Arrays.stream(twoSumIndex(new int[] { 10, 15, 3, 7 }, 17)).forEach(i-> System.out.print(i + " "));
        System.out.println();
        Arrays.stream(twoSumIndex(new int[] { 5,3, 1, 7, 5,  9  }, 10)).forEach(i-> System.out.print(i + " "));
        System.out.println();
        Arrays.stream(twoSumIndex(new int[] {20, 2, 9, 1, 10, 2, 6, 8, 5, 7, 4, -7, 12, 14, 15 }, 18)).forEach(i-> System.out.print(i + " "));
    }
}
