package org.fasttrackit;

import org.fasttrackit.pageobjects.DriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {


    protected WebDriver driver;
    @Before
    public void setup(){
        String browser = System.getProperty("browser", "chrome");

        driver = DriverManager.initDriver(browser);
        driver.get(AppConfig.getSiteUrl());
        System.out.println("Opened homepage");

    }

    @After

    public void tearDowm(){

        driver.quit();
    }
    public void waitForPageToLoad(long timeoutMillis){
        do{
            long waitTime = 500;
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                System.out.println("Wait interrupted " + e.getMessage());
            }
            timeoutMillis -= waitTime;
            System.out.println("Waiting for page to load. Remaining millis: " + timeoutMillis);
        } while (timeoutMillis > 0 && !((JavascriptExecutor) driver)
                .executeScript("return.readyState")
                .equals("complete"));


    }
}
