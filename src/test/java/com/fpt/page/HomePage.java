package com.fpt.page;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {
    private WebDriver driver;

    @AndroidFindBy(id = "android:id/action_bar")
    @iOSXCUITFindBy(id = "")
    private WebElement actionBar;

    @AndroidFindBy(accessibility = "Search")
    private WebElement searchButton;

    @AndroidFindBy(id = "android:id/search_src_text")
    private WebElement searchField;

    @AndroidFindBy(id = "com.afwsamples.testdpc:id/search_result_list")
    private WebElement searchResultList;

    @AndroidFindBy(accessibility = "Collapse")
    private WebElement backButton;

    @AndroidFindBy(accessibility = "Clear query")
    private WebElement clearQuery;

    public HomePage(AppiumDriver driver){
        this.driver = driver;
        // page factory map all annotation
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebElement getActionBar() {
        return actionBar;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

    public WebElement getSearchField() {
        return searchField;
    }

    public WebElement getSearchResultList() {
        return searchResultList;
    }

    public WebElement getBackButton() {
        return backButton;
    }

    public WebElement getClearQuery() {
        return clearQuery;
    }

    public HomePage search(String search) {
        searchButton.click();
        searchField.sendKeys(search);

        return this;
    }

    public List<String> getSearchResult() {
        // get all child class and return its text
        return searchResultList.findElements(AppiumBy.className("android.widget.TextView")).stream().map(WebElement::getText).toList();
    }

    public HomePage clearQuery(){
        clearQuery.click();
        return this;
    }
}
