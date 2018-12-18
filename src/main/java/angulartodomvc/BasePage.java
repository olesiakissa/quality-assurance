package angulartodomvc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * This class contains the common methods of all page classes.
 */
public class BasePage {

    public WebDriver webDriver;
    public WebDriverWait wait;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 10);
    }

    /**
     * @param elementBy locator for the element that must be checked
     *                  if it is present on the page.
     */
    public void waitVisibility(By elementBy) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
    }

    public void click(By elementBy) {
        waitVisibility(elementBy);
        webDriver.findElement(elementBy).click();
    }

    public void writeText(By elementBy, String text) {
        waitVisibility(elementBy);
        webDriver.findElement(elementBy).sendKeys(text);
    }

    public void submit(By elementBy) {
        waitVisibility(elementBy);
        webDriver.findElement(elementBy).submit();
    }

    public void clear(By elementBy) {
        waitVisibility(elementBy);
        webDriver.findElement(elementBy).clear();
    }

}
