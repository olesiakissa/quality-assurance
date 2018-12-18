package testselenium.angulartodomvc;

import angulartodomvc.AngularTodomvcPage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import util.TestPropertiesReader;
import java.util.concurrent.TimeUnit;


/**
 * Base methods for handling driver setup and teardown.
 */
public class BaseTest {

    protected static WebDriver webDriver;
    protected static JavascriptExecutor javascriptExecutor;
    protected static TestPropertiesReader propertiesReader = new TestPropertiesReader();
    protected static AngularTodomvcPage angularPage;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.gecko.driver", propertiesReader.getDriverLocationFirefox());
        webDriver = new FirefoxDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        javascriptExecutor = (JavascriptExecutor) webDriver;
    }

    @Before
    public void initializePage() {
        webDriver.manage().deleteAllCookies();
        angularPage = new AngularTodomvcPage(webDriver);
    }

    @After
    public void cleanUp() {
        webDriver.manage().deleteAllCookies();
        javascriptExecutor.executeScript("window.localStorage.clear();");
    }

    @AfterClass
    public static void tearDown() {
        webDriver.quit();
    }

}
