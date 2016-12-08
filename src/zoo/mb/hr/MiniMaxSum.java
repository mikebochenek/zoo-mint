package zoo.mb.hr;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MiniMaxSum {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        
        int words = s.length() > 0 ? 1 : 0;
        for (int i = 0; i < s.length(); i++) {
			if (Character.isUpperCase(s.charAt(i))) {
				words ++;
			}
		}
        System.out.println(words);
    }
	
    public static void main0(String[] args) {
        Scanner in = new Scanner(System.in);
        long a = in.nextLong();
        long b = in.nextLong();
        long c = in.nextLong();
        long d = in.nextLong();
        long e = in.nextLong();
        
        long[] sums = { a + b + c + d, a + b + c + e, a + b + d + e, a + c + d + e, b + c + d + e};
        Arrays.sort(sums);
        
        System.out.println(sums[0] + " " + sums[4]);
    }
}

