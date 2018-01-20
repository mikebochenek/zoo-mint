package zoo.mb.hr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Calculate statistical mean, mode, and median
 * 
 * Sample Input
 * 10
 * 64630 11735 14216 99233 14470 4978 73429 38120 51135 67060
 * 
 * Sample Output
 * 43900.6
 * 44627.5
 * 4978
 */
public class Stats1MeanMode {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		long a[] = new long[n];
		for (int i = 0; i < n; i++) {
			a[i] = in.nextLong();
		}
		
		System.out.printf("%.1f\n", getMean(a));
		System.out.printf("%.1f\n", getMedian(a));
		System.out.println(getMode(a));
	}
	
	public static double getMean(long[] a) {
		long total = 0;
		for (long num : a) {
			total += num;
		}
		return total * 1.0 / a.length;
	}

	public static double getMedian(long[] a) {
		Arrays.sort(a);
		if (a.length % 2 != 0) { // even
			return a[a.length / 2];
		} else { // odd
			return (a[a.length / 2] + a[a.length / 2 - 1]) / 2.0;
		}
	}
	
	public static Long getMode(long[] a) {
		Map<Long, Long> map = new HashMap<Long, Long>();
		for (long key : a) {
			if (map.containsKey(key)) {
				map.put(key, map.get(key) + 1);
			} else {
				map.put(key, 1L);
			}
		}
		
		List<Long> keyList = new ArrayList<Long>(map.keySet());
		Collections.sort(keyList);
		
		long max = 0;
		long maxKey = 0;
		for (Long key : keyList) {
			if (map.get(key) > max) {
				max = map.get(key);
				maxKey = key;
			}
		}
		return maxKey;
	}
}
