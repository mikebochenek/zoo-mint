package zoo.mb.hr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import com.google.common.collect.Lists;

public class SherlockValidString {

	/* Find the Running Median */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = Integer.parseInt(in.nextLine());
		int a[] = new int[n];
		for (int i = 0; i < a.length; i++) {
			a[i] = Integer.parseInt(in.nextLine());
		}
		
		SortedSet<Integer> set = new TreeSet<Integer>();
		for (int i = 0; i < a.length; i++) {
			set.add(a[i]);
			//PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
			List<Integer> list = new ArrayList<Integer>(set);
			
			double median = 0.0f;
			if (i % 2 == 0) { // odd
				median = list.get(list.size() / 2);
			} else { // even
				median = list.get(list.size() / 2 - 1);
				median += list.get(list.size() / 2);
				median /= 2;
			}
			
			System.out.printf("%.1f\n", median);
		}
	}	

	/* Find the Running Median */
	public static void mainSlow(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = Integer.parseInt(in.nextLine());
		int a[] = new int[n];
		for (int i = 0; i < a.length; i++) {
			a[i] = Integer.parseInt(in.nextLine());
		}
		
		SortedSet<Integer> set = new TreeSet<Integer>();
		for (int i = 0; i < a.length; i++) {
			set.add(a[i]);
			Iterator<Integer> itr = set.iterator();
			
			double median = 0.0f;
			if (i % 2 == 0) { // odd
				for (int j = 0; j * 2 < (i + 1); j++) {
					median = itr.next();
				}
			} else { // even
				for (int j = 0; j * 2 < (i + 1); j++) {
					median = itr.next();
				}
				median += itr.next();
				median /= 2;
			}
			
			System.out.printf("%.1f\n", median);
		}
	}	
	
	/* Sherlock Valid String */
	public static void main0(String[] args) {
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		int count[] = new int[26];
		for (int i = 0; i < line.length(); i++) {
			int ascii = line.charAt(i) - 97;
			count[ascii]++;
		}
		
		int total = 0;
		int nonzero = 0;
		for (int i = 0; i < count.length; i++) {
//			System.out.println(count[i]);
			if (count[i] > 0) {
				total += count[i];
				nonzero++;
			}
		}
		
//		System.out.println("total:"+ total + " nonzero:" + nonzero);
//		System.out.println((total - 1) % nonzero == 0);
		
		if (total % nonzero == 0 || (total - 1) % nonzero == 0 || (total - 1) % (nonzero - 1) == 0) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}

	}

}
