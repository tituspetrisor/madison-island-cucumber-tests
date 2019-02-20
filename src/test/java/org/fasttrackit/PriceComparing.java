package org.fasttrackit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PriceComparing {

    @Test
    public void priceCompare() {
        System.setProperty("webdriver.chrome.driver",
                "src//test//resources//drivers//chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://fasttrackit.org/selenium-test/");
        String keyWord = "vase";
        driver.findElement(By.id("search")).
                sendKeys(keyWord);
        System.out.println("Pressed enter in search field."); // log example
        driver.findElement(By.tagName("button")).click();

        String[] oldPrice = driver.findElement(By.xpath("//div[@class='price-box']//p[@class='old-price']//span[@class='price']")).getText().split(" ");

        String part = oldPrice[0];
        System.out.println(part);

        double value = Double.parseDouble( part.replace("," , "."));
        System.out.println(value);

        String[] newPrice = driver.findElement(By.xpath("//div[@class='price-box']//p[@class='special-price']//span[@class='price']")).getText().split(" ");

        String part1 = newPrice[0];
        System.out.println(part1);

        double value1 = Double.parseDouble( part1.replace("," , "."));
        System.out.println(value1);

        if (value >= value1) {
            boolean result = true;
            assertThat("The price is bigger than the initial price",result,is(false));
        }

    }
}
