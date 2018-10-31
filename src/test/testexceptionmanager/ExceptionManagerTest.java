package testexceptionmanager;

import exceptionmanager.ExceptionManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ExceptionManagerTest {

    private ExceptionManager exceptionManager;

    @Before
    public void setUp() {
        exceptionManager = new ExceptionManager();
    }

    @Test
    public void isExceptionCritical_ReturnsTrue() {
        assertTrue(exceptionManager.isExceptionCritical(new NumberFormatException()));
    }

    @Test
    public void isExceptionCritical_ReturnsFalse() {
        assertFalse(exceptionManager.isExceptionCritical(new ArrayStoreException()));
    }

    @Test
    public void testHandleException_IncrementCriticalExceptionCounter() {
        int initialCounter = exceptionManager.getCriticalExceptionCounter();
        exceptionManager.handleException(new NullPointerException());
        assertEquals(initialCounter + 1, exceptionManager.getCriticalExceptionCounter());
    }

    @Test
    public void testHandleException_IncrementRegularExceptionCounter() {
        int initialCounter = exceptionManager.getRegularExceptionCounter();
        exceptionManager.handleException(new ClassNotFoundException());
        assertEquals(initialCounter + 1, exceptionManager.getRegularExceptionCounter());
    }

}
