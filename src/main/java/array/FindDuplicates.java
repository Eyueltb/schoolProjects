package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Find duplicate -This question is from byte by byte
 * Question: Given an array of integers where each value 1 <= x <= len(array), write a
 * function that finds all the duplicates in the array.
 * duplicates([1, 2, 3]) = []
 * duplicates([1, 2, 2]) = [2]
 * duplicates([3, 3, 3]) = [3]
 * duplicates([2, 1, 2, 1]) = [1, 2]
 */
public class FindDuplicates {
    private static int[] duplicates(int [] arr) {
        Set<Integer> unique = new HashSet<>(); // to keep track of unique
        Set<Integer>  duplicate = new HashSet<>();  // to keep track of duplicate

        for(int num : arr) {
            if(!unique.contains(num))  //contains()->O(1)
                unique.add(num);
            else
                duplicate.add(num);
        }
        //Arrays.copyOf(duplicate.toArray(), duplicate.size(), Integer[].class)-> copy Set<Integer> to Integer[]
        //int[] intArray = Arrays.stream(array).mapToInt(Integer::intValue).toArray(); -> Integer [] -> int[]
        return Arrays.stream(Arrays.copyOf(duplicate.toArray(), duplicate.size(), Integer[].class)).mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        Arrays.stream(duplicates(new int [] {1, 2, 3})).forEach(System.out::print); //[]
        Arrays.stream(duplicates(new int [] {1, 2, 2})).forEach(System.out::print); //[2]
        System.out.println();
        Arrays.stream(duplicates(new int [] {3, 3, 3})).forEach(System.out::print); //[3]
        System.out.println();
        Arrays.stream(duplicates(new int [] {2, 1, 2, 1})).forEach(System.out::print); //[1, 2]
    }
}
