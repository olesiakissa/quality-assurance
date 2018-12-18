package testselenium.angulartodomvc;

import org.junit.Test;
import static org.junit.Assert.assertTrue;


public class CompleteTodoPresentOnMainTab extends BaseTest {

    @Test
    public void testCompleteTodo_IsCompletedAndStillPresentOnMainTab() {
        String todoText = "to do";
        angularPage.createTodo(todoText);
        angularPage.completeTodo();
        assertTrue(angularPage.isTodoPresentInList(todoText));
    }

}
