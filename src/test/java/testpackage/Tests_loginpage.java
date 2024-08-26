package testpackage;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import web_pages.Login_Page;


public class Tests_loginpage {
	WebDriver driver;
    Login_Page login;
    boolean errorMessageDisplay;
    
    

    @BeforeMethod
    public void setUp() {
    	WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();	
        driver.manage().window().maximize();
        login = new Login_Page(driver);

        driver.get("https://webpage.com/login");
        
    }

    @Test
    public void SuccessfulLogin() {
        // Test Login with valid credentials
    	login.inputUsername("validusername");
    	login.inputPassword("validpassword");
    	login.LoginButton();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String currenturl = login.getweb_url();
        Assert.assertEquals(currenturl, "https://webpage.com/after_login page", "Login failed with valid credentials");
        System.out.println("Login Successfully");
    }

    @Test
    public void FailedLogin() {
        // Test Login with invalid credentials and error Message 
    	login.inputUsername("invalidusername");
    	login.inputPassword("invalidpassword");
    	login.LoginButton();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        errorMessageDisplay = login.getErrorMessage();
        Assert.assertTrue(errorMessageDisplay, "Login successful with invalid credentials");
        System.out.println("Login failed - Test Passed");
    }


    @Test
    public void empty_UsernameFieldAndPasswordFields() {
        // Test Login with no Username and no Password
    	login.inputUsername("");
    	login.inputPassword("");
    	login.LoginButton();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        errorMessageDisplay = login.getErrorMessage();
        Assert.assertTrue(errorMessageDisplay, "Login successful with No username and password");
        System.out.println("Login with no username and no password failed - Test Passed");
    }

    @Test
    public void empty_UsernameField() {
    	 // Test Login with empty Username 
    	login.inputPassword("validpassword");
    	login.LoginButton();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        errorMessageDisplay = login.getErrorMessage();
        Assert.assertTrue(errorMessageDisplay, "Login successful with No username");
        System.out.println("Login with no username failed - Test Passed");
    }

    @Test
    public void emptyPasswordField() {
        // Test Login no password 
        login.inputUsername("validusername");
        login.inputPassword("");
        login.LoginButton();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        errorMessageDisplay = login.getErrorMessage();
        Assert.assertTrue(errorMessageDisplay, "Login successful with No password");
        System.out.println("Login with no password failed - Test Passed");
    }

    @Test
    public void specialCharactersInUsernamePasswordTest() {
        // Test Login using special characters in username and password fields
        login.inputUsername("validusername#@");
        login.inputPassword("validpassword#@");
        login.LoginButton();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        errorMessageDisplay = login.getErrorMessage();
        Assert.assertFalse(errorMessageDisplay, "Login failed with Special characters in username and password");
        System.out.println("Login successful with Special characters in username and password - Test Passed");
    }


    @AfterMethod
    public void tearDown() {
        // Close the browser
        driver.quit();
    }

}
