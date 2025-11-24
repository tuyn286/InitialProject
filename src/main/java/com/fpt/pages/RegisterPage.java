package com.fpt.pages;

import com.fpt.components.TopNav;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;


public class RegisterPage extends BasePage{
    private static final Logger logger = LoggerFactory.getLogger(RegisterPage.class);
    private final By maleField = By.id("gender-male");
    private final By femaleField = By.id("gender-female");
    private final By firstNameField = By.id("FirstName");
    private final By lastNameField = By.id("LastName");
    private final By emailField = By.id("Email");
    private final By passwordField = By.id("Password");
    private final By confirmPasswordField = By.id("ConfirmPassword");
    private final By registerButton = By.id("register-button");
    private final By result = By.xpath("//div[@class='result' or @class='validation-summary-errors']");
    private WebDriver driver;
    private WebDriverWait wait;
    private TopNav topNav;
    public RegisterPage(WebDriver driver){
        super(driver);
        topNav = new TopNav(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void checkGender(String gender){
        if (gender.equalsIgnoreCase("male")){
            click(maleField);
        } else {
            click(femaleField);
        }
    }
    public void setFirstName(String firstName){
        type(firstNameField, firstName);
    }
    public void setLastName(String lastName){
        type(lastNameField, lastName);
    }
    public void setEmail(String email){
        type(emailField, email);
    }
    public void setPassword(String password){
        type(passwordField, password);
    }
    public void setConfirmPassword(String confirmPassword){
        type(confirmPasswordField, confirmPassword);
    }
    public void submitForm(){
        click(registerButton);
    }

    public String successRegister(String gender,
                                  String email,
                                  String firstName,
                                  String lastName,
                                  String password,
                                  String confirmPassword){
        topNav.openRegisterPage();
        logger.info("navigate to register page");
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerButton));
        logger.info("loaded page successfully");
        checkGender(gender);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
        setConfirmPassword(confirmPassword);
        submitForm();
        logger.info("registering...");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(result)).getText();
    }
}
