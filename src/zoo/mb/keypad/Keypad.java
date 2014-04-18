package zoo.mb.keypad;

import java.util.HashSet;
import java.util.Set;

/**
 * http://www.dialabc.com/words/history.html
 * 
 * 	2 ABC     6 MNO   
 * 	3 DEF     7 PQRS   
 *  4 GHI     8 TUV   
 *  5 JKL     9 WXYZ   
 */
public class Keypad {
	
	final char[][] pad = { {'0'}, // 0
			{'1'}, // 1
			{'A','B','C'}, // 2
			{'D','E','F'}, // 3
			{'G','H','I'}, // 4
			{'J','K','L'}, // 5
			{'M','N','O'}, // 6
			{'P','Q','R','S'}, // 7
			{'T','U','V'}, // 8
			{'W','X','Y','Z'} }; // 9

	public long getNumber(String word) {
		return -1;
	}
	
	public Set<String> getAllCombinations(long num) {
		Set<String> all = new HashSet<String>();
		
		//assert num > 100; // what about numbers starting with zero?
		
		String numStr = "" + num;
		
		char[] result = numStr.toCharArray(); //new char[numStr.length()];
		
		for (int i = 0; i < numStr.length(); i++) {
			int idx = (int)numStr.charAt(i);
			idx = idx - 48; //TODO some asserts or checks
			
			for (int j = 0; j < pad[idx].length; j++) {
				result[i] = pad[idx][j];
				all.add(new String(result));
			}
		}
		
		return all;
	}
	
}
