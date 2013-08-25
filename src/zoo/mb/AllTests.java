package zoo.mb;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import zoo.mb.discrete.math.chapter2.projects.DeterminePosition;
import zoo.mb.discrete.math.chapter2.projects.Prime;
import zoo.mb.mint.kata.RomanNumerals;

@RunWith(Suite.class)
@SuiteClasses({ DeterminePosition.class, Prime.class, RomanNumerals.class })
public class AllTests {

}
