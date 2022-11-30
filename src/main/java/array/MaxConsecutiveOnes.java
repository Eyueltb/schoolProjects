package array;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Given a binary array nums, return the maximum number of consecutive 1's in the array.
 * Input -> nums = [1,1,0,1,1,1], outPut = 3
 * Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
 *  Input -> nums = [1,0,1,1,0,1], outPut = 2
 *  Constraints: 1 <= nums.length <= 105 ,  nums[i] is either 0 or 1.
 *
 *  Similar Questions
 *      Max Consecutive Ones II  Medium
 *      Max Consecutive Ones III  Medium
 *      Consecutive Characters   Easy
 *      Longer Contiguous Segments of Ones than Zeros  Easy
 *      Length of the Longest Alphabetical Continuous Substring  Medium
 */
public class MaxConsecutiveOnes {
    public static int findMaxConsecutiveOnes(int[] nums) {
        int count = 0, max = 0;
        for(int num : nums) {
            if(num == 1)
                count++;
            else
                count = 0;

            max = Math.max(count, max);
        }
        return max;
    }

    public static int findMaxConsecutiveOnesStream(int[] nums) {
        AtomicInteger count = new AtomicInteger(); //Effectively final
        AtomicInteger max = new AtomicInteger();
        Arrays.stream(nums).forEach(num-> {
            if(num == 1) {
                count.getAndIncrement();
                max.set(Math.max(count.get(), max.get())) ;
            }else{
                count.set(0);
            }
        });
        return max.get();
    }

    public static void main(String[] args) {
        System.out.println(findMaxConsecutiveOnes(new int[] {1,1,0,1,1,1}));
        System.out.println(findMaxConsecutiveOnes(new int[] {1,0,1,1,0,1}));
    }
}
