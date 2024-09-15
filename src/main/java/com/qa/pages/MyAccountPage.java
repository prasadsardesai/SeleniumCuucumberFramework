package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BasePage {

	private WebDriver driver;

	private By contactInfo = By.xpath("//div[@class='box box-information']//div[@class='box-content']//p");

	public MyAccountPage(WebDriver driver) {
		super(driver);

	}

	public String getContactInformation() {

		return getText(contactInfo);
	}

}
