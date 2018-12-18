package testselenium.angulartodomvc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LoadPageListsAreEmpty extends BaseTest {

    @Test
    public void testLoadPage_UrlIsValidAndPageIsLoaded() {
        assertEquals(webDriver.getCurrentUrl(), propertiesReader.getAngularTodosUrl());
    }

}
