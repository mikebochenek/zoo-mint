package zoo.mb.hr;

import java.util.Scanner;

/**
 * Given an array, , of  integers and an array, , representing the respective weights 
 * of 's elements, calculate and print the weighted mean of 's elements. 
 * Your answer should be rounded to a scale of  decimal place (i.e.,  format).
 * 
 * Input Format
 * The first line contains an integer, , denoting the number of elements in arrays  and . 
 * The second line contains  space-separated integers describing the respective elements of array . 
 * The third line contains  space-separated integers describing the respective elements of array .
 * 
 * Sample Input
 * 5
 * 10 40 30 50 20
 * 1 2 3 4 5
 * 
 * Sample Output
 * 32.0
 */
public class Stats1WeightedMean {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		long a[] = new long[n];
		long w[] = new long[n];
		for (int i = 0; i < n; i++) {
			a[i] = in.nextLong();
		}
		for (int i = 0; i < n; i++) {
			w[i] = in.nextLong();
		}
		in.close();
		
		System.out.printf("%.1f\n", getMean(a, w));
	}
	
	public static double getMean(long[] a, long[] w) {
		long total = 0;
		for (int i = 0; i < a.length; i++) {
			total += a[i] * w[i];
		}
		long totalW = 0;
		for (long _w : w) {
			totalW += _w;
		}
		return total * 1.0 / totalW;
	}
}
