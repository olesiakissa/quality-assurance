package testselenium.angulartodomvc;

import org.junit.Test;
import static org.junit.Assert.assertTrue;


public class CompleteTodoPresentOnCompletedTab extends BaseTest {

    @Test
    public void testCompleteTodo_IsCompletedAndPresentOnCompletedTab() {
        String todoText = "item";
        angularPage.createTodo(todoText);
        angularPage.completeTodo();
        assertTrue(angularPage.isPresentInCompletedTodosList(todoText));
    }

}
