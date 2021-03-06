package com.sample.test.demo;

import static org.testng.Assert.fail;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {

    private Configuration config;
    protected WebDriver driver;
    protected String url;

    @BeforeClass(alwaysRun = true)
    public void init() throws Throwable {
        config = new Configuration();
        url = config.getUrl();
        // url = "file:///C:/Users/gle62/Documents/NBC/SQEDemonstrationChallengeUIProject-master/src/test/resources/files/index.html";
        initializelDriver();
        navigateToSite();
    }

    private void navigateToSite() {
        driver.get(url);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        try {
            driver.quit();

        } catch (Exception e) {
        }
    }

    private void initializelDriver() {
        System.setProperty("webdriver.chrome.driver",
                "src/test/resources/chromedriver/windows/chromedriver.exe");
        driver = new ChromeDriver();

        /* if (config.getBrowser().equalsIgnoreCase("chrome")) {
             if (config.getPlatform().equalsIgnoreCase("mac")) {
                 System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/mac/chromedriver");
             } else {
                 System.setProperty("webdriver.chrome.driver",
                        "src/test/resources/chromedriver/windows/chromedriver.exe");
             }
        driver = new ChromeDriver();
        }
        else {
             fail("Unsupported browser " + config.getBrowser());
         }

         */

    }


}
