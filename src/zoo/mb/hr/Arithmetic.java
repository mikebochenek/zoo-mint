package zoo.mb.hr;

import java.util.Scanner;

public class Arithmetic {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = Integer.parseInt(in.nextLine());
		int s[] = new int[n];
		for (int i = 0; i < s.length; i++) {
			s[i] = (in.nextInt());
		}
		int pos = 0, neg = 0, zeros = 0;
		for (int i = 0; i < s.length; i++) {
			if (s[i] == 0) zeros++;
			if (s[i] < 0) neg++;
			if (s[i] > 0) pos++;
		}
		System.out.printf("%.6f\n", pos * 1.0 / s.length);
		System.out.printf("%.6f\n", neg * 1.0 / s.length);
		System.out.printf("%.6f\n", zeros * 1.0 / s.length);
	}
	
	public static void mainRoman(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = Integer.parseInt(in.nextLine());
		String s[] = new String[n];
		for (int i = 0; i < s.length; i++) {
			s[i] = (in.nextLine());
		}
		for (int i = 0; i < s.length; i++) {
			int v = deconvert(s[i]);
			String out = convert(v);
			System.out.println(out);
		}
		for (int i = 0; i < 1999; i++) {
			System.out.println (i + " -> " + convert(i));
		}
	}
	
	private static int deconvert(String s) {
		int total = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == 'I') total += 1;
			if (c == 'V') total += 5;
			if (c == 'X') total += 10;
			if (c == 'L') total += 50;
			if (c == 'C') total += 100;
			if (c == 'D') total += 500;
			if (c == 'M') total += 1000;
		}
		
		return total;
	}
	
	/**
	 * very primitive approach, but could we do better?
	 */
	private static String convert(int y) {
		String s = "";
		
		//assert (y < 3000);
		
		// if (y >= 1000) { - we realize this if-stmt is not needed because of for-loop
		for (int i = y / 1000; i > 0; i--) { s += "M"; y -= 1000; }
		
		if (y >= 900) { s += "CM"; y -= 900; }
		
		if (y >= 500) { s += "D"; y -= 500; }
		
		if (y >= 400) { s += "CD"; y -= 400; }
		
		for (int i = y / 100; i > 0; i--) { s += "C"; y -= 100; }
		
		if (y >= 90) { s += "XC"; y -= 90; }
		
		if (y >= 50) { s += "L"; y -= 50; }
		
		if (y >= 40) { s += "XL"; y -= 40; }
	
		for (int i = y / 10; i > 0; i--) { s += "X"; y -= 10; }
			
		if (y >= 9) { s += "IX"; y -= 9; }
		
		if (y >= 5) { s += "V"; y -= 5; }
		
		if (y >= 4) { s += "IV"; y -= 4; }
		
		for (int i = y; i > 0; i--) { s += "I"; }
		
		return s;
	}
	
	public static void main1(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		scan.close();
		String ans = "";

		// if 'n' is NOT evenly divisible by 2 (i.e.: n is odd)
		if (n % 2 == 1) {
			ans = "Weird";
		} else {
			if (n >= 6 && n <= 20) {
				ans = "Weird";
			} else {
				ans = "Not Weird";
			}
		}
		System.out.println(ans);
	}

	public static void main0(String[] args) {
		Scanner scan = new Scanner(System.in);
		double mealCost = scan.nextDouble(); // original meal price
		int tipPercent = scan.nextInt(); // tip percentage
		int taxPercent = scan.nextInt(); // tax percentage
		scan.close();

		// Write your calculation code here.
		double actualCost = mealCost + mealCost * (tipPercent / 100.0) + mealCost * (taxPercent / 100.0);

		// cast the result of the rounding operation to an int and save it as
		// totalCost
		int totalCost = (int) Math.round(actualCost);

		// Print your result
		System.out.println("The total meal cost is " + totalCost + " dollars.");
	}
}
