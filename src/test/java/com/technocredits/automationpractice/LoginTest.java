package com.technocredits.automationpractice;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AuthenticationPage;
import pages.HomePage;
import pages.MyProfilePage;
import util.ExcelOperation;

public class LoginTest extends TestBase{
	
	@Test(dataProvider = "SignIn Data", enabled = false)
	public void signIn(String email, String password, String firstName, String lastName) {
		HomePage homePage = HomePage.getInstance();
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		MyProfilePage myProfilePage = authenticationPage.signIn(email, password);
		String text = myProfilePage.getHeaderText();
		Assert.assertTrue(text.contains(firstName+" "+lastName),"Login Failed");
	}
	
	@DataProvider(name = "SignIn Data")
	public String[][] signInData() throws IOException {
		return ExcelOperation.getExcelData("TestData.xlsx", "Login");
	}
	
	@Test
	public void invalidEmailSignIn() {
		HomePage homePage = HomePage.getInstance();
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		authenticationPage.enterEmailSignIn("ajshajsbb@gmail.com");
		authenticationPage.enterPassword("automation");
		authenticationPage.clickOnSignInButton();
		Assert.assertEquals("Authentication failed.", authenticationPage.getAlertErrorText());
	}
	
	@Test
	public void invalidPasswordSignIn() {
		HomePage homePage = HomePage.getInstance();
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		authenticationPage.enterEmailSignIn("automation_practice@gmail.com");
		authenticationPage.enterPassword("automation123");
		authenticationPage.clickOnSignInButton();
		Assert.assertEquals("Authentication failed.", authenticationPage.getAlertErrorText());
	}
	
	@Test
	public void blankEmailSignIn() {
		HomePage homePage = HomePage.getInstance();
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		authenticationPage.enterPassword("automation");
		authenticationPage.clickOnSignInButton();
		Assert.assertEquals("An email address required.", authenticationPage.getAlertErrorText());
	}
	
	@Test
	public void blankPasswordSignIn() {
		HomePage homePage = HomePage.getInstance();
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		authenticationPage.enterEmailSignIn("automation_practice@gmail.com");
		authenticationPage.clickOnSignInButton();
		Assert.assertEquals("Password is required.", authenticationPage.getAlertErrorText());
	}
	
	@Test
	public void blankEmailAndPasswordSignIn() {
		HomePage homePage = HomePage.getInstance();
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		authenticationPage.clickOnSignInButton();
		Assert.assertEquals("An email address required.", authenticationPage.getAlertErrorText());
	}
	
	@Test
	public void invalidEmailAndPasswordSignIn() {
		HomePage homePage = HomePage.getInstance();
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		authenticationPage.enterEmailSignIn("automation_practice123@gmail.com");
		authenticationPage.enterPassword("automation123");
		authenticationPage.clickOnSignInButton();
		Assert.assertEquals("Authentication failed.", authenticationPage.getAlertErrorText());
	}
}
