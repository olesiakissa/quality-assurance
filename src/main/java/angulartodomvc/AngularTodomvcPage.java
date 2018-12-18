package angulartodomvc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.TestPropertiesReader;


public class AngularTodomvcPage extends BasePage {

    private TestPropertiesReader propertiesReader = new TestPropertiesReader();
    private String pageUrl = propertiesReader.getAngularTodosUrl();
    private By inputTodo = By.className("new-todo");

    /**
     * The PageFactory processes all the annotated WebElements,
     * locates the element on the page using the annotated selector,
     * and helps to reduce the amount of boiler-plate code.
     */
    public AngularTodomvcPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void loadPage() {
        webDriver.get(pageUrl);
    }

    public void createTodo(String textToWrite) {
        click(inputTodo);
        writeText(inputTodo, textToWrite);
        submit(inputTodo);
        clear(inputTodo);
    }

    public boolean isInputFocused() {
        WebElement focusedElement = webDriver.switchTo().activeElement();
        return focusedElement.equals(webDriver.findElement(inputTodo));
    }

    public boolean isInputCleared() {
        return webDriver.findElement(inputTodo).getText().equals("");
    }

    public boolean isEmptyTodoPresentInList() {
        String locatorXpathTodoItem = "(.//*[normalize-space(text()) and normalize-space(.)='Mark all as complete'])[1]/following::label[1]";
        return webDriver.findElement(By.xpath(locatorXpathTodoItem)).getText().equals("");
    }

    public boolean isTodoPresentInList(String textOfTodo) {
        String locatorXpathTodoItem = "(.//*[normalize-space(text()) and normalize-space(.)='Mark all as complete'])[1]/following::label[1]";
        return webDriver.findElement(By.xpath(locatorXpathTodoItem)).getText().equals(textOfTodo);
    }

    public boolean isWhitespaceTrimmed(String textOfTodo) {
        String locatorXpathTodoItem = "(.//*[normalize-space(text()) and normalize-space(.)='Mark all as complete'])[1]/following::label[1]";
        WebElement createdTodo = webDriver.findElement(By.xpath(locatorXpathTodoItem));
        return createdTodo.getText().equals(textOfTodo.trim());
    }

    public void editTodo(String originalText, String textToPaste) {
        String locatorXpathTodoItem = "(.//*[normalize-space(text()) and normalize-space(.)='Mark all as complete'])[1]/following::label[1]";
        By todo = By.xpath(locatorXpathTodoItem);
        click(todo);
        String locatorXpathCurrentTodo = "(.//*[normalize-space(text()) and normalize-space(.)='" + originalText + "'])[1]/following::input[1]";
        By currentTodo = By.xpath(locatorXpathCurrentTodo);
        // might catch an error in Firefox > 59.0 because of gecko bug
        // https://github.com/mozilla/geckodriver/issues/661
        // sometimes doubleClick does not trigger the dbclick action
        Actions action = new Actions(webDriver);
        action.doubleClick(webDriver.findElement(currentTodo)).doubleClick(webDriver.findElement(currentTodo)).build().perform();
        clear(currentTodo);
        writeText(currentTodo, textToPaste);
        submit(currentTodo);
    }

    public void removeTodo(String todoText) {
        String locatorXpathTodoRemoveBtn = "(.//*[normalize-space(text()) and normalize-space(.)='" + todoText + "'])[1]/following::button[1]";
        String locatorXpathTodo = "(.//*[normalize-space(text()) and normalize-space(.)='Mark all as complete'])[1]/following::label[1]";
        By todoRemoveBtn = By.xpath(locatorXpathTodoRemoveBtn);
        WebElement removeBtn = webDriver.findElement(todoRemoveBtn);
        Actions action = new Actions(webDriver);
        action.click(webDriver.findElement(By.xpath(locatorXpathTodo))).build().perform();
        action.moveToElement(webDriver.findElement(By.xpath(locatorXpathTodo))).build().perform();
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(locatorXpathTodoRemoveBtn))));
        removeBtn.click();
    }

    public void completeTodo() {
        String locatorXpathTodoToComplete = "(.//*[normalize-space(text()) and normalize-space(.)='Mark all as complete'])[1]/following::label[1]";
        Actions action = new Actions(webDriver);
        waitVisibility(By.xpath(locatorXpathTodoToComplete));
        action.click(webDriver.findElement(By.xpath(locatorXpathTodoToComplete))).build().perform();
        String locatorXpathTodo = "(.//*[normalize-space(text()) and normalize-space(.)='Mark all as complete'])[1]/following::input[1]";
        action.moveToElement(webDriver.findElement(By.xpath(locatorXpathTodo))).click().build().perform();
    }

    public boolean isPresentInCompletedTodosList(String todoText) {
        click(By.linkText("Completed"));
        String locatorXpathCompletedTodoItem = "(.//*[normalize-space(text()) and normalize-space(.)='Mark all as complete'])[1]/following::label[1]";
        return webDriver.findElement(By.xpath(locatorXpathCompletedTodoItem)).getText().equals(todoText);
    }

}
