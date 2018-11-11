package refactoredexceptionmanager;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({ExceptionManagerServerTest.class,
                     ErrorProcessorTest.class,
                     ParseUtilsTest.class})
public class TestSuiteHolder {
}
