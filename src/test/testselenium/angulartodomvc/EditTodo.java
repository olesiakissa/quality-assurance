package testselenium.angulartodomvc;

import angulartodomvc.AngularTodomvcPage;
import org.junit.Test;
import static org.junit.Assert.assertTrue;


public class EditTodo extends BaseTest {

    /**
     * Might fail because of gecko driver bug
     * For more information on this issue:
     * @see AngularTodomvcPage#editTodo(String, String)
     */
    @Test
    public void testEditTodo_SucceedAndItemChanged() {
        String todoText = "item";
        angularPage.createTodo(todoText);
        angularPage.createTodo(todoText+" 2");
        angularPage.createTodo(todoText+" 3");
        String textToPaste = "tsts";
        angularPage.editTodo(todoText, textToPaste);
        assertTrue(angularPage.isTodoPresentInList(textToPaste));
    }

}
