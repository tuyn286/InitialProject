package com.fpt.stepDefinitions;

import com.fpt.hooks.Hooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Map;

public class ExampleSteps {
    private String url;
    private final WebDriver driver = Hooks.driver;
    @Given("user has home page url")
    public void user_has_home_page_url() {
        this.url = "https://aristino.com/";
    }
    @When("user navigate to home page url")
    public void user_navigate_to_home_page_url() {
        driver.get(url);
    }
    @Then("home page has a logo")
    public void home_page_has_a_logo() {
        By locator = By.id("mainHeader");
        Assert.assertTrue(driver.findElement(locator).isDisplayed());
    }

    @Given("user open open login page by url {string}")
    public void userOpenOpenLoginPageByUrl(String arg0) {
        driver.get(arg0);
    }

    @And("user type username and password")
    public void userTypeUsernameAndPassword(DataTable table) {
        Map<String, String> data = table.asMap(String.class, String.class);
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("user click submit button")
    public void userClickSubmitButton() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("account page is displayed")
    public void accountPageIsDisplayed() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
