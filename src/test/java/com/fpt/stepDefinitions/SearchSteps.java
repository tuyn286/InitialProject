package com.fpt.stepDefinitions;

import com.fpt.hooks.Hooks;
import com.fpt.page.HomePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class SearchSteps {
    private final AppiumDriver driver = (AppiumDriver) Hooks.driver;

    private final HomePage homePage;

    private List<String> searchResult;

    public SearchSteps(){
        this.homePage = new HomePage(this.driver);
    }

    @Given("user open app successfully")
    public void userOpenAppSuccessfully() {
        Assert.assertNotNull(this.driver, "the app should be open successfully");

        WebElement appName = homePage.getActionBar().findElement(AppiumBy.xpath(".//android.widget.TextView"));
        Assert.assertEquals(appName.getText(),"Policy management", "Wrong app name");
    }

    @When("user perform search functionality with input {string}")
    public void userPerformSearchFunctionalityWithInput(String search) {
        try{
            homePage.search(search);
        } catch (Exception e){
            System.out.println("Cannot perform search. Exception: "+e);
        }
    }

    @Then("search result must contain {string}")
    public void searchResultMustContain(String arg0) {
        try {
            searchResult = homePage.getSearchResult();
        } catch (Exception e){
            System.out.println("Cannot get search result. "+e);
        }

        Assert.assertTrue(searchResult.contains(arg0), "result not correct");
    }

    @And("search result have {int} result")
    public void searchResultHaveResult(int arg0) {
        Assert.assertEquals(searchResult.size(), arg0, "quantity of result not match");
    }
}
