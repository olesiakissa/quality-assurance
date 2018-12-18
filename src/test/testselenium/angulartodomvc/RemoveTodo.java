package testselenium.angulartodomvc;

import org.junit.Test;
import static org.junit.Assert.assertFalse;


public class RemoveTodo extends BaseTest {

    @Test
    public void testRemoveTodo_SucceedAndItemRemoved() {
        String todoText = "item";
        angularPage.createTodo(todoText);
        angularPage.createTodo(todoText+" 2");
        angularPage.createTodo(todoText+" 3");
        angularPage.removeTodo(todoText);
        assertFalse(angularPage.isTodoPresentInList(todoText));
    }

}
