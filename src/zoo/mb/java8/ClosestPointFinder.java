package zoo.mb.java8;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClosestPointFinder {

    public static void main(String[] args) {
    	if (args.length < 1) {
    		System.err.println("please provide filename");
    	} else {
            new ClosestPointFinder().run(args[0]);
    	}
    }
    
    // https://en.wikipedia.org/wiki/K-d_tree
    // https://rosettacode.org/wiki/K-d_tree#J
    class KDTree {
    	short axis;
    	Point location;
    	KDTree leftChild;
    	KDTree rightChild;
    	
    	public KDTree(List<Point> list, short depth) {
    		axis = (short) (depth % 2);
    		
    		// find median
    		// list.sort(_by X);
    		
    		int medianIdx = list.size() / 2; // etc.
    		
    		// recursively create KDTree with two sublists
    		// right away think of ending recursion (if size = 1 we stop) 
    		location = null;
//    		leftChild = new KDTree(list.subList(0, medianIdx), (short) depth+1);
//    		rightChild = new KDTree(list.subList(medianIdx, list.size()-1), (short) depth+1);
    	}
    }

    class Point {
        public Point(short _x, short _y) {
            x = _x;
            y = _y;
        }
        short x;
        short y;
        double d; // NB: distance squared, not distance
    }

    public void run(String filename) {
		List<Point> list = readFile(filename);

        findClosest(list, new Point((short)-200, (short)300), 10, true);
        findClosest(list, new Point((short)1000, (short)25), 20, false);
    }

	private List<Point> readFile(String filename) {
		List<Point> list = new ArrayList<Point>();
		long startTS = System.currentTimeMillis();

        Path path = Paths.get(filename);
        try {
			byte[] bytes =  Files.readAllBytes(path);
			assert(bytes.length % 4 == 0);
	        list = new ArrayList<>(bytes.length / 4);
			
			for (int i = 0; i < bytes.length; i += 4) {
				short x = ByteBuffer.wrap( new byte[]{bytes[i], bytes[i+1]} ).order(ByteOrder.BIG_ENDIAN).getShort();
				short y = ByteBuffer.wrap( new byte[]{bytes[i+2], bytes[i+3]} ).order(ByteOrder.BIG_ENDIAN).getShort();
				list.add(new Point(x,y));
			}
			
			//for (int i = 0; i < 3; i++) { System.out.println(list.get(i).x + "," + list.get(i).y); }
			
	        System.out.println ("read file " + filename + " into memory " + bytes.length + " bytes");
	        System.out.println ("milliseconds expired: " + (System.currentTimeMillis() - startTS) + "\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
        return list;
	}

	/**
	 * Find the first closest points to target in a list
	 * @param list huge random list of points
	 * @param target target Point used to calculate distance
	 * @param first number of results to be printed
	 * @param closest if true, find closest.  if false, find points furthest away!
	 */
	private void findClosest(List<Point> list, Point target, int first, boolean closest) {
		long startTS = System.currentTimeMillis();

		// optimization starts
        List<Double> sampleDistances = new ArrayList<Double>();
        int sampleSizeFactor = Math.max(new Random().nextInt(100) * 13, first);
        
        for (int i = 0; i < list.size(); i += sampleSizeFactor) {
        	Point p = list.get(i);
            long xd = target.x - p.x;
            long yd = target.y - p.y;
            double d = (xd * xd) + (yd * yd);
            sampleDistances.add(d);
        }
        
        Collections.sort(sampleDistances);
        final double min = sampleDistances.get(first);
        final double max = sampleDistances.get((sampleDistances.size() - 1) - first);
        // System.out.println("max: " + max + " min: " + min);
        // optimization ends (min/max are used in intermediate.filter below)

        Stream<Point> intermediate = list.stream().parallel().map(p -> {
            long xd = target.x - p.x;
            long yd = target.y - p.y;
            p.d = (xd * xd) + (yd * yd); // this violates stream immutability principles
            return p;
        });

        List<Point> result = intermediate
        		.filter(p -> closest ? p.d < min : p.d > max) // NB: we throw away irrelevant points
        		.sorted(Comparator.comparingDouble(t -> t.d)).collect(Collectors.toList());
        
        if (!closest) {
        	Collections.reverse(result);
        }
        
        //System.out.println("--> " + result.size()); // this shows how effective our optimization is
        
        for (int i = 0; i < first; i++) {
        	System.out.println(result.get(i).x + "," + result.get(i).y);// +  " -> " + result.get(i).d);
        }
        
        System.out.println ("milliseconds expired: " + (System.currentTimeMillis() - startTS) + "\n");
	}
} 

