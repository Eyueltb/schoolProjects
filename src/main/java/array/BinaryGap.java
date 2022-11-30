package array;


/**
 * A question from codility
 * A binary gap within a positive integer N is any maximal sequence of consecutive zeros
 * that is surrounded by ones at both ends in the binary representation of N.
 *
 * For example,
 * 9 => 1001 => answer = 2
 * 529 => 1000010001 => binary gap is 4 and 3 => max gap is 4
 * 20 => 10100 => binary gap is 1
 * 15 => 1111 => No binary gap
 * 32 => 100000 => No binary gap
 *
 * number 9 has binary representation 1001 and contains a binary gap of length 2.
 * The number 529 has binary representation 1000010001 and contains two binary gaps: one of length 4 and one of length 3.
 * The number 20 has binary representation 10100 and contains one binary gap of length 1.
 * The number 15 has binary representation 1111 and has no binary gaps.
 * The number 32 has binary representation 100000 and has no binary gaps.
 *
 * Write a function:
 *
 * class Solution { public int solution(int N); }
 *
 * that, given a positive integer N, returns the length of its longest binary gap.
 * The function should return 0 if N doesn't contain a binary gap.
 *
 * For example, given N = 1041 the function should return 5,
 * because N has binary representation 10000010001 and so its longest binary gap is of length 5.
 * Given N = 32 the function should return 0, because N has binary representation '100000' and thus no binary gaps.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [1..2,147,483,647].
 */
public class BinaryGap {

    public static int solution(int N) {
        String binaryString = Integer.toBinaryString(N);  //Number to binary String
        boolean started = false;
        int count = 0, max = 0;
        for(int i = 0; i < binaryString.length(); i++) {
            final char c = binaryString.charAt(i); //String c = binaryString.substring(i, i+1);
            if(c =='0')
                count ++;
            if(c == '1') {
                if(started) {
                   max = Math.max(max, count);
                }
             count = 0;
             started = true;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(solution(9)); //  9 => 1001 => answer = 2
        System.out.println(solution(529)); //  529 => 1000010001 => binary gap is 4 and 3 => max gap is 4
        System.out.println(solution(20)); //20 => 10100 => binary gap is 1
        System.out.println(solution(15)); // 15 => 1111 => 0
        System.out.println(solution(32)); // 32 => 100000 => 0
        System.out.println(solution(1041)); // 1041 => 10000010001 => 0
    }
}
