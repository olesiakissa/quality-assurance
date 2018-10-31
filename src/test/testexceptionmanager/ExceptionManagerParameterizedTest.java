package testexceptionmanager;

import exceptionmanager.ExceptionManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ExceptionManagerParameterizedTest {

    private boolean expectedTrueOrFalse;
    private Exception exception;
    private ExceptionManager exceptionManager;

    @Before
    public void setUp() {
        exceptionManager = new ExceptionManager();
    }

    @Parameterized.Parameters
    public static Collection criticalExceptions() {
        return Arrays.asList(new Object[][] {
                {new NumberFormatException(), true},
                {new NullPointerException(), true},
                {new FileNotFoundException(), true},
                {new IOException(), true},
                {new ClassCastException(), false},
                {new ClassNotFoundException(), false}
        });
    }

    public ExceptionManagerParameterizedTest(Exception exception, Boolean result) {
        this.exception = exception;
        this.expectedTrueOrFalse = result;
    }

    @Test
    public void testIsExceptionCritical_Parameterized() {
        assertEquals(expectedTrueOrFalse, exceptionManager.isExceptionCritical(exception));
    }
}
