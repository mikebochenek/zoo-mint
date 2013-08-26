package zoo.mb.topcoder;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Little Fox Jiro is standing at the bottom of a long flight of stairs. The
 * bottom of the stairs has number 0, the bottommost step has number 1, the next
 * step has number 2, and so on. The staircase is so long that Jiro is
 * guaranteed not to reach its top.
 * 
 * Jiro will now perform N consecutive actions. The actions are numbered 1
 * through N, in order. When performing action X, Jiro chooses between two
 * options: either he does nothing, or he jumps exactly X steps up the stairs.
 * In other words, if Jiro starts performing action X standing on step Y, he
 * will end it either on step Y, or on step Y+X.
 * 
 * For example, if N=3, Jiro will make three consecutive choices: whether or not
 * to jump 1 step upwards, 2 steps upwards, and then 3 steps upwards.
 * 
 * One of the steps is broken. The number of this step is badStep. Jiro cannot
 * jump onto this step.
 * 
 * You are given the ints N and badStep. Compute and return the number of the
 * topmost step that can be reached by Jiro.
 * 
 * Definition
 * Class:	JumpFurther
 * Method:	furthest
 * Parameters:	int, int
 * Returns:	int
 * Method signature:	int furthest(int N, int badStep)
 * (be sure your method is public)
 * 
 * Constraints
 * -	N will be between 1 and 2,000, inclusive.
 * -	badStep will be between 1 and 4,000,000, inclusive. 
 *
 * http://community.topcoder.com/stat?c=problem_statement&pm=12300&rd=15699
 */
public class JumpFurther {

	public int furthest(int N, int badStep) {
		return 1;
	}
	
	@Test
	public void test0() {
		assertEquals(3, furthest(2, 2));
		// The optimal strategy is to jump upwards twice: from step 0 to step 1, 
		// and then from step 1 to step 3. This trajectory avoids the broken step.
	}

	@Test 
	public void test1() {
		assertEquals(2, furthest(2, 1));
		// In this case step 1 is broken, so Jiro cannot jump upwards as his 
		// first action. The optimal strategy is to first stay on step 0, 
		// and then to jump from step 0 to step 2.
	}
	
	@Test 
	public void test2() {
		assertEquals(5, furthest(3, 3));
	}
	
	@Test 
	public void test3() {
		assertEquals(862641, furthest(1313, 5858));
	}
	
	@Test
	public void test4() {
		assertEquals(1, furthest(1, 757065));
	}

}
