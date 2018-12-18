package util;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestPropertiesReader {

    private final String TEST_CONFIG_URL = "src/test/resources/testconfig.properties";
    private Properties properties = new Properties();
    private FileInputStream fileInputStream;

    @Getter
    private String websiteUrl;
    @Getter
    private String angularTodosUrl;
    @Getter
    private String driverLocationChrome;
    @Getter
    private String driverLocationFirefox;

    public TestPropertiesReader() {
        initializePropertiesObject();
    }

    private void initializePropertiesObject() {
        try {
            fileInputStream = new FileInputStream(TEST_CONFIG_URL);
            properties.load(fileInputStream);

            websiteUrl = properties.getProperty("website.url");
            angularTodosUrl = properties.getProperty("angulartodos.url");
            driverLocationChrome = properties.getProperty("webdriver.location.chrome");
            driverLocationFirefox = properties.getProperty("webdriver.location.firefox");
        } catch (FileNotFoundException e) {
            System.err.println("Could not find file. " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Could not read file. " + e.getMessage());
        }
    }
}
