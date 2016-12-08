package zoo.mb.hr;

import java.math.BigInteger;
import java.util.Scanner;

public class DivisibleNumbers {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        
        int m = find(n);
        
        //TODO, wouldn't a short cut be to use primes only?
        
        //System.out.println(m);
        System.out.println(m);
    }
    
    public static int find(int n) {
    	BigInteger bigN = BigInteger.valueOf(n);
    	for (int i = 1; ; i+=2) {
    		BigInteger m = BigInteger.valueOf(i).multiply(bigN);
    		if (nozeros(m) && ruletwo(m)) {
    			System.out.println("yes found " + m);
    			return m.toString().length();
    		}
    	}
    }
    
    public static int _find(int n) {
    	for (int i = 1; i <= (n*n*n); i++) {
    		if (i % n == 0) {
    			if (nozeros(new BigInteger(""+i)) && ruletwo(new BigInteger(""+i))) {
    				//System.out.println("yes found " + i);
    				return i;
    			}
    		}
    	}
    	return n;
    }
    
    public static boolean nozeros(BigInteger i) {
    	String s = "" + i;
    	return ! (s.contains("0"));
    }
    
    /** The sum of m's digits must be greater than or equal to the product of m's digits. */
    public static boolean ruletwo(BigInteger i) {
    	String s = "" + i;
    	int sum = 0;
    	int product = 1;
    	for (int j = 0; j < s.length(); j++) {
    		int val = Integer.parseInt(new String(""+s.charAt(j)));
			sum += val;
			product *= val;
			
			if (product > Integer.MAX_VALUE / 9) return false; // 36988988985 product : -2145058816
		}
    	if (sum >= product) {
        	System.out.println (i + " sum : " + sum);
        	System.out.println (i + " product : " + product);
    	}
    	return sum >= product;
    }
}
