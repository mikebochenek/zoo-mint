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
 */
public class JumpFurther {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
