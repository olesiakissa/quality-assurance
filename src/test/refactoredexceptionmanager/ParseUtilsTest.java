package refactoredexceptionmanager;

import org.junit.Before;
import org.junit.Test;
import util.ParseUtils;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class ParseUtilsTest {

    private ParseUtils parseUtils;
    private Object newExceptionObject;


    @Before
    public void setUp() {
        parseUtils = new ParseUtils();
    }

    @Test
    public void testConvertExceptionStringNameToClassObject_ConversionSucceed() {
        String expectedExceptionClassName = "NullPointerException";
        newExceptionObject = parseUtils.convertExceptionStringNameToClassObject(expectedExceptionClassName);
        String resultExceptionClassName = newExceptionObject.getClass().getSimpleName();
        assertEquals(expectedExceptionClassName, resultExceptionClassName);
    }

    @Test
    public void testConvertExceptionStringNameToClassObject_ReturnsExceptionObject() {
        String exceptionClassName = "NegativeArraySizeException";
        newExceptionObject = parseUtils.convertExceptionStringNameToClassObject(exceptionClassName);
        assertTrue(newExceptionObject instanceof Exception);
    }

    @Test
    public void testGetExceptionClass_ReturnsClass() {
        String exceptionClassName = "SecurityException";
        newExceptionObject = parseUtils.convertExceptionStringNameToClassObject(exceptionClassName);
        assertNotNull(parseUtils.getExceptionClass(newExceptionObject));
    }

}
