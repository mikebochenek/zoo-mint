package zoo.mb.hr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class IsFibonacci {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		long a[] = new long[n];
		for (int i = 0; i < n; i++) {
			a[i] = in.nextLong();
		}
		
		for (int i = 0; i < a.length; i++) {
			System.out.println(isFiba(a[i]) ? "IsFibo" : "IsNotFibo");
		}
	}

	private static Map<Long,Long> cache = new HashMap<Long,Long>();

	public static boolean isFiba(long n) {
		for (int j = 0; n >= itFibN(j); j++) {
			long fib = itFibN(j);
			if (n == fib) {
				return true;
			}
		}
		return false;
	}

	public static long itFibN(long n) {
		if (cache.get(n) != null) return cache.get(n);
		else {
			long f = _itFibN(n);
			cache.put(n, f);
			return f;
		}
	}

	public static long _itFibN(long n) {
		if (n < 2)
			return n;
		long ans = 0;
		long n1 = 0;
		long n2 = 1;
		for (n--; n > 0; n--) {
			ans = n1 + n2;
			n1 = n2;
			n2 = ans;
		}
		return ans;
	}
	
	/** Hence, for xx to be a Fibonacci number, 5x2+45x2+4 or 5x2−45x2−4 must be a perfect square. */
	public static boolean isFib(int x) {
		Math.sqrt(5 * x * x + 4);
		return false;
	}
	
/**
06.12.2016 22:00:53

Points: 4.44 Rank: 87513

19
3028455434
9148500612
3292692109
3822190089
7866085129
8012912888
2667995244
7010162887
9205020319
4813968787
939418871
6013163775
7281005807
8514855781
6369128304
5768788913
6558985503
1753533532
7836005813

IsNotFibo
IsNotFibo
IsNotFibo
IsNotFibo
IsNotFibo
IsNotFibo
IsNotFibo
IsNotFibo
IsNotFibo
IsNotFibo
IsNotFibo
IsNotFibo
IsNotFibo
IsNotFibo
IsNotFibo
IsNotFibo
IsNotFibo
IsNotFibo
IsNotFibo


 */
}
