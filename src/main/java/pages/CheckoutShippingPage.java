package pages;

import org.apache.log4j.Logger;

import base.PredefinedActions;
import constantPath.ConfigFilePath;
import util.AutoLog;
import util.PropertyFileReader;

public class CheckoutShippingPage extends PredefinedActions{
	private PropertyFileReader prop;
	private static CheckoutShippingPage checkoutShippingPage;
	Logger log = AutoLog.getLogger();
	
	private CheckoutShippingPage() {
		prop = new PropertyFileReader(ConfigFilePath.CHECKOUTSHIPPING_PAGE_PROPERTIES);
	}
	
	public static CheckoutShippingPage getInstance() {
		if(checkoutShippingPage == null)
			checkoutShippingPage = new CheckoutShippingPage();
		return checkoutShippingPage;
	}
	
	public String getShippingCharge() {
		log.info("Step: Get Shipping charge");
		return getElementText(prop.getValue("shippingCharges"), true).substring(1);
	}
	
	public void agreeTermsAndConditions() {
		log.info("Step: Agree terms & conditions");
		clickOnElement(prop.getValue("agreeTermsAndConditions"), false);
	}
	
	public CheckoutPaymentPage clickOnProceedToCheckout() {
		log.info("Step: CheckoutShippingPage - Click on 'proceed to checkout' button");
		clickOnElement(prop.getValue("proceedToCheckoutButton"), true);
		return CheckoutPaymentPage.getInstance();
	}
}
