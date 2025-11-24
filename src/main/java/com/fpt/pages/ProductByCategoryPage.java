package com.fpt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductByCategoryPage {
    public static int addProducts(WebDriver driver, String category){
        // navigate to specific category
        List<WebElement> list = driver.findElements(By.cssSelector("ul.top-menu li a"));
        for (WebElement a : list){
            if (a.getText().equalsIgnoreCase(category)){
                a.click();
                break;
            }
        }
        // wait for product grid load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product-grid")));
        // add all products
        // get all exist add to cart button
        List<WebElement> addToCartButtons = driver.findElements(By.cssSelector("input.button-2.product-box-add-to-cart-button"));
        for (WebElement e : addToCartButtons){
            // click button to add product
            e.click();
            // wait for notify
            WebElement notify = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bar-notification")));
            notify.findElement(By.className("close")).click();
            wait.until(ExpectedConditions.invisibilityOf(notify)); // close notify
        }
        return addToCartButtons.size();
    }
}
