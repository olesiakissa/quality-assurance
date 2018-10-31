package testexceptionmanager;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class ExceptionManagerTestRunner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ExceptionManagerTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }

}
