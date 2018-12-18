package testselenium.angulartodomvc;

import org.junit.Test;
import static org.junit.Assert.assertTrue;


public class NewTodoItemAppeared extends BaseTest {

    @Test
    public void testCreateNewTodo_SucceedAndNewItemAppeared() {
        String todoText = "refactor code";
        angularPage.createTodo(todoText);
        angularPage.createTodo(todoText+" 2");
        angularPage.createTodo(todoText+" 3");
        assertTrue(angularPage.isTodoPresentInList(todoText));
    }

}
