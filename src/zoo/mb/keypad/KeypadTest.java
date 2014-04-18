package zoo.mb.keypad;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

public class KeypadTest {

	@Test
	public void testGetNumber() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllCombinations() {
		Keypad pad = new Keypad();
		Set<String> all = pad.getAllCombinations(2553249);
		for (String s : all) {
			System.out.println(s);
		}
		
		System.out.println(all.size() + " in total.");
	}

}
