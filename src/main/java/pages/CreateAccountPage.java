package pages;

import org.apache.log4j.Logger;
import org.testng.Assert;

import base.PredefinedActions;
import constantPath.ConfigFilePath;
import pojo.CreateAccountDetailsPojo;
import util.AutoLog;
import util.PropertyFileReader;

public class CreateAccountPage extends PredefinedActions{
	
	private PropertyFileReader prop;
	private static CreateAccountPage createAccountPage;
	Logger log = AutoLog.getLogger();
	
	private CreateAccountPage() {
		prop = new PropertyFileReader(ConfigFilePath.CREATEACCOUNT_PAGE_PROPERTIES);
	}
	
	public static CreateAccountPage getInstance() {
		if(createAccountPage == null)
			createAccountPage = new CreateAccountPage();
		return createAccountPage;
	}
	
	public void validatePageHeading() {
		log.info("Step: Validate CreateAccount page title");
		Assert.assertTrue(isElementDisplayed(prop.getValue("pageTitle"), true));
	}
	
	private void selectTitle(boolean isMale) {
		log.info("Step: Select title");
		if(isMale)
			clickOnRadioButton(prop.getValue("genderMale"));
		else
			clickOnRadioButton(prop.getValue("genderFemale"));
	}
	
	private void enterFirstName(String firstName) {
		log.info("Step: Enter First Name");
		enterText(prop.getValue("firstName"), firstName, true);
	}
	
	private void enterLastName(String lastName) {
		log.info("Step: Enter Last Name");
		enterText(prop.getValue("lastName"), lastName, true);
	}
	
	private void enterPassword(String password) {
		log.info("Step: Enter Password");
		enterText(prop.getValue("password"), password, true);
	}
	
	private void selectDay(String day) {
		log.info("Step: Select day");
		clickOnElement(prop.getValue("dayDropdown"), true);
		selectValueFromDropdownByValue(prop.getValue("days"), day, false);
	}
	
	private void selectMonth(String month) {
		log.info("Step: Select month");
		clickOnElement(prop.getValue("monthDropdown"), true);
		selectValueFromDropdownByValue(prop.getValue("months"), month, false);
	}
	
	private void selectYear(String year) {
		log.info("Step: Select year");
		clickOnElement(prop.getValue("yearDropdown"), true);
		selectValueFromDropdownByValue(prop.getValue("years"), year, false);
	}
	
	private void enterCompanyName(String company) {
		log.info("Step: Enter Company Name");
		enterText(prop.getValue("company"), company, false);
	}
	
	private void enterAddress(String address) {
		log.info("Step: Enter Address Name");
		enterText(prop.getValue("address"), address, false);
	}
	
	private void enterCity(String city) {
		log.info("Step: Enter City Name");
		enterText(prop.getValue("city"), city, false);
	}
	
	private void selectState(String state) {
		log.info("Step: Select State");
		clickOnElement(prop.getValue("stateDropdown"), false);
		selectValueFromDropdownByVisibleText(prop.getValue("state"), state, false);
	}
	
	private void enterPostCode(String postCode) {
		log.info("Step: Enter Postcode");
		enterText(prop.getValue("postCode"), postCode, false);
	}
	
	private void additionalInfo(String additionalInfo) {
		log.info("Step: Enter additional information");
		enterText(prop.getValue("additionalInfo"), additionalInfo, false);
	}
	
	private void enterHomePhoneNumber(String hPhone) {
		log.info("Step: Enter Home mobile number");
		enterText(prop.getValue("homePhone"), hPhone, false);
	}
	
	private void enterMobilePhone(String mNumber) {
		log.info("Step: Enter Mobile number");
		enterText(prop.getValue("mobileNumber"), mNumber, false);
	}
	
	public void enterCreateAccountDetails(CreateAccountDetailsPojo createAccountDetailsPojo) {
		selectTitle(createAccountDetailsPojo.isMale());
		enterFirstName(createAccountDetailsPojo.getFirstName());
		enterLastName(createAccountDetailsPojo.getLastName());
		enterPassword(createAccountDetailsPojo.getPassword());
		selectDay(createAccountDetailsPojo.getDay());
		selectMonth(createAccountDetailsPojo.getMonth());
		selectYear(createAccountDetailsPojo.getYear());
		enterCompanyName(createAccountDetailsPojo.getCompany());
		enterAddress(createAccountDetailsPojo.getAddress1());
		enterCity(createAccountDetailsPojo.getCity());
		selectState(createAccountDetailsPojo.getState());
		enterPostCode(createAccountDetailsPojo.getPostCode());
		enterHomePhoneNumber(createAccountDetailsPojo.gethPhone());
		enterMobilePhone(createAccountDetailsPojo.getmNumber());
	}
	
	public void validatePersonalInformationPageHeader() {
		log.info("Validate header 'Your personal information'");
		Assert.assertTrue(isElementDisplayed(prop.getValue("personalInformationPageHeader"), true));
	}
	
	public MyProfilePage clickOnRegistration() {
		clickOnElement(prop.getValue("registrationButton"), false);
		log.info("Details Registered in Application");
		return MyProfilePage.getInstance();
	}
}
