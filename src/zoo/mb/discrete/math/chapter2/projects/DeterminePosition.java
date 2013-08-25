package zoo.mb.discrete.math.chapter2.projects;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * page 164 of Discrete Mathematics and Its Applications by Kenneth H. Rosen
 * question #3. Given a list of n distinct integers, determine the position of an
 * integer in the list using a linear search.
 */
public class DeterminePosition {

	final int[] one = {3, 5, 7, 4};
	
	private int getPosition(int[] x, int search) {
		for (int i = 0; i < x.length; i++) {
			if (search == x[i]) return i;
		}
		return -1;
	}
	
	@Test
	public void test() {
		assertEquals(1, getPosition(one, 5));
		assertEquals(-1, getPosition(one,  2));
	}

}
