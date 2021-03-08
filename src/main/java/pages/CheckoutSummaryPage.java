package pages;

import org.apache.log4j.Logger;

import base.PredefinedActions;
import constantPath.ConfigFilePath;
import pojo.ProductDetailsPojo;
import util.AutoLog;
import util.PropertyFileReader;

public class CheckoutSummaryPage extends PredefinedActions{

	private PropertyFileReader prop;
	private static CheckoutSummaryPage checkoutSummaryPage;
	Logger log = AutoLog.getLogger();
	
	private CheckoutSummaryPage() {
		prop = new PropertyFileReader(ConfigFilePath.CHECKOUTSUMMARY_PAGE_PROPERTIES);
	}
	
	public static CheckoutSummaryPage getInstance() {
		if(checkoutSummaryPage == null)
			checkoutSummaryPage = new CheckoutSummaryPage();
		return checkoutSummaryPage;
	}
	
	public String getProductName() {
		log.info("Step: Get product name");
		return getElementText(prop.getValue("productName"), true);
	}

	public String getProductPrice() {
		log.info("Step: Get product price");
		return getElementText(prop.getValue("productPrice"), true).substring(1);
	}

	public String getTotalPrice() {
		log.info("Step: Get total price");
		return getElementText(prop.getValue("totalPrice"), true).substring(1);
	}
	
	public String getSizeAndColor() {
		log.info("Step: Get size and color");
		return getElementText(prop.getValue("sizeAndColor"), true);
	}
	
	public CheckoutAddressPage clickOnProceedToCheckout() {
		log.info("Step: Summary Page - Click on 'proceed to checkout'");
		clickOnElement(prop.getValue("proceedToCheckoutButton"), true);
		return CheckoutAddressPage.getInstance();
	}
	
	public void captureShippingCharges(ProductDetailsPojo productDetailsPojo) {
		String shippingCost = getElementText(prop.getValue("totalShipping"), true).substring(1);
		productDetailsPojo.setShippingCost(shippingCost);
	}
	
	public void captureFinalPrice(ProductDetailsPojo productDetailsPojo) {
		String finalPrice = getElementText(prop.getValue("totalPriceIncludingShipping"), true).substring(1);
		productDetailsPojo.setFinalPrice(finalPrice);
	}
}
