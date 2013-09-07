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
		for (int i = 1; i <= 10; i++) {
			Calculator calculator = new Calculator(i);
			Thread thread = new Thread(calculator);
			thread.start();
		}
	}
}