package com.fpt.stepDefinitions;

import com.fpt.hooks.Hooks;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class OpenAppSteps {

    private AppiumDriver driver = (AppiumDriver) Hooks.driver;

    @Given("the app is launched")
    public void theAppIsLaunched() {
        System.out.println("given");
        // Chỉ cần xác minh driver đã được khởi tạo
        Assert.assertNotNull(driver, "Driver is null — app was not launched!");
        System.out.println("✅ App launched successfully on remote device.");
    }

    @Then("the app should display the main screen")
    public void theAppShouldDisplayTheMainScreen() {
        try {
            List<WebElement> contactList = driver.findElements(AppiumBy.xpath("//*[@resource-id='com.example.android.contactmanager:id/contactEntryText']"));
            Assert.assertEquals(contactList.size(), 30, "Contact size not same");
        }
        catch (Exception e) {
            Assert.fail("Wrong contact size" + e.getMessage());
        }
    }

    @And("grant permission for app")
    public void grantPermissionForApp() {
        try{
            WebElement continueButton = driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Continue']"));
            continueButton.click();
        } catch (NoSuchElementException e){
            System.out.println("Cannot locate element");
        }
    }

    @And("accept old version alert")
    public void acceptOldVersionAlert() {
        WebElement warningText = driver.findElement(AppiumBy.xpath("//*[contains(@text, 'an older version of Android and may not work properly')]"));
        Assert.assertTrue(warningText.isDisplayed(), "Version warning text should be displayed");
        try {
            WebElement continueButton = driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='OK']"));
            continueButton.click();
        } catch (NoSuchElementException e){
            System.out.println("Cannot locate element");
        }
    }

    @And("open add new contact form")
    public void openAddNewContactForm() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("type {string} and {string}")
    public void typeUsernameAndPassword(String username, String password) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("contact list should have new contact")
    public void contactListShouldHaveNewContact() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("contact list should have specific contact")
    public void contactListShouldHaveSpecificContact(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
