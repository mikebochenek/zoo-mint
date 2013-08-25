package zoo.mb.mint.kata;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * http://codingdojo.org/cgi-bin/wiki.pl?KataRomanNumerals
 * 
 * There is no need to be able to convert numbers larger than about 3000. 
 * (The Romans themselves didn't tend to go any higher)
 * 
 * Note that you can't write numerals like "IM" for 999. Wikipedia says: Modern
 * Roman numerals ... are written by expressing each digit separately starting
 * with the left most digit and skipping any digit with a value of zero. To see
 * this in practice, consider the ... example of 1990. In Roman numerals 1990 is
 * rendered: 1000=M, 900=CM, 90=XC; resulting in MCMXC. 2008 is written as
 * 2000=MM, 8=VIII; or MMVIII.
 * 
 * @author Mike Bochenek
 */
public class RomanNumerals {

	// none of these are used, and they are a bad idea because they make the code longer
	final String one = "I";
	final String five = "V";
	final String ten = "X";
	final String fifty = "L";
	final String hundred = "C";
	final String fiveHundred = "D";
	final String thousand = "M";
	

	/**
	 * very primitive approach, but could we do better?
	 */
	private String convert(int y) {
		String s = "";
		
		assert (y < 3000);
		
		// if (y >= 1000) { - we realize this if-stmt is not needed because of for-loop
		for (int i = y / 1000; i > 0; i--) { s += "M"; y -= 1000; }
		
		if (y >= 900) { s += "CM"; y -= 900; }
		
		if (y >= 500) { s += "D"; y =- 500; }
		
		if (y >= 400) { s += "CD"; y =- 400; }
		
		for (int i = y / 100; i > 0; i--) { s += "C"; y -= 100; }
		
		if (y >= 90) { s += "XC"; y -= 90; }
		
		if (y >= 50) { s += "L"; y -= 50; }
		
		if (y >= 40) { s += "XL"; y -= 40; }
	
		for (int i = y / 10; i > 0; i--) { s += "X"; y -= 10; }
			
		if (y >= 9) { s += "IX"; y -= 9; }
		
		if (y >= 5) { s += "V"; y -= 5; }
		
		for (int i = y; i > 0; i--) { s += "I"; }
		
		return s;
	}

	@Test
	public void test7() {
		assertEquals("VII", convert(7));
	}
	
	@Test
	public void test1990() {
		assertEquals("MCMXC", convert(1990));
	}
	
	/**
	 * more tests from http://www.novaroma.org/via_romana/numbers.html
	 */
	@Test
	public void testNovaRomaOrg() {
		assertEquals("MCCCXCII", convert(1392));
		assertEquals("MCMLXXIX", convert(1979));
	}
}
