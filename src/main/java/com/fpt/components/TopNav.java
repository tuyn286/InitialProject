package com.fpt.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TopNav {
    private final By registerLink = By.xpath("//a[@class='ico-register']");
    private final By loginLink = By.xpath("//a[@class='ico-login']");
    private final By accountInfo = By.xpath("//a[@class='account']");
    private final By logOutLink = By.xpath("//a[@class='ico-logout']");
    private final By cartLink = By.xpath("//a[@class='ico-cart']");
    private final By wishListLink = By.xpath("//a[@class='ico-wishlist']");
    private WebDriver driver;
    public TopNav(WebDriver driver){
        this.driver = driver;
    }
    public void openRegisterPage(){
        driver.findElement(registerLink).click();
    }
    public void openLoginPage(){
        driver.findElement(loginLink).click();
    }
    public void openCartPage(){
        driver.findElement(cartLink).click();
    }
    public void openWishListPage(){
        driver.findElement(wishListLink).click();
    }
    public void logout(){
        driver.findElement(logOutLink).click();
    }
    public void openAccountPage(){
        driver.findElement(accountInfo).click();
    }
}
