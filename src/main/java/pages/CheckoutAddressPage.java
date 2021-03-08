package pages;

import java.util.List;

import org.apache.log4j.Logger;

import base.PredefinedActions;
import constantPath.ConfigFilePath;
import util.AutoLog;
import util.PropertyFileReader;

public class CheckoutAddressPage extends PredefinedActions {
	private PropertyFileReader prop;
	Logger log = AutoLog.getLogger();
	private static CheckoutAddressPage checkoutAddressPage;
	
	private CheckoutAddressPage() {
		prop = new PropertyFileReader(ConfigFilePath.CHECKOUTADDRESS_PAGE_PROPERTIES);
	}
	
	public static CheckoutAddressPage getInstance() {
		if(checkoutAddressPage == null)
			checkoutAddressPage = new CheckoutAddressPage();
		return checkoutAddressPage;
	}
	
	public boolean isDeliveryAndBillingAddressSame() {
		log.info("Step: check if checkbox checked");
		return isElementSelected(prop.getValue("addressCheckbox"), false);
	}

	public List<String> getDeliveryAddress() {
		log.info("Step: Get delivery address");
		return getElementsText(prop.getValue("deliveryAddress"), false);
	}

	public List<String> getBillingAddress() {
		log.info("Step: Get billing address");
		return getElementsText(prop.getValue("billingAddress"), false);
	}
	
	public CheckoutShippingPage clickOnProceedToCheckout() {
		log.info("Step: CheckoutAddressPage - Click on 'proceed to checkout' button");
		clickOnElement(prop.getValue("proceedToCheckoutButton"), true);
		return CheckoutShippingPage.getInstance();
	}
}
