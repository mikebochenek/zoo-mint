package zoo.mb.concurrency.chapter1;

/**
 * Java 7 Concurrency Cookbook
 * By: Javier Fernández;   
 * Print ISBN-13: 978-1-84968-788-1
 *
 * Creating and running a thread
 */
public class Calculator implements Runnable {
	private int number;

	public Calculator(int number) {
		this.number = number;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.printf("%s: %d * %d = %d\n", Thread.currentThread()
					.getName(), number, i, i * number);
		}
	}
}
