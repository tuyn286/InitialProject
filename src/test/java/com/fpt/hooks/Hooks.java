package com.fpt.hooks;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Hooks {
    public static final String BROWSERSTACK_URL = "https://hub.browserstack.com/wd/hub";

    public static WebDriver driver;
    @Before
    public void setUp() throws MalformedURLException {
        System.out.println("---  Before scenario  ---");
        String platform = System.getenv("PLATFORM");
        URI uri = URI.create(BROWSERSTACK_URL);
        URL url = uri.toURL();
        MutableCapabilities capabilities;

        switch (platform) {
            case "ANDROID" :
                capabilities = this.androidCapabilities();
                driver = new AndroidDriver(url, capabilities);
                System.out.println("init driver success");
                break;
            case "IOS" :
                capabilities = this.iosCapabilities();
                driver = new IOSDriver(url, capabilities);
                break;
            default :
                capabilities = this.websiteCapabilities();
                driver = new RemoteWebDriver(url, capabilities);
                break;
        }
    }

    @After
    public void tearDown(Scenario scenario){
        // send status to browser stack
        if (scenario.isFailed()) {
            // print out page source DOM
            System.out.println(driver.getPageSource());
            ((JavascriptExecutor) driver).executeScript(
                    "browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Scenario failed\"}}"
            );
        } else {
            ((JavascriptExecutor) driver).executeScript(
                    "browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"passed\", \"reason\": \"Scenario passed\"}}"
            );
        }

        if (driver != null) {
            driver.quit();
        }
        System.out.println("---  After scenario  ---");
    }


    public MutableCapabilities websiteCapabilities(){
        MutableCapabilities caps = new MutableCapabilities ();

        MutableCapabilities browserstackOptions = new MutableCapabilities();
        browserstackOptions.setCapability("os", System.getenv("OS_NAME"));
        browserstackOptions.setCapability("osVersion", System.getenv("OS_VERSION"));
        browserstackOptions.setCapability("userName", System.getenv("BS_USERNAME"));
        browserstackOptions.setCapability("accessKey", System.getenv("BS_PASSWORD"));
        browserstackOptions.setCapability("browserName", System.getenv("BROWSER_NAME"));
        browserstackOptions.setCapability("browserVersion", System.getenv("BROWSER_VERSION"));
        browserstackOptions.setCapability("projectName", System.getenv("PROJECT_NAME"));
        browserstackOptions.setCapability("buildName", System.getenv("BUILD_NAME"));
        browserstackOptions.setCapability("sessionName", System.getenv("SESSION_NAME"));

        caps.setCapability("bstack:options", browserstackOptions);

        return caps;
    }

    public MutableCapabilities iosCapabilities() {
        MutableCapabilities caps = new MutableCapabilities ();
        caps.setCapability("app", System.getenv("APP_URL"));

        Map<String, Object> browserstackOptions = new HashMap<>();
        browserstackOptions.put("deviceName", System.getenv("DEVICE_NAME"));
        browserstackOptions.put("osVersion", System.getenv("OS_VERSION"));
        browserstackOptions.put("projectName", System.getenv("PROJECT_NAME"));
        browserstackOptions.put("buildName", System.getenv("BUILD_NAME"));
        browserstackOptions.put("sessionName", System.getenv("SESSION_NAME"));
        browserstackOptions.put("realMobile", "true");

        caps.setCapability("bstack:options", browserstackOptions);

        return caps;
    }

    public MutableCapabilities androidCapabilities() {
        MutableCapabilities caps = new MutableCapabilities ();

        MutableCapabilities bsCapabilities = new MutableCapabilities();
        bsCapabilities.setCapability("userName", System.getenv("BS_USERNAME"));
        bsCapabilities.setCapability("accessKey", System.getenv("BS_PASSWORD"));
        bsCapabilities.setCapability("projectName", System.getenv("PROJECT_NAME"));
        bsCapabilities.setCapability("buildName", System.getenv("BUILD_NAME"));
        bsCapabilities.setCapability("sessionName", System.getenv("SESSION_NAME"));

        caps.setCapability("bstack:options", bsCapabilities);

        caps.setCapability("appium:app", System.getenv("APP_URL"));
        caps.setCapability("appium:deviceName", System.getenv("DEVICE_NAME"));
        caps.setCapability("appium:osVersion", System.getenv("OS_VERSION"));
        caps.setCapability("appium:autoGrantPermissions", true);
        caps.setCapability("appium:realMobile", "true");

        return caps;
    }

}
