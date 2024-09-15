package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

	private By userName = By.name("login[username]");
	private By passWord = By.name("login[password]");
	private By loginButton = By.name("send");
	private By warningMsg = By.xpath("//div[@class='message-error error message']//div");

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void launchUrl() {

		get("https://magento.softwaretestingboard.com/customer/account/login");
	}

	public String getTitleOfHomePage() {
		return getTitle();
	}

	public void enterUsername(String username) {

		enterText(userName, username);

	}

	public void enterPassword(String password) {

		enterText(passWord, password);

	}

	public void clickLoginButton() {

		click(loginButton);

	}

	public String getWarningMessage() {

		return getText(warningMsg);
	}

}
