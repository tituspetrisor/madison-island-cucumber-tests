package org.fasttrackit.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductGrid {


    @FindBy(css = ".product-name > a")
    private List<WebElement> productNameContainers;

    public List<WebElement> getProductNameContainers() {
        return productNameContainers;
    }
    public WebElement getAddToCartButton(String productName, WebDriver driver){
        return driver.findElement(By.xpath("//div[@class='product-info' and .// a[text()= '" + productName + "']]//button[@title='Add to Cart']"));

    }

    public void addProductToCart(String productName, WebDriver driver){
        getAddToCartButton(productName, driver).click();
    }
}
