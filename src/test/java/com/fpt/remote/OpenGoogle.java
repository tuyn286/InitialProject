package com.fpt.remote;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URL;

public class OpenGoogle {

    @Test
    public void openGoogleOnEdgeWindows11() throws Exception {
        String hub = System.getenv().getOrDefault("BROWSERSTACK_HUB", "https://hub.browserstack.com/wd/hub");

        MutableCapabilities caps = new MutableCapabilities();

        MutableCapabilities bstackOptions = new MutableCapabilities();
        bstackOptions.setCapability("userName", System.getenv("BS_USERNAME"));
        bstackOptions.setCapability("accessKey", System.getenv("BS_PASSWORD"));
        bstackOptions.setCapability("projectName", System.getenv("PROJECT_NAME"));
        bstackOptions.setCapability("buildName", System.getenv("BUILD_NAME"));
        bstackOptions.setCapability("sessionName", System.getenv("SESSION_NAME"));
        bstackOptions.setCapability("os", System.getenv().getOrDefault("OS_NAME", "Windows"));
        bstackOptions.setCapability("osVersion", System.getenv().getOrDefault("OS_VERSION", "11"));

        caps.setCapability("bstack:options", bstackOptions);

        caps.setCapability("browserName", System.getenv().getOrDefault("BROWSER_NAME", "Edge"));
        caps.setCapability("browserVersion", System.getenv().getOrDefault("BROWSER_VERSION", "latest"));

        RemoteWebDriver driver = new RemoteWebDriver(new URL(hub), caps);
        try {
            driver.get("https://www.google.com");
            Thread.sleep(2000);
            String title = driver.getTitle();
            Assert.assertTrue(title.toLowerCase().contains("google"), "Page title should contain 'Google' but was: " + title);
        } finally {
            driver.quit();
        }
    }
}
