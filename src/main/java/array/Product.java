package array;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Daily Coding Problem #2
 * This problem was asked by Uber.
 *
 * Given an array of integers, return a new array such that each element at index i of
 * the new array is the product of all the numbers in the original array except the one at i.
 *
 * For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24].
 * If our input was [3, 2, 1], the expected output would be [2, 3, 6].
 *
 * Follow-up: what if you can't use division?
 */
public class Product {

    private static int [] getProduct(int [] A) {
        int prod = IntStream.of(A).reduce(1,(x,y)->x*y);
        IntStream.range(0, A.length).forEach(i-> {
            A[i] = prod / A[i];
        });
       return A;
    }

    public static void main(String[] args) {
        Arrays.stream(getProduct(new int [] {1, 2, 3, 4, 5})).forEach(i-> System.out.print(" "+i)); //[120, 60, 40, 30, 24]
        System.out.println();
        Arrays.stream(getProduct(new int [] {3, 2, 1})).forEach(i-> System.out.print(" "+i)); //[2, 3, 6]

    }

}
