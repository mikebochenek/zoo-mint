package zoo.mb.hr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Staircase {

	public static Integer[] list;
	public static int total = 0;
	public static Set<String> answers = new HashSet<String>();
	/* challenges/coin-change */
	/* challenges/coin-change */
	public static void main(String[] args) throws NumberFormatException, IOException {

		Scanner in = new Scanner(System.in);

		int N, M;
		N = in.nextInt();
		M = in.nextInt();

		list = new Integer[M];
		for (int i = 0; i < M; i++) {
			list[i] = in.nextInt();
		}

		Arrays.sort(list, Collections.reverseOrder());
		for (int i = 0; i < list.length; i++) {
			//System.out.println(list[i]);
			check("", N, i);
		}
		System.out.println(total);
		//System.out.println(answers.size());
	}
	public static void checkF(String s, int n, int i) {

		int ci = list[i];
		s += " " + ci;
		if (ci - n == 0) {
			System.out.println(s + " YES ");
			total++;
		}
		if (n - ci < 0) {
			return;
		}
		if (n - ci > 0) {
			for (int j = i; j < list.length; j++) {
				check(s, n - ci, j);
			}
		}
	}
	
	public static void mainworksSlowly(String[] args) throws NumberFormatException, IOException {

		Scanner in = new Scanner(System.in);

		int N, M;
		N = in.nextInt();
		M = in.nextInt();

		list = new Integer[M];
		for (int i = 0; i < M; i++) {
			list[i] = in.nextInt();
		}

		Arrays.sort(list, Collections.reverseOrder());
		for (int i = 0; i < list.length; i++) {
			//System.out.println(list[i]);
			check("", N, i);
		}
		System.out.println(total);
		//System.out.println(answers.size());
	}
	
	public static void check(String s, int n, int i) {
		//System.out.print(ci);
		int ci = list[i];
		s += " " + ci;
		//System.out.println ("n:" + n + " ci:" + ci);
		if (ci - n == 0) {
			System.out.println (s + " YES ");
/*			String[] w = s.split(" ");
			Arrays.sort(w);
			String answer = "";
			for (int j = 0; j < w.length; j++) {
				answer += " " + w[j];
			}
			answers.add(answer);
			*/
			//answers.add(s);
			total++;
		}
		if (n - ci < 0) {
			//System.out.println (s + " NO ");
			return;
		}
		if (n - ci > 0) {
			for (int j = i; j < list.length; j++) {
				check(s, n-ci, j);
			}
		}
	}
	
	public static void main3(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int K = Integer.parseInt(in.readLine());
		int[] list = new int[N];

		for (int i = 0; i < N; i++)
			list[i] = Integer.parseInt(in.readLine());

		int unfairness = Integer.MAX_VALUE;

		Arrays.sort(list);
		for (int i = 0; i < list.length - K + 1; i++) {
			//System.out.println("i: " + i + " new: " + (list[i+K-1] - list[i]) + " unfairness: " + unfairness);

			if (list[i+K-1] - list[i] < unfairness) {
				unfairness = list[i+K-1] - list[i];
			}
		}

		System.out.println(unfairness);
	}

	public static void main2(String args[]) {
		Scanner in = new Scanner(System.in);

		int N, K;
		N = in.nextInt();
		K = in.nextInt();

		int C[] = new int[N];
		for (int i = 0; i < N; i++) {
			C[i] = in.nextInt();
		}

		Arrays.sort(C);
		int result = 0;

		for (int i = C.length - 1; i >= 0; i--) {
			System.out.println(" - " + (1 + (C.length - i - 1) / K));
			System.out.println(C[i]);
			result += (1 + (C.length - i - 1) / K) * C[i];
		}

		System.out.println(result);

	}

	public static void main0(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		for (int i = 0; i < n; i++) {
			String s = "";
			for (int j = 0; j < (n - i - 1); j++) {
				s += " ";
			}
			for (int j = (n - i - 1); j < n; j++) {
				s += "#";
			}
			System.out.println(s);
		}

	}

}
