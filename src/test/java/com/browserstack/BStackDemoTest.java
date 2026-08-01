package com.browserstack;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class BStackDemoTest extends SeleniumTest {

    @Test
    public void addProductToCart() throws Exception {

        driver.get("https://www.bstackdemo.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Check title
        wait.until(ExpectedConditions.titleIs("StackDemo"));
        Assert.assertEquals(driver.getTitle(), "StackDemo");

        // Wait for product to appear
        By product = By.xpath("//*[@id='1']/p");
        wait.until(ExpectedConditions.visibilityOfElementLocated(product));

        // Save product name
        String productOnScreenText =
                driver.findElement(product).getText();

        // Add product to cart
        By addToCart = By.xpath("//*[@id='1']/div[4]");
        wait.until(ExpectedConditions.elementToBeClickable(addToCart));
        driver.findElement(addToCart).click();

        // Wait for cart
        By cart = By.cssSelector(".float-cart__content");
        wait.until(ExpectedConditions.visibilityOfElementLocated(cart));

        Assert.assertTrue(
                driver.findElement(cart).isDisplayed()
        );

        // Get product from cart
        By productInCart = By.xpath(
                "//*[@id='__next']/div/div/div[2]/div[2]/div[2]/div/div[3]/p[1]"
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(productInCart)
        );

        String productOnCartText =
                driver.findElement(productInCart).getText();

        Assert.assertEquals(
                productOnScreenText,
                productOnCartText
        );
    }
}