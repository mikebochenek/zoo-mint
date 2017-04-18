package zoo.mb.mint.kata;

public class CodiliPractice {

	public static void main(String[] args) {
		CodiliPractice cp = new CodiliPractice();
		
		/* System.out.println(cp.permCheckSolution(new int[]{4, 1, 3, 2}));
		System.out.println(cp.permCheckSolution(new int[]{4, 1, 3}));
		System.out.println(cp.permCheckSolution(new int[]{4, 1, 1}));
		System.out.println(cp.permCheckSolution(new int[]{4}));
		System.out.println(cp.permCheckSolution(new int[]{4, 1, 3, 2, 5}));
		System.out.println(cp.permCheckSolution(new int[]{3, 1, 3, 3, 5})); 
		
		System.out.println(cp.missingIntegerSolution(new int[]{3, 1, 3, 3, 5}));
		System.out.println(cp.missingIntegerSolution(new int[]{1, 3, 6, 4, 1, 2}));
		System.out.println(cp.missingIntegerSolution(new int[]{-1, 13, 16, 40, 11, 15}));
		
		System.out.println(cp.binaryGapSolution(9));
		System.out.println(cp.binaryGapSolution(529));
		System.out.println(cp.binaryGapSolution(1041));
		System.out.println(cp.binaryGapSolution(7));
		System.out.println(cp.binaryGapSolution(20));
		System.out.println(cp.binaryGapSolution(15));*/

		System.out.println(cp.oddOccurrencesInArraySolution(new int[]{1, 3, 6, 4, 1, 2}));
		System.out.println(cp.oddOccurrencesInArraySolution(new int[]{9, 3, 9, 3, 9, 7, 9}));
	}
	
	/**
	 * OddOccurrencesInArray
	 * lessons/2-arrays/odd_occurrences_in_array/
	 * 
	 * A non-empty zero-indexed array A consisting of N integers is given. 
	 * The array contains an odd number of elements, and each element of the array can 
	 * be paired with another element that has the same value, except for one element that is left unpaired.
	 * 
	 * write a function that, given an array A consisting of N integers fulfilling the above 
	 * conditions, returns the value of the unpaired element.
	 * 
	 * Assume that:
	 *   N is an odd integer within the range [1..1,000,000];
	 *   each element of array A is an integer within the range [1..1,000,000,000];
	 *   all but one of the values in A occur an even number of times.
	 * Complexity:
	 *   expected worst-case time complexity is O(N);
	 *   expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
	 *   Elements of input arrays can be modified.
	 */
	public int oddOccurrencesInArraySolution(int[] A) {
		long sum = 0;
		for (int a : A) {
			sum += a;
		}
		int xor = 0;
		for (int a : A) {
			xor = xor ^ a;
		}
		return xor;
	}
	
	/**
	 * BinaryGap
	 * lessons/1-iterations/binary_gap/
	 * 
	 * A binary gap within a positive integer N is any maximal sequence of consecutive zeros 
	 * that is surrounded by ones at both ends in the binary representation of N.
	 * 
	 * For example, number 9 has binary representation 1001 and contains a binary gap of length 2. 
	 * The number 529 has binary representation 1000010001 and contains two binary gaps: one of 
	 * length 4 and one of length 3. The number 20 has binary representation 10100 and contains 
	 * one binary gap of length 1. The number 15 has binary representation 1111 and has no binary gaps.
	 * 
	 * Write a function that, given a positive integer N, returns the length of its longest binary gap. 
	 * The function should return 0 if N doesn't contain a binary gap.
	 * 
	 * For example, given N = 1041 the function should return 5, because N has binary representation 10000010001 
	 * and so its longest binary gap is of length 5.
	 */
	public int binaryGapSolution(int N) {
		String s = Integer.toBinaryString(N);
		int maxGap = 0, gap = 0;
		for (int i = 0; i < s.length(); i++) {
			if (('0' == s.charAt(i))) {
				gap++;
			} else {
				if (gap > maxGap) {
					maxGap = gap;
				}
				gap = 0;
			}
		}
		return maxGap;
	}
	
	/**
	 * lessons/4-counting_elements/missing_integer/
	 * that, given a non-empty zero-indexed array A of N integers, 
	 * returns the minimal positive integer (greater than 0) that does not occur in A.
	 */
	public int missingIntegerSolution(int[] A) {
		boolean[] found = new boolean[A.length];
		for (int a : A) {
			if (a > 0 && a < A.length) {
				found[a-1] = true;
			}
		}
		for (int i = 0; i < A.length; i++) {
			if (!found[i]) {
				return i+1;
			}
		}
		return 0;
	}

	/**
	 * lessons/4-counting_elements/perm_check/
	 * A non-empty zero-indexed array A consisting of N integers is given.
	 * A permutation is a sequence containing each element from 1 to N once, and only once.
	 * 
	 * Assume that:
	 * N is an integer within the range [1..100,000];
	 * each element of array A is an integer within the range [1..1,000,000,000].
	 * 
	 * Complexity:
	 * expected worst-case time complexity is O(N);
	 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	 * Elements of input arrays can be modified.
	 */
	public int permCheckSolution(int[] A) {
		if (A.length == 1) { return 1; }
		
		int expectedSum = sum(A.length);
		int sum = 0;
		boolean contains1 = false;
		boolean containsHighestNumber = false;
		boolean[] b = new boolean[A.length];
		
		for (int a : A) {
			sum += a;
			if (a <= 0 || a > A.length) {
				return 0;
			}
			if (a == 1) {
				contains1 = true;
			}
			if (a == A.length) {
				containsHighestNumber = true;
			}
			b[a-1] = true;
		}
		
		if (contains1 && containsHighestNumber && expectedSum == sum) {
			for (boolean _b : b) {
				if (!_b) { 
					return 0;
				}
			} 
			return 1;
		} else {
			return 0;
		}
	}
	
	/**
	 * https://www.varsitytutors.com/hotmath/hotmath_help/topics/sum-of-the-first-n-terms-of-a-series
	 */
	private int sum(int n) {
		return n * (1 + n) / 2;
	}
}
