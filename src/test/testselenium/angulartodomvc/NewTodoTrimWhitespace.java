package testselenium.angulartodomvc;

import org.junit.Test;
import static org.junit.Assert.assertTrue;


public class NewTodoTrimWhitespace extends BaseTest {

    @Test
    public void testCreateNewTodo_TrimTextWhitespace() {
        String todoText = "         refactor code   ";
        angularPage.createTodo(todoText);
        assertTrue(angularPage.isWhitespaceTrimmed(todoText));
    }

}
