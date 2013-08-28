package zoo.mb;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import zoo.mb.discrete.math.chapter2.projects.DeterminePosition;
import zoo.mb.discrete.math.chapter2.projects.Prime;
import zoo.mb.mint.kata.RomanNumerals;
import zoo.mb.topcoder.BallRemoval;
import zoo.mb.topcoder.JumpFurther;

@RunWith(Suite.class)
@SuiteClasses({ DeterminePosition.class, Prime.class, RomanNumerals.class,
		JumpFurther.class, BallRemoval.class })
public class AllTests {

}
