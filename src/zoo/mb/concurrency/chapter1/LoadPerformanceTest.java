package zoo.mb.concurrency.chapter1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class LoadPerformanceTest implements Runnable {
	public final static int THREADS = 10;
	
	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) {
			System.out.println("test #" + i + " (threads=" + THREADS + ")");
			test();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static void test() {
		for (int i = 1; i <= THREADS; i++) {
			LoadPerformanceTest test = new LoadPerformanceTest(i);
			Thread thread = new Thread(test);
			thread.start();
		}
	}
	
	private int number;

	public LoadPerformanceTest(int number) {
		this.number = number;
	}

	@Override
	public void run() {
		long start = System.nanoTime(); // A nanosecond (ns) is an SI unit of time equal to one billionth of a second (10−9 or 1/1,000,000,000 s).
		curl("http://localhost:9000/api/recommend?id=1&longitude=47.3921582785799&latitude=8.512902108369&minPrice=0&maxPrice=150&maxDistance=20.0&openNow=false&maxDishes=15");
		curl("http://localhost:9000/api/restdishes/17,1");
		curl("http://localhost:9000/api/getlikes/36");
		curl("http://localhost:9000/api/settings/1");
		
		long end = System.nanoTime();
		System.out.println("miliseconds elapsed: " + (end - start) / 1000000.0);
		//for (int i = 1; i <= 10; i++) {
			//System.out.printf("%s: %d * %d = %d\n", Thread.currentThread().getName(), number, i, i * number);
		//}
	}

	private void curl(String url) {
		try {
			URL yahoo = new URL(url);
			URLConnection yc = yahoo.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
			}
			in.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}