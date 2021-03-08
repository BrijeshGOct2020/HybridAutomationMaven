package com.technocredits.automationpractice;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AuthenticationPage;
import pages.CreateAccountPage;
import pages.HomePage;
import pages.MyProfilePage;
import pojo.CreateAccountDetailsPojo;
import util.ExcelOperation;
import util.TimeUtil;

public class CreateAccountTest extends TestBase{

	@Test(enabled=false)
	public void createAccountTest() {
		HomePage homePage = HomePage.getInstance();
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		authenticationPage.validatePageHeading();
		authenticationPage.enterEmailAdress("automation19783849@gmail.com");
		CreateAccountPage createAccountPage = authenticationPage.clickOnCreateAccount();
		createAccountPage.validatePageHeading();

		System.out.println("Navigate to create account page");
		CreateAccountDetailsPojo createAccountDetailsPojo = new CreateAccountDetailsPojo();
		createAccountDetailsPojo.setMale(true);
		createAccountDetailsPojo.setFirstName("Test");
		createAccountDetailsPojo.setLastName("User");
		createAccountDetailsPojo.setPassword("Avbdggh@567");
		createAccountDetailsPojo.setDay("12");
		createAccountDetailsPojo.setMonth("1");
		createAccountDetailsPojo.setYear("1997");
		createAccountDetailsPojo.setCompany("PTC");
		createAccountDetailsPojo.setAddress1("650 Grassmere park");
		createAccountDetailsPojo.setCity("nashville");
		createAccountDetailsPojo.setState("Maine");
		createAccountDetailsPojo.setPostCode("37211");
		createAccountDetailsPojo.setAdditionalInfo("NA");
		createAccountDetailsPojo.sethPhone("1234567890");
		createAccountDetailsPojo.setmNumber("9876543210");
		createAccountDetailsPojo.setAliasAddress("");
		createAccountPage.enterCreateAccountDetails(createAccountDetailsPojo);

		MyProfilePage myProfilePage = createAccountPage.clickOnRegistration();
		String actual = myProfilePage.getHeaderText();
		String expected = "Test User";
		Assert.assertEquals(actual, expected, "Verification of headertext failed");
	}

	@Test(dataProvider = "Create Account", enabled=false)
	public void createAccountTestDynamicData(String email, String gender, String firstName, String lastName,
			String password, String day, String month, String year, String company, String address, String city,
			String state, String postCode, String additionalInfo, String hPhone, String mNumber,
			String aliasAddress) {
		HomePage homePage = HomePage.getInstance();
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		authenticationPage.validatePageHeading();
		authenticationPage.enterEmailAdress(email);
		CreateAccountPage createAccountPage = authenticationPage.clickOnCreateAccount();
		createAccountPage.validatePageHeading();

		System.out.println("Navigate to create account page");
		CreateAccountDetailsPojo createAccountDetailsPojo = new CreateAccountDetailsPojo();
		boolean genderFlag = gender.equalsIgnoreCase("m") ? true : false;
		createAccountDetailsPojo.setMale(genderFlag);
		createAccountDetailsPojo.setFirstName(firstName);
		createAccountDetailsPojo.setLastName(lastName);
		createAccountDetailsPojo.setPassword(password);
		createAccountDetailsPojo.setDay(day);
		createAccountDetailsPojo.setMonth(month);
		createAccountDetailsPojo.setYear(year);
		createAccountDetailsPojo.setCompany(company);
		createAccountDetailsPojo.setAddress1(address);
		createAccountDetailsPojo.setCity(city);
		createAccountDetailsPojo.setState(state);
		createAccountDetailsPojo.setPostCode(postCode);
		createAccountDetailsPojo.setAdditionalInfo(additionalInfo);
		createAccountDetailsPojo.sethPhone(hPhone);
		createAccountDetailsPojo.setmNumber(mNumber);
		createAccountDetailsPojo.setAliasAddress(aliasAddress);
		createAccountPage.enterCreateAccountDetails(createAccountDetailsPojo);

		MyProfilePage myProfilePage = createAccountPage.clickOnRegistration();
		String actual = myProfilePage.getHeaderText();
		String expected = firstName+" "+lastName;
		Assert.assertEquals(actual, expected, "Verification of headertext failed");
	}
	
	@Test(dataProvider = "Create Account Pojo")
	public void createAccountTestDynamicDataPojo(CreateAccountDetailsPojo createAccountDetailsPojo) {
		HomePage homePage = HomePage.getInstance();
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		authenticationPage.validatePageHeading();
		authenticationPage.enterEmailAdress(createAccountDetailsPojo.getEmail());
		CreateAccountPage createAccountPage = authenticationPage.clickOnCreateAccount();
		createAccountPage.validatePageHeading();

		System.out.println("Navigate to create account page");
		createAccountPage.validatePersonalInformationPageHeader();
		createAccountPage.enterCreateAccountDetails(createAccountDetailsPojo);

		MyProfilePage myProfilePage = createAccountPage.clickOnRegistration();
		String actual = myProfilePage.getHeaderText();
		String expected = createAccountDetailsPojo.getFirstName()+" "+createAccountDetailsPojo.getLastName();
		Assert.assertEquals(actual, expected, "Verification of headertext failed");
	}


	@DataProvider(name = "Create Account")
	public String[][] createAccount() throws IOException {
		return ExcelOperation.getExcelData("TestData.xlsx", "CreateAccount");
	}
	
	@DataProvider(name = "Create Account Pojo")
	public Object[][] createAccountPojo() throws IOException {
		String[][] data = ExcelOperation.getExcelData("TestData.xlsx", "CreateAccount");
		Object[][] obj = new Object[data.length][1];
		for(int index = 0; index < data.length; index++) {
			String[] row = data[index];
			CreateAccountDetailsPojo  createAccountDetailsPojo = new CreateAccountDetailsPojo();
			createAccountDetailsPojo.setEmail(row[0]+TimeUtil.getTimeStamp());
			boolean genderFlag = row[1].equalsIgnoreCase("m") ? true : false;
			createAccountDetailsPojo.setMale(genderFlag);
			createAccountDetailsPojo.setFirstName(row[2]);
			createAccountDetailsPojo.setLastName(row[3]);
			createAccountDetailsPojo.setPassword(row[4]);
			createAccountDetailsPojo.setDay(row[5]);
			createAccountDetailsPojo.setMonth(row[6]);
			createAccountDetailsPojo.setYear(row[7]);
			createAccountDetailsPojo.setCompany(row[8]);
			createAccountDetailsPojo.setAddress1(row[9]);
			createAccountDetailsPojo.setCity(row[10]);
			createAccountDetailsPojo.setState(row[11]);
			createAccountDetailsPojo.setPostCode(row[12]);
			createAccountDetailsPojo.setAdditionalInfo(row[13]);
			createAccountDetailsPojo.sethPhone(row[14]);
			createAccountDetailsPojo.setmNumber(row[15]);
			createAccountDetailsPojo.setAliasAddress(row[16]);
			obj[index][0] = createAccountDetailsPojo;
		}
		return obj;
	}
}
