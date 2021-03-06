package zoo.mb.concurrency.chapter1;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

public class RandomWinton {

	public static void main(String[] args) {
		long startTS = System.currentTimeMillis();
		
		try {
			List<String> lines = Files.readAllLines(Paths.get("/home/mike/data/winton/train1000.csv"), Charset.forName("UTF-8"));
			for(String line:lines){
				  System.out.println(line);
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			PrintWriter writer = new PrintWriter("/tmp/submission-" + System.currentTimeMillis() + ".csv", "UTF-8");
			writer.println("Id,Predicted");
			for (int i = 0; i < 60000; i++) {
				for (int j = 0; j < 62; j++) {
					writer.println((i+1) + "_" + (j+1) + "," + randomReturn(0.000000004));
				}
				//writer.println((i+1) + "_" + (61) + "," + randomReturn(0.004)); somehow this made things worse actually....
				//writer.println((i+1) + "_" + (62) + "," + randomReturn(0.004));
			}
			writer.close();
		} catch (IOException io) {
			System.out.println(io);
		}
		
		System.out.println (System.currentTimeMillis() - startTS + "ms elapsed");
	}
	
	private static Random random = new Random();
	private static DecimalFormat format = new DecimalFormat("#.###########");
	
	public static String randomReturn(double sizer) {
		double r = random.nextDouble();
		r -= 0.5;
		r *= sizer;
		return format.format(r);
		//return "0";
	}

}
