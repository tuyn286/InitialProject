package com.fpt.home;

import com.fpt.BaseTest;
import com.fpt.pages.LoginPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.UUID;

public class Login extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(Login.class);
    private WebDriverWait wait;
    private LoginPage loginPage;
    @BeforeMethod
    public void loadPage(){
        driver.get("https://demowebshop.tricentis.com/");
        loginPage = new LoginPage(driver);
    }

//    @Test
//    public void testRegister(){
//        // prepare data
//        String email = "tuyen" + UUID.randomUUID()+"@gmail.com";
//        String password = "123456";
//        // register
//        RegisterPage.register(driver, true, "Tuyen", "Nguyen",email
//                , password, password);
//        logger.info("registing...");
//        // after submit register form successfully, system will automate login
//        WebElement loggoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ico-logout")));
//
//        Assert.assertEquals( driver.findElement(By.className("result")).getText(), "Your registration completed");
//        logger.info("register successfully");
//        // log out button appear, log out
//        loggoutButton.click();
//        logger.info("logout successfully");
//        // login again
//        LoginPage.login(driver, email, password, false);
//        // log out button appear
//        WebElement loggoutButton2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ico-logout")));
//        Assert.assertEquals(loggoutButton2.getText(), "Log out");
//        // account info appear
//        WebElement accountInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("account")));
//        Assert.assertEquals(accountInfo.getText(), email);
//        logger.info("logging successfully");
//
//        // add products to cart
//        String category = "Books";
//        int productQuantity = ProductByCategoryPage.addProducts(driver, category);
//        logger.info("navigate to "+category+" page, have "+ productQuantity+" to add.");
//        String expectResult = "("+productQuantity+")";
//        Assert.assertEquals(expectResult, driver.findElement(By.cssSelector(".ico-cart .cart-qty")).getText());
//    }

    @Test
    public void successLogin(){
        String email = "tuyen" + UUID.randomUUID()+"@gmail.com";
        String password = "123456";
        String result = loginPage.login(email, password, false);
        Assert.assertEquals(result, email, "Cannot login");
    }
}
