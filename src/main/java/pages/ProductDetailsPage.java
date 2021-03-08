package pages;

import org.apache.log4j.Logger;

import base.PredefinedActions;
import constantPath.ConfigFilePath;
import pojo.ProductDetailsPojo;
import util.AutoLog;
import util.PropertyFileReader;

public class ProductDetailsPage extends PredefinedActions{

	private PropertyFileReader prop;
	private static ProductDetailsPage productDetailsPage;
	Logger log = AutoLog.getLogger();
	
	private ProductDetailsPage() {
		prop = new PropertyFileReader(ConfigFilePath.PRODUCTDETAILS_PAGE_PROPERTIES);
	}
	
	public static ProductDetailsPage getInstance() {
		if(productDetailsPage == null)
			productDetailsPage = new ProductDetailsPage();
		return productDetailsPage;
	}
	
	public ProductDetailsPojo captureProductDetails() {
		ProductDetailsPojo productDetailsPojo = new ProductDetailsPojo();
		String productName = getElementText(prop.getValue("productName"), true);
		String price = getElementText(prop.getValue("displayPrice"), false).substring(1);
		String quantity = getAttribute(prop.getValue("quantity"), "value", false);
		String size = getElementText(prop.getValue("size"), false);
		String productDescription = getElementText(prop.getValue("productDescription"), false);
		String color = getAttribute(prop.getValue("color"), "title", false);
		
		productDetailsPojo.setProductName(productName);
		productDetailsPojo.setPrice(price);
		productDetailsPojo.setQuantity(quantity);
		productDetailsPojo.setProductDescription(productDescription);
		productDetailsPojo.setSize(size);
		productDetailsPojo.setColor(color);
		return productDetailsPojo;
	}
	
	public void setQuantity(String quantity) {
		log.info("Step: set quantity");
		enterText(prop.getValue("quantity"), quantity, true);
	}
	
	public void setSize(String size) {
		log.info("Step: set size");
		selectValueFromDropdownByVisibleText(prop.getValue("setSize"), size, false);
	}
	
	public void setColor(String color) {
		log.info("Step: set color");
		clickOnElement("[xpath]:-//a[@title='"+color+"']", true);
	}
	
	public void clickOnAddToCart() {
		log.info("Step: click on add to cart");
		clickOnElement(prop.getValue("addToCart"), true);
	}
	
	public String verifyProductName() {
		log.info("Step: verify product name");
		return getElementText(prop.getValue("verifyProductName"), true);
	}
	
	public String verifyQuantity() {
		log.info("Step: verify quantity");
		return getElementText(prop.getValue("verifyProductQuantity"), true);
	}
	
	public String verifySizeAndColor() {
		log.info("Step: verify size and color");
		return getElementText(prop.getValue("verifyProductAttributes"), true);
	}
	
	public String verifyTotalPrice() {
		log.info("Step: verify total price");
		return getElementText(prop.getValue("verifyTotalPrice"), true).substring(1);
	}
	
	public CheckoutSummaryPage clickOnProceedToCheckout() {
		log.info("Step: Product Details page - click on 'proceed to checkout'");
		clickOnElement(prop.getValue("proceedToCheckoutButton"), true);
		return CheckoutSummaryPage.getInstance();
	}
	
	
}
