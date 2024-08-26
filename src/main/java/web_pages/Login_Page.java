package web_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login_Page {
	 WebDriver driver;

	    
	    private By usernameinputField = By.id("username");
	    private By passwordinputField = By.id("password");
	    private By loginButton = By.id("loginButton");
	    private By errorMessage = By.id("errorMessage");

	    // Constructor
	    public Login_Page(WebDriver driver) {
	        this.driver = driver;
	    }

	    // Page Actions
	    public void inputUsername(String username) {
	        driver.findElement(usernameinputField).sendKeys(username);
	    }

	    public void inputPassword(String password) {
	        driver.findElement(passwordinputField).sendKeys(password);
	    }

	    public void LoginButton() {
	        driver.findElement(loginButton).click();
	    }

	    public boolean getErrorMessage() {
	        return driver.findElement(errorMessage).isDisplayed();
	    }

	    public String getweb_url() {
	        return driver.getCurrentUrl();
	    }

}
