package com.fpt.stepDefinitions;

import com.fpt.hooks.Hooks;
import com.fpt.pages.LoginPage;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class LoginSteps {
    private final WebDriver driver = Hooks.driver;

    private final LoginPage loginPage;

    public LoginSteps(){
        this.loginPage = new LoginPage(this.driver);
    }

    @Given("user navigate to login page")
    public void userNavigateToLoginPage() {
        loginPage.navigateToLoginPage();
    }

    @When("user type {string} and {string}")
    public void userTypeAnd(String arg0, String arg1) {
        loginPage.login(arg0, arg1, false);
    }

    @And("user submit login form")
    public void userSubmitLoginForm() {
        loginPage.submitLogin();
    }

    @Then("redirect to account page")
    public void redirectToAccountPage() {
        String currentUrl = loginPage.getCurrentUrl();

        Assert.assertFalse(currentUrl.contains("/login"), "must navigate to home page");
    }
}
