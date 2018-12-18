package testselenium.angulartodomvc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoadPage.class,
        LoadPageAutoFocusInputField.class,
        NewTodoInputCleared.class,
        NewTodoItemAppeared.class,
        NewTodoTrimWhitespace.class,
        NewEmptyTodo.class,
        EditTodo.class,
        RemoveTodo.class,
        CompleteTodoPresentOnCompletedTab.class,
        CompleteTodoPresentOnMainTab.class
})
public class AngularTodomvcTestSuiteHolder extends BaseTest {

}

