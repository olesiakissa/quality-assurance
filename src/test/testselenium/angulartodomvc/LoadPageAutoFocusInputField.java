package testselenium.angulartodomvc;

import org.junit.Test;
import static org.junit.Assert.assertTrue;


public class LoadPageAutoFocusInputField extends BaseTest {

    @Test
    public void testLoadPage_AutoFocusOnInputField() {
        assertTrue(angularPage.isInputFocused());
    }

}
