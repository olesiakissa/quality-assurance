package refactoredexceptionmanager;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class ErrorProcessorTest {

    private ErrorProcessor errorProcessor;

    @Before
    public void setUp() {
        errorProcessor = mock(ErrorProcessor.class);
    }

    @Test
    public void testIncrementErrorCounter_VerifyCounterIncrementCalledOnce() {
        verify(errorProcessor, times(1)).incrementErrorCounter();
    }

}
