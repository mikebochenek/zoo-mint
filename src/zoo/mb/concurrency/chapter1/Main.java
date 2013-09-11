package zoo.mb.concurrency.chapter1;

/**
 * Java 7 Concurrency Cookbook
 * By: Javier Fernández;   
 * Print ISBN-13: 978-1-84968-788-1
 *
 * Creating and running a thread
 */
public class Main {
	public static void main(String[] args) {
		long start = System.nanoTime();
		for (int i = 1; i <= 150; i++) {
			Calculator calculator = new Calculator(i);
			Thread thread = new Thread(calculator);
			thread.start();
		}
		
		// A nanosecond (ns) is an SI unit of time equal to one billionth of a second (10−9 or 1/1,000,000,000 s). 
		System.out.println("\n\n   nano elapsed: " + (System.nanoTime() - start));
		System.out.println("\n\n   nano elapsed: " + ((System.nanoTime() - start) / 1000000L));
	}
}