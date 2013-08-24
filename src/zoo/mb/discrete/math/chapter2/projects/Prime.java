package zoo.mb.discrete.math.chapter2.projects;

import static org.junit.Assert.*;

import org.junit.Test;

public class Prime {

	public boolean isPrime(long x) {
		for (int i = 1; i < x; i += 2) {
			if (x % i == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	@Test
	public void test4() {
		assertFalse(isPrime(4));
	}
	
	@Test
	public void test7() {
		assertTrue(isPrime(7));
	}

}
