package pages;

import org.apache.log4j.Logger;

import base.PredefinedActions;
import constantPath.ConfigFilePath;
import util.AutoLog;
import util.PropertyFileReader;

public class MyProfilePage extends PredefinedActions {
	
	private PropertyFileReader prop;
	private static MyProfilePage myProfilePage;
	Logger log = AutoLog.getLogger();
	
	private MyProfilePage() {
		prop = new PropertyFileReader(ConfigFilePath.MYPROFILE_PAGE_PROPERTIES);
	}
	
	public static MyProfilePage getInstance() {
		if(myProfilePage == null)
			myProfilePage = new MyProfilePage();
		return myProfilePage;
	}
	
	public String getHeaderText() {
		return getElementText(prop.getValue("headerText"), true);
	}
	
	public ProductCategoryPage selectCategory(String category) {
		log.info("Step: Click on category '"+category+"'");
		clickOnElement("[xpath]:-//ul/li/a[text()='"+category+"']", true);
		return ProductCategoryPage.getInstance();
	}
}
