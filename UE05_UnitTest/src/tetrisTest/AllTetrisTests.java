package tetrisTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BorderElementTest.class, CoordinateTest.class, GameElementTest.class, GameLogicTest.class })
public class AllTetrisTests {

}
