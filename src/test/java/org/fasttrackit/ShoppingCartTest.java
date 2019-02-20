package org.fasttrackit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ShoppingCartTest {


    @Test
    public void addToCartFromSearchResultTest(){
        System.setProperty("webdriver.chrome.driver",
                "c");

        WebDriver driver = new ChromeDriver();
        driver.get("https://fasttrackit.org/selenium-test/");

        String keyword = "vase";
        String productName = "Herald Glass Vase";

        driver.findElement(By.id("search")).sendKeys(keyword);
        driver.findElement(By.className("button ")).click();
        driver.findElement(By.xpath("//div[@class='product-info' and .// a[text()= '" + productName + "']]//button[@title='Add to Cart']")).click();
        String successMessage = driver.findElement(By.className("success-msg")).getText();

        assertThat("Some of the product are not in cart", successMessage, is(productName + " was added to your shopping cart."));

        WebElement productNameInCart = driver.findElement(By.xpath("//table[@id='shopping-cart-table']//a[text()='"+ productName + "']"));

        assertThat("product not displayed in cart", productNameInCart.isDisplayed());
    }
}
