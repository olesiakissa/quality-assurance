package testselenium;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import util.TestPropertiesReader;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class HomePageTest {

    private final String LOCATOR_ID_INPUT_FIELD = "gh-ac";

    private static WebDriver webDriver;
    private static TestPropertiesReader propertiesReader = new TestPropertiesReader();
    private WebElement element;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", propertiesReader.getDriverLocationChrome());
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get(propertiesReader.getWebsiteUrl());
    }

    @Test
    public void testHomePageUrl_IsValidAndPageIsLoaded() {
        String actualUrl = webDriver.getCurrentUrl();
        String expectedUrl = propertiesReader.getWebsiteUrl();
        assertEquals(actualUrl, expectedUrl);
    }

    @Test
    public void testInputFieldForProductSearch_IsPresent() {
        element = webDriver.findElement(By.id(LOCATOR_ID_INPUT_FIELD));
        assertNotNull(element);
    }

    @Test
    public void searchForProduct_ReturnsListOfAvailableProducts() {
        element = webDriver.findElement(By.id(LOCATOR_ID_INPUT_FIELD));
        element.click();
        element.sendKeys("bts sweatshirt");
        element.submit();
        List<WebElement> searchResultSet = webDriver.findElements(By.id("ResultSetItems"));
        assertNotNull(searchResultSet);
    }

    @Test
    public void testAddProductToShoppingCart_ActionSucceedAndRedirectedToCart() {
        element = webDriver.findElement(By.id(LOCATOR_ID_INPUT_FIELD));
        element.click();
        element.sendKeys("bts sweatshirt");
        element.submit();
        // choosing the first found product
        WebElement desiredProduct = webDriver.findElement(By.className("imgWr2"));
        desiredProduct.click();
        // choosing additional product characteristics
        Select colorLabel = new Select(webDriver.findElement(By.id("msku-sel-1")));
        Select sizeLabel = new Select(webDriver.findElement(By.id("msku-sel-2")));
        colorLabel.selectByIndex(1);
        sizeLabel.selectByIndex(1);
        WebElement cartButton = webDriver.findElement(By.id("isCartBtn_btn"));
        cartButton.click();
        String redirectExpectedUrl = "https://cart.ebay.com/";
        assertEquals(redirectExpectedUrl, webDriver.getCurrentUrl());
        boolean cartSummaryIsPresent = webDriver.findElement(By.className("cartsummary")).isDisplayed();
        assertTrue(cartSummaryIsPresent);
    }

    @AfterClass
    public static void tearDown() {
        webDriver.quit();
    }

}
