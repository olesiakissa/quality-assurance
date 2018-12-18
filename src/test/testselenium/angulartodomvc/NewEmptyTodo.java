package testselenium.angulartodomvc;

import org.junit.Test;
import static org.junit.Assert.assertFalse;


public class NewEmptyTodo extends BaseTest {

    @Test
    public void testCreateEmptyTodo_DoesNotAffectList() {
        String todoText = "complete task";
        String emptyTodo = "";
        angularPage.createTodo(todoText);
        angularPage.createTodo(todoText+" 2");
        angularPage.createTodo(todoText+" 3");
        angularPage.createTodo(emptyTodo);
        assertFalse(angularPage.isEmptyTodoPresentInList());
    }

}
