package zoo.mb.kagg.stolens;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * For this competition, you are asked to optimize the total weighted distance
 * traveled (weighted reindeer weariness). You are given a list of gifts with
 * their destinations and their weights. You will plan sleigh trips to deliver
 * all the gifts to their destinations while optimizing the routes.
 * 
 * --> All sleighs start from north pole, then head to each gift in the order
 * that you assign, and then head back to north pole. - You may have an
 * unlimited number of sleigh trips.
 * 
 * --> All the gifts must be traveling with the sleigh at all times until the
 * sleigh delivers it to the destination. A gift may not be dropped off anywhere
 * before it's delivered.
 * 
 * --> Sleighs have a base weight of 10, and a maximum weight capacity of 1000
 * (excluding the sleigh).
 * 
 * --> All trips are flying trips, which means you don't need to travel via
 * land. Haversine is used in calculating distance.
 * 
 * https://en.wikipedia.org/wiki/Haversine_formula
 * 
 * 
 * 
 * Evaluation Your goal is to minimize total weighted reindeer weariness:
 * 
 * --> Weighted Reindeer Weariness = (distance traveled) * (weights carried for
 * that segment)
 * 
 * --> All sleighs start from north pole (Lat=90, Long=0), then head to each
 * gift in the order that a user gives, and then head back to north pole
 * 
 * --> Sleighs have a base weight = 10 - Each sleigh has a weight limit = 1000
 * (excluding the sleigh base weight)
 * 
 * For example, if you have 2 gifts A, B to deliver in the trip, then the WRW is
 * calculated as:
 * 
 * --> dist( North pole to A ) * ( weight A + weight B + base_weight )
 * 
 * --> + dist( A to B ) * ( weight B + base_weight )
 * 
 * --> + dist( B to North pole ) * ( base_weight )
 */
public class RealStolen {
	private GiftData[] data;
	private final double northpole_lat = 90.0;
	private final double northpole_lon = 0.0; //north pole (Lat=90, Long=0),
	private GiftData northPole = new GiftData("0,90.0,0,0");

	private int partitionSize = 100;
	private String homedirectory = "/home/mike/data/santa/";
	
	public static void main(String[] args) {
		RealStolen rs = new RealStolen();

		if (args.length > 0) {
			rs.homedirectory = args[0];
		}
		if (args.length > 1) {
			rs.partitionSize = Integer.parseInt(args[1]);
		}
		
		System.out.println ("starting with dir=" + rs.homedirectory + "  pSize=" + rs.partitionSize);
		
		rs.run();
	}

	public void run() {
		try {
			readInput("gifts.csv");

			// System.out.println(Haversine.haversine(36.12, -86.67, 33.94,
			// -118.40));

			// sampleProcess();
			
			partition();
			
			realProcess();

			writeOutput();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private HashMap<Integer, List<GiftData>> partition = new HashMap<Integer, List<GiftData>>();
	
	private void partition() {
		long startTS = System.currentTimeMillis();
		
		for (int i = 0; i < partitionSize; i++) {
			Random r = new Random();
			int key = r.nextInt(data.length);
			List<GiftData> list = new ArrayList<GiftData>();
			partition.put(key, list);
		}
		
		for (GiftData d : data) {
			double minDistance = Double.MAX_VALUE;
			Integer minPartitionIdx = 0;
			
			for (Integer key : partition.keySet()) {
				double newDistance = Haversine.haversine(data[key].latitude, data[key].longitude, d.latitude, d.longitude);
				if (minDistance > newDistance) {
					minDistance = newDistance;
					minPartitionIdx = key;
				}
			}
			
			partition.get(minPartitionIdx).add(d);
		}

		
		int assigned = 0;
		for (Integer key : partition.keySet()) {
			//System.out.println("key=" + key + "  size:" + partition.get(key).size());
			assigned += partition.get(key).size();
		}
		

		System.out.println("assigned: " + assigned);
		System.out.println("2. partition done: " + (System.currentTimeMillis() - startTS) + "ms elapsed");
	}
	
	private void realProcess() {
		long startTS = System.currentTimeMillis();

		int tripId = 1;
		
		for (Integer key : partition.keySet()) {
			System.out.println("... starting partition with key=" + key + "  size:" + partition.get(key).size());
			
			List<GiftData> localPartition = partition.get(key);
			
			boolean allPopulated = false;
			do {
				int countNoTripId = 0;
				for (GiftData d : localPartition) {
					if (d.tripID == 0) {
						countNoTripId++;
						//allPopulated = false;
					}
				}
				if (countNoTripId > 0) {
					allPopulated = false;
				}
				if (countNoTripId == 0) {
					allPopulated = true;
				}

				double currentW = 0.0;
				GiftData start = northPole;
				
				while (currentW < 1000) { //TODO this is wrong..

					double minDistanceCost = Double.MAX_VALUE;
					GiftData nextStop = null;

					// greedy - find current best
					for (GiftData d : localPartition) {
						if (d.tripID == 0 && (currentW + d.weight < 1000)) {
							double newDistance = Haversine.haversine(start.latitude, start.longitude, d.latitude, d.longitude);
							if (minDistanceCost > (newDistance * d.weight) && (currentW + d.weight < 1000)) {
								minDistanceCost = (newDistance * d.weight);
								nextStop = d;
							}
						}
					}
					
					if (nextStop != null) {
						currentW += nextStop.weight;
						nextStop.tripID = tripId;
						start = nextStop;
						//System.out.println(currentW + "   next stop: " + nextStop);
					} else {
						break;
					}
				}
				
				tripId++;
				System.out.println ("allpopulated: " + allPopulated + " tripid: " + tripId);

				
			} while (!allPopulated);
			
		}

		
		/*
		GiftData[] sortedData = Arrays.copyOf(data, data.length);
		Arrays.sort(sortedData);
		
		GiftData[][] buckets = new GiftData[36][10000];

		for (GiftData gd : data) {
			int bucketIdx = new Double(gd.longitude / 10.0).intValue() + 18;
			System.out.println(bucketIdx);
		}
		
		for (int i = 0; i < 36; i++) {
			
		}*/
		
		System.out.println("tripID="+ tripId);
		
		System.out.println("3. processing done: " + (System.currentTimeMillis() - startTS) + "ms elapsed");
	}

	private void readInput(String filename) throws IOException {
		long startTS = System.currentTimeMillis();
		List<String> lines = Files.readAllLines(Paths.get(homedirectory + filename),
				Charset.forName("UTF-8"));
		data = new GiftData[lines.size() - 1];
		for (int i = 1; i < lines.size(); i++) {
			String line = lines.get(i);
			data[i - 1] = new GiftData(line);
		}
		System.out.println("1. reading and parsing done: " + (System.currentTimeMillis() - startTS) + "ms elapsed");
	}

	private void writeOutput() throws IOException {
		long startTS = System.currentTimeMillis();
		String outputfilename = homedirectory + "tmp/submission-" + System.currentTimeMillis() + ".csv";

		PrintWriter writer = new PrintWriter(outputfilename, "UTF-8");
		writer.println("GiftId,TripId");
		for (GiftData d : data) {
			writer.println(d.toLine());
		}

		writer.close();

		System.out.println("4. finished writing file " + outputfilename + "  " + (System.currentTimeMillis() - startTS)
				+ "ms elapsed");
	}

	/**
	 * baseline.. [ Fri Dec 11 16:59:16 ~/data/santa/tmp ] diff
	 * submission-1449849549297.csv ../sample_submission.csv
	 */
	private void sampleProcess() {
		for (int i = 0; i < data.length; i++) {
			data[i].setTripID(i % 5000);
		}
	}
}
