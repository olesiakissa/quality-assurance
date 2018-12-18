package testselenium.angulartodomvc;

import org.junit.Test;
import static org.junit.Assert.assertTrue;


public class NewTodoInputCleared extends BaseTest {

    @Test
    public void testCreateNewTodo_SucceedAndInputCleared() {
        String todoText = "create project";
        angularPage.createTodo(todoText);
        assertTrue(angularPage.isInputCleared());
    }

}
