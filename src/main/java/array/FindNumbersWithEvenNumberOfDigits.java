package array;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * LeetCode Questions
 * Given an array nums of integers, return how many of them contain an even number of digits.
 * Example 1:
 * Input: nums = [12,345,2,6,7896]
 * Output: 2
 * Explanation:
 * 12 contains 2 digits (even number of digits).
 * 345 contains 3 digits (odd number of digits).
 * 2 contains 1 digit (odd number of digits).
 * 6 contains 1 digit (odd number of digits).
 * 7896 contains 4 digits (even number of digits).
 * Therefore only 12 and 7896 contain an even number of digits.
 * Example 2:
 * * Input: nums = [555,901,482,1771]
 * Output: 1
 * Explanation:
 * Only 1771 contains an even number of digits.
 *
 * Constraints:
 * 1 <= nums.length <= 500
 * 1 <= nums[i] <= 105
 */
public class FindNumbersWithEvenNumberOfDigits {

    private static int findNumbers(int[] nums) {
        int count = 0, max = 0;
        for(int num : nums) { //outer loop
           while(num > 0){
                num = num / 10;  // num/ 10 => count
                count++;
            }
            if(count% 2 == 0)
                max++;
        }
        return max;
    }

    private static int findNumbersWithEvenNumberOfDigits(int[] nums) {
       int count = 0;
        for(int i = 0; i < nums.length; i++) {
             if((String.valueOf(nums[i]).length() % 2 ) == 0)
              count++;
        }
       return count;
    }

    private static int findNumbersWithEvenNumberOfDigitsStream(int[] nums) {
        return (int) IntStream.range(0, nums.length).filter(i -> (String.valueOf(nums[i]).length()) % 2 == 0).count();
    }

    public static void main(String[] args) {
        System.out.println(findNumbers(new int [] {12,345,2,6,7896})); // 2
        System.out.println(findNumbers(new int [] {555,901,482,1771})); // 1
        System.out.println(findNumbersWithEvenNumberOfDigits(new int [] {12,345,2,6,7896})); // 2
        System.out.println(findNumbersWithEvenNumberOfDigits(new int [] {555,901,482,1771})); // 1
        System.out.println(findNumbersWithEvenNumberOfDigitsStream(new int [] {12,345,2,6,7896})); // 2
        System.out.println(findNumbersWithEvenNumberOfDigitsStream(new int [] {555,901,482,1771})); // 1
    }
}
