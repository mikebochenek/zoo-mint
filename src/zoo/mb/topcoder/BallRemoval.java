package zoo.mb.topcoder;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * You have N balls, where N is odd. The balls are numbered from 0 to N-1.
 * In that order, they are arranged into a row going from the left to the right.
 * 
 * In addition to the number, each ball has either the word "left" or the word "right" 
 * written on it. For simplicity, we will use the character '<' instead of "left", 
 * and the character '>' instead of "right". You are given the labels on all balls
 * as the String label. For each i, character i of label represents the word on ball i.
 * 
 * You will now repeat the following procedure:
 * 1. Choose a ball that is not at either end of the row of balls.
 * 2. If the chosen ball has the label '<', remove the chosen ball and also the ball 
 *    immediately to the left of it. Otherwise, remove the chosen ball and also the ball 
 *    to the right of it.
 * 3. Without reordering the remaining balls, push them together to get rid of the gap 
 *    created in the previous step.
 * 
 * The process ends when only one ball remains in the row. That ball is called the survivor.
 * Note that the numbers on the balls do not change during the process.
 * 
 * Find all possible survivors. Your method must return a String containing exactly
 * N characters. If ball i can be the survivor, character i of the return value must
 * be 'o' (lowercase oh). Otherwise, the corresponding character must be '.' (a period).
 *
 * Definition
 * Class:	BallRemoval
 * Method:	canLeave
 * Parameters:	String
 * Returns:	String
 * Method signature:	String canLeave(String label)
 * (be sure your method is public)
 * 
 * Constraint
 * -	label will contain between 3 and 49 characters, inclusive
 * -	label will contain an odd number of characters.
 * -	Each character of label will be either '>' or '<'.
 * 
 * http://community.topcoder.com/stat?c=problem_statement&pm=12157
 */
public class BallRemoval {

	private String canLeave(String label) {
		return null;
	}
	
	@Test
	public void test0() {
		assertEquals("..o", canLeave("<<>"));
		// Initially, you have three balls. Since you cannot choose balls at the ends
		// of the row, you have to choose ball 1. As its label is '<', you remove 
		// balls 0 and 1. Hence the only possible survivor is ball 2.
	}

	@Test
	public void test1() {
		assertEquals("o...o", canLeave(">>><<"));
		// If you choose ball 2 or ball 3 first, you have to choose ball 1 next, 
		// and the survivor will be ball 0. If you choose ball 1 first,
		// you have to choose ball 3 next, and the survivor will be ball 4.
	}

	@Test
	public void test2() {
		assertEquals("....o", canLeave("<<><<"));
	}

	@Test
	public void test3() {
		assertEquals("o.....o", canLeave("<><<><>"));
	}

	@Test
	public void test4() {
		assertEquals("o.....o.o.....o", canLeave(">>><<<>>>>><<<>"));
	}

}
