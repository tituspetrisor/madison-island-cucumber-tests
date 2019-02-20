package org.fasttrackit;

import org.fasttrackit.pageobjects.Header;
import org.fasttrackit.pageobjects.ProductGrid;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ShoppingCartTest {


    @Test
    public void addToCartFromSearchResultTest(){
        System.setProperty("webdriver.chrome.driver", AppConfig.getChromeDriverPath());

        WebDriver driver = new ChromeDriver();
        driver.get(AppConfig.getSiteUrl());
        String keyword = "vase";
        String productName = "Herald Glass Vase";

        Header header = PageFactory.initElements(driver, Header.class);
        header.search(keyword);

        ProductGrid productGrid = PageFactory.initElements(driver, ProductGrid.class);
        productGrid.addProductToCart(productName,driver);


        String successMessage = driver.findElement(By.className("success-msg")).getText();

        assertThat("Some of the product are not in cart", successMessage, is(productName + " was added to your shopping cart."));

        WebElement productNameInCart = driver.findElement(By.xpath("//table[@id='shopping-cart-table']//a[text()='"+ productName + "']"));

        assertThat("product not displayed in cart", productNameInCart.isDisplayed());
    }
}
