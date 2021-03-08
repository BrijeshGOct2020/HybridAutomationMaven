package pages;

import org.apache.log4j.Logger;

import base.PredefinedActions;
import constantPath.ConfigFilePath;
import util.AutoLog;
import util.PropertyFileReader;

public class HomePage extends PredefinedActions{
	private PropertyFileReader prop;
	private static HomePage homePage;
	Logger log = AutoLog.getLogger();
	
	private HomePage() {
		prop = new PropertyFileReader(ConfigFilePath.HOME_PAGE_PROPERTIES);
	}
	
	public static HomePage getInstance() {
		if(homePage == null)
			homePage = new HomePage();
		return homePage;
	}
	
	public AuthenticationPage clickOnSignIn() {
		log.info("Step: Click on sign in");
		clickOnElement(prop.getValue("signInButton"),true);
		return AuthenticationPage.getInstance();
	}
}
