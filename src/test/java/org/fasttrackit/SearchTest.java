package org.fasttrackit;

import org.fasttrackit.pageobjects.Header;
import org.fasttrackit.pageobjects.ProductGrid;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class SearchTest extends TestBase {
    private String keyword;

    public SearchTest(String keyword) {
        this.keyword = keyword;
    }

    @Parameterized.Parameters
    public static List<String> inputData(){
        return Arrays.asList("vase", "camera");
    }

    @Test
    public void searchByOneKeywordTest() {

        Header header = PageFactory.initElements(driver, Header.class);
        header.search(keyword);

        //        driver.findElement(By.tagName("button")).click();
//        driver.findElement(By.className("button ")).click();
        driver.findElement(By.name("q"));
//        driver.findElement(By.linkText("WOMEN")).click();

        ProductGrid productGrid = PageFactory.initElements(driver, ProductGrid.class);

        for (WebElement container : productGrid.getProductNameContainers()) {
            String productName = container.getText();
            assertThat("Some of the product name do not contain the searched keyword", productName, containsString(keyword.toUpperCase()));
            System.out.println(productName);

        }

    }
}
