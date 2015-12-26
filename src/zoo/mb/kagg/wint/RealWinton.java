package zoo.mb.kagg.wint;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RealWinton {

	private static final int tcols = 119;

	public static void main(String[] args) {
		
		int threshold = 7;
		if (args.length > 0) {
			threshold = Integer.parseInt(args[0]);
		}
		System.out.println("starting with threshold=" + threshold);
		System.out.flush();
		
		long startTS = System.currentTimeMillis();
		
//		String testData = "outputtest1000.csv";
//		String trainData = "train1000.csv";
		String testData = "test.csv";
		String trainData = "train.csv";
		
		Data[] data = null;
		Data[] test = null;
		
		try {
			List<String> lines = Files.readAllLines(Paths.get("/home/mike/data/winton/" + trainData),
					Charset.forName("UTF-8"));
			data = new Data[lines.size()-1];
			for (int i = 1; i < lines.size(); i++) {
				String line = lines.get(i);
				data[i-1] = new Data(line);
				//System.out.println(new Data(line).toString());
			}
			
			lines = Files.readAllLines(Paths.get("/home/mike/data/winton/" + testData),
					Charset.forName("UTF-8"));
			test = new Data[lines.size()-1];
			for (int i = 1; i < lines.size(); i++) {
				String line = lines.get(i);
				test[i-1] = new Data(line);
				//System.out.println(new Data(line).toString());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println ("train data: " + data.length);
		System.out.println ("test data:  " + test.length);
		System.out.println ("1. reading and parsing done: " + (System.currentTimeMillis() - startTS) + "ms elapsed");

		
		startTS = System.currentTimeMillis();
		
		//ret2 to ret120
		ColumnValue[][] sorted = new ColumnValue[tcols][data.length];
		for (int j = 0; j < tcols; j++) {
			for (int i = 0; i < data.length; i++) {
				sorted[j][i] = new ColumnValue(data[i].ret[j], data[i]);
			}
			
			Arrays.sort(sorted[j]);
		}
		
		System.out.println ("2. prepared sorted column values: " + (System.currentTimeMillis() - startTS) + "ms elapsed");

		
		startTS = System.currentTimeMillis();
		
		for (int k = 0; k < /* 100 */ test.length; k++) {
			//if ( (k+1) % (test.length / 100) == 0) System.out.println ("600 done (1%)" + System.currentTimeMillis());

			int[] matchscore = new int[data.length];
			
			
			for (int i = 0; i < tcols; i++) {
				Double d = test[k].ret[i];
				if (d == null) d = Double.NaN;
				int j = 0;
				for ( ; j < sorted[i].length && d > sorted[i][j].value; j+=100) {
				}
				
				if (j >= 100) j -= 100;
				
				for ( ; j < sorted[i].length && d > sorted[i][j].value; j++) {
				}
				
				//System.out.println ("j=" + j + "  d=" + d + " with closest match " + sorted[i][j]);
				
				for (int s = (-1 * threshold); s < threshold; s++) {
					int idx = s + sorted[i][j].reverseLink.id;
					if (idx >= 0 && idx < matchscore.length) {
						matchscore[idx] += 1;
					}
				}
			}
			
			
			int highestscore = 0;
			int highestindex = 0;
			for (int i = 0; i < matchscore.length; i++) {
				if (highestscore < matchscore[i]) {
					highestscore = matchscore[i];
					highestindex = i;
				}
			}
			
			// System.out.println (highestscore + " " + highestindex);
			
			//TODO uncomment highestindex = (new Random()).nextInt(data.length);
			
			// "predict" -- copy over highest index data into test array
			test[k].ret_PlusOne = data[highestindex].ret_PlusOne;
			test[k].ret_PlusTwo = data[highestindex].ret_PlusTwo;
			for (int i = 119; i < test[k].ret.length; i++) {
				test[k].ret[i] = 0.0;//TODO data[highestindex].ret[i];
			}
		}
		
		System.out.println ("3. matching done " + (System.currentTimeMillis() - startTS) + "ms elapsed");

		
		startTS = System.currentTimeMillis();
		String outputfilename = "/home/mike/data/winton/tmp/submission-" + System.currentTimeMillis() + ".csv";

		try {
			PrintWriter writer = new PrintWriter(outputfilename, "UTF-8");
			writer.println("Id,Predicted");
			for (int i = 0; i < test.length; i++) {
				for (int j = 0; j < 60; j++) {
					writer.println((i+1) + "_" + (j+1) + "," + doubleToString(test[i].ret[119+j]));
				}
				writer.println((i+1) + "_" + (61) + "," + doubleToString(test[i].ret_PlusOne)); 
				writer.println((i+1) + "_" + (62) + "," + doubleToString(test[i].ret_PlusTwo));
			}
			writer.close();
		} catch (IOException io) {
			System.out.println(io);
		}
		
		System.out.println ("4. finished writing file " + outputfilename + "  " + (System.currentTimeMillis() - startTS) + "ms elapsed");

	}
	
	private static String doubleToString(Double d) {
		return d == null ? "0" : format.format(d);
	}
	
	
	private static Random random = new Random();
	private static DecimalFormat format = new DecimalFormat("#.############");
	
	public static String randomReturn(double sizer) {
		double r = 0;
		return format.format(r);
	}

}
