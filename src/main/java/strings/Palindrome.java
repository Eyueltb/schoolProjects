package strings;
/**
 *  A string is a palindrome if it reads the same forwards as backwards.
 *  That means, after reversing it, it is still the same string.
 *  For example: "abcdcba", or "racecar" or "" are Palindrome
 *  "abc" are not palindrome
 */
public class Palindrome {
    //Time complexity = O(N),
    private static boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        while(left < right) {
            if(str.charAt(left) != str.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }
    private static boolean isPalindromeStringBuilder(String str) {
        return new StringBuilder(str).reverse().toString().equals(str);
    }
}
