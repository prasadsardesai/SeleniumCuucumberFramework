package com.qa.stepdefinations;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.qa.factory.DriverFactory;
import com.qa.pages.MyAccountPage;

import io.cucumber.java.en.When;

public class MyAccountStepDefination {

	MyAccountPage objMyAccountPage = new MyAccountPage(DriverFactory.getDriver());

	@When("user verify username as {string} and emailID as {string} under Contact Information")
	public void user_select_tab_from_main_menu(String userName, String emailId) {

		String actualInfo = objMyAccountPage.getContactInformation();

		assertTrue(actualInfo.contains(userName));

		assertTrue(actualInfo.contains(emailId));

	}

}
