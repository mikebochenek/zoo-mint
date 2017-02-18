package zoo.mb.hr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ArrayTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[]a = {1};
		ArrayTest test = new ArrayTest();
		System.out.println(a[a.length - 1]);
		
		int b = 0;
		int c = 4;
		try {
			int q = c / b;
		} catch (Exception e) {
			System.out.println("exceptiON");
		} finally {
			System.out.println("final");
		}
		
		System.out.println(maxDifference(new int[]{2,3,10,2,4,8,1}));
		System.out.println(maxDifference(new int[]{7,9,5,6,3,2}));
		System.out.println("");
		System.out.println(verifyItems(new String[]{"rice", "sugar", "wheat", "cheese"}, 
				new float[]{16.89f,56.92f,20.89f, 345.99f}, new String[]{"rice","cheese"}, 
				new float[]{18.99f,400.89f}));
		System.out.println(verifyItems(new String[]{"chocolate","cheese","tomato"}, 
				new float[]{15f,300.90f,23.44f}, new String[]{"chocolate","cheese","tomato"}, 
				new float[]{15f,300.90f,10f}));
	}
	void inc(int[] i) {
		i[i.length - 1]++;
	}
	
	class c {
		int v;
		int idx;
	}


	static int maxDifference(int[] a) {
    	Map<Integer,Integer> map = new HashMap<Integer,Integer>();
    	for (int i = 0; i < a.length; i++) {
			map.put(a[i], i);
		}
    	Collection<Integer> c = map.values();
    	Collections.sort(new ArrayList<Integer>(c), Collections.reverseOrder());
    	
    	for (Iterator iterator = c.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
			if (integer - )
		}
    	
    	int diff = -1;
    	return diff;
    }
    
    static int verifyItems(String[] origItems, float[] origPrices, String[] items, float[] prices) {
    	int incorrect = 0;
    	Map<String, Float> original = new HashMap<String, Float>();
    	for (int i = 0; i < origItems.length; i++) {
			original.put(origItems[i], origPrices[i]);
		}
    	
    	for (int i = 0; i < items.length; i++) {
			float originalPrice = original.get(items[i]);
			if (prices[i] != originalPrice) {
				incorrect++;
			}
		}
    	
    	return incorrect;
    }    
}
