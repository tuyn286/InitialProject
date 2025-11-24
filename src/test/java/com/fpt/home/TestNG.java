package com.fpt.home;

import com.fpt.pages.LoginPage;
import com.fpt.pages.RegisterPage;
import com.fpt.utils.FileReader;
import com.fpt.utils.MailSender;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;


@Test
public class TestNG {
    private static final Logger logger = LoggerFactory.getLogger(TestNG.class);
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private WebDriver driver;
    private SoftAssert softAssert;
    private MailSender mailSender = new MailSender();
    private File testDataFile = new File(System.getProperty("user.dir")+"/src/main/resources/testdata/DemoWebShop.xlsx");
    @BeforeSuite
    public void setUp(){
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        softAssert = new SoftAssert();
    }
    @AfterClass
    public void quit(){
        driver.quit();
    }
    @AfterSuite
    public void tearDown(){
        // send mail after finish test
        mailSender.sendMail("tuyennh286@gmail.com", "test case report", "this is automated email");
    }
    @BeforeMethod
    public void redirectToHome(){
        driver.get("https://demowebshop.tricentis.com/");
        logger.info("redirect to home page");
    }
    @AfterMethod
    public void logout(ITestResult result){
        if (!result.isSuccess()){
            logger.info(result.getTestName()+" failed. Ignored logout");
            // take screenshot if test failed
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            File destination = new File(System.getProperty("user.dir")
                    + "/src/main/resources/screenshots/"+result.getName()
                    +result.getEndMillis()+".png");
            try {
                FileHandler.copy(source, destination);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            loginPage.logout();
            logger.info("logout");
        }
    }
    @DataProvider(name = "test-register")
    public Object[][] data(){
        FileReader fileReader = new FileReader();
        if (testDataFile.exists()){
            return fileReader.readData(testDataFile, "register");
        } else {
            logger.error("file not found");
            return null;
        }
    }
    @DataProvider(name = "test-login")
    public Object[][] dataLogin(){
        FileReader fileReader = new FileReader();
        if (testDataFile.exists()){
            return fileReader.readData(testDataFile, "login");
        } else {
            logger.error("file not found");
            return null;
        }
    }
    @Test(dataProvider = "test-register", priority = 1)
    public void register(String gender,
                         String firstName,
                         String lastName,
                         String email,
                         String password,
                         String confirmPassword){
        String result = registerPage.successRegister(gender, email, firstName, lastName, password, confirmPassword);
        Assert.assertEquals(result, "Your registration completed", result);
    }
    @Test(dataProvider = "test-login", priority = 2)
    public void login(String email,
                      String password){
        String result = loginPage.login(email, password, true);
        Assert.assertEquals(result, email);
    }


}
