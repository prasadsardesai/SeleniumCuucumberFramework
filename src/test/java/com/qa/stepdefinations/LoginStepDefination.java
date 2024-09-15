package com.qa.stepdefinations;


import com.qa.factory.DriverFactory;
import com.qa.pages.LoginPage;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginStepDefination {

	LoginPage objLoginPage = new LoginPage(DriverFactory.getDriver());

	@Given("user is on the Software TestingBoard login page")
	public void user_is_on_the_orange_hrm_login_page() {
		objLoginPage.launchApp();
		System.out.println("Software TestingBoard Page is launched..");
	}

	@When("user enters username as {string}")
	public void user_enters_username_as(String userName) {
		objLoginPage.enterUsername(userName);
		System.out.println("UserName is entered..");
	}

	@When("user enters password as {string}")
	public void user_enters_password_as(String passWord) {
		objLoginPage.enterPassword(passWord);
		System.out.println("Password is entered..");
	}

	@When("user clicks on the login button")
	public void user_clicks_on_the_login_button() {

		objLoginPage.clickLoginButton();
	}

	@Then("the page title of homepage should be {string}")
	public void the_page_title_should_be(String expTitle) {
		String actTitle = objLoginPage.getTitleOfHomePage();
		assertEquals(actTitle, expTitle, "Actual Title is not matching with the Expected title..!");
		
		System.out.println("Title of the HomePage is : " + actTitle);

	}

	@Then("verify it shows appropriate warning message {string}")
	public void verify_it_shows_appropriate_warning_message(String expWarningMsg) {
		String actWarningMsg = objLoginPage.getWarningMessage();
		System.out
				.println("Expected Warning Message : " + expWarningMsg + " Actual Warning Message : " + actWarningMsg);

		assertEquals(actWarningMsg, expWarningMsg,
				"Actual Warning Message is not matching with the Expected Warning Message..!");

	}

}
