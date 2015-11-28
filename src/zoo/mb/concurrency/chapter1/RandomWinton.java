package zoo.mb.concurrency.chapter1;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Random;

public class RandomWinton {

	public static void main(String[] args) {
		try {
			PrintWriter writer = new PrintWriter("/tmp/submission-" + System.currentTimeMillis() + ".csv", "UTF-8");
			writer.println("Id,Predicted");
			for (int i = 0; i < 60000; i++) {
				for (int j = 0; j < 62; j++) {
					writer.println((i+1) + "_" + (j+1) + "," + randomReturn());
				}
			}
			writer.close();
		} catch (IOException io) {
			System.out.println(io);
		}
	}
	
	private static Random random = new Random();
	private static DecimalFormat format = new DecimalFormat("#.######");
	
	public static String randomReturn() {
		double r = random.nextDouble();
		r -= 0.5;
		r *= 0.004;
		return format.format(r);
		//return "0";
	}

}
