package org.fasttrackit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchTest {

    @Test
    public void searchByOneKeywordTest(){
        System.setProperty("webdriver.chrome.driver",
                AppConfig.getChromeDriverPath());

        WebDriver driver = new ChromeDriver();
        driver.get(AppConfig.getSiteUrl());
        System.out.println("Opened homepage");

        String keyword = "vase";

        driver.findElement(By.id("search")).sendKeys(keyword);
        System.out.println("Pressed enter in search field");

        //        driver.findElement(By.tagName("button")).click();
        driver.findElement(By.className("button ")).click();
        driver.findElement(By.name("q"));
//        driver.findElement(By.linkText("WOMEN")).click();
        List<WebElement> productNameContainters = driver.findElements(By.cssSelector(".product-name > a"));
        for (WebElement container : productNameContainters) {
            String productName = container.getText();
            assertThat("Some of the product name do not contain the searched keyword", productName, containsString(keyword.toUpperCase()));
            System.out.println(productName);

        }

    }
}
