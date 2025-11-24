package com.fpt.pages;

import com.fpt.components.TopNav;
import com.fpt.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class LoginPage extends BasePage{
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
    private final By emailField = By.id("Email");
    private final By passwordField = By.id("Password");
    private final By rememberCheck = By.id("RememberMe");
    private final By loginButton = By.cssSelector("input.button-1.login-button");

    private final By accountInfo = By.className("account");
    private WebDriver driver;
    private TopNav topNav;
    private WebDriverWait wait;
    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        topNav = new TopNav(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void setEmail(String email){
        type(emailField, email);
    }
    public void setPassword(String password){
        type(passwordField, password);
    }
    public void setRememberCheck(){
        click(rememberCheck);
    }
    public void submitLogin(){
        click(loginButton);
    }
    public String login(String email, String password, boolean rememberMe){
        topNav.openLoginPage();
        logger.info("open login form");
        setEmail(email);
        logger.info("input email");
        setPassword(password);
        logger.info("input password");
        if (rememberMe){
            setRememberCheck();
            logger.info("check remember me");
        }
        logger.info("submit form");

        return wait.until(ExpectedConditions.visibilityOfElementLocated(accountInfo)).getText();
    }
    public void logout(){
        topNav.logout();
    }

    public void navigateToLoginPage(){
        String url = ConfigReader.getProperty("domain.url") + "login";
        driver.get(url);
    }

    public String getCurrentUrl(){
        return this.driver.getCurrentUrl();
    }
}
