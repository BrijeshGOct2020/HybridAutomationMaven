package com.technocredits.automationpractice;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.AuthenticationPage;
import pages.CheckoutAddressPage;
import pages.CheckoutPaymentPage;
import pages.CheckoutShippingPage;
import pages.CheckoutSummaryPage;
import pages.HomePage;
import pages.MyProfilePage;
import pages.ProductCategoryPage;
import pages.ProductDetailsPage;
import pojo.ProductDetailsPojo;

public class E2EProductPurchaseTest extends TestBase{
	
	@Test
	public void e2ePurchase() {
		HomePage homePage = HomePage.getInstance();
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		MyProfilePage myProfilePage = authenticationPage.signIn("automation_practice@gmail.com", "automation");
		ProductCategoryPage productCategoryPage = myProfilePage.selectCategory("Women");
		List<WebElement> productList = productCategoryPage.getProductList();
		ProductDetailsPage productDetailsPage = productCategoryPage.selectFirstProduct(productList);
		productDetailsPage.setQuantity("10");
		productDetailsPage.setSize("M");
		productDetailsPage.setColor("Blue");
		productDetailsPage.clickOnAddToCart();
		
		ProductDetailsPojo productDetailsPojo = productDetailsPage.captureProductDetails();
		Assert.assertEquals(productDetailsPojo.getProductName(), productDetailsPage.verifyProductName());
		Assert.assertEquals(productDetailsPojo.getQuantity(), productDetailsPage.verifyQuantity());
		Assert.assertEquals(productDetailsPojo.getColor()+", "+productDetailsPojo.getSize(), productDetailsPage.verifySizeAndColor());
		double totalPrice = Double.parseDouble(productDetailsPojo.getPrice())*Integer.parseInt(productDetailsPojo.getQuantity());
		productDetailsPojo.setTotalBill(String.format("%.2f", totalPrice));
		Assert.assertEquals(productDetailsPojo.getTotalBill(), productDetailsPage.verifyTotalPrice());
		
		CheckoutSummaryPage checkoutSummaryPage = productDetailsPage.clickOnProceedToCheckout();
		Assert.assertEquals(productDetailsPojo.getProductName(), checkoutSummaryPage.getProductName());
		Assert.assertEquals(productDetailsPojo.getPrice(), checkoutSummaryPage.getProductPrice());
		Assert.assertEquals(productDetailsPojo.getTotalBill(), checkoutSummaryPage.getTotalPrice());
		Assert.assertEquals("Color : "+productDetailsPojo.getColor()+", Size : "+productDetailsPojo.getSize(), checkoutSummaryPage.getSizeAndColor());
		checkoutSummaryPage.captureShippingCharges(productDetailsPojo);
		checkoutSummaryPage.captureFinalPrice(productDetailsPojo);
		String expectedFinalPrice =  String.format("%.2f",Double.parseDouble(productDetailsPojo.getTotalBill())+Double.parseDouble(productDetailsPojo.getShippingCost()));
		Assert.assertEquals(expectedFinalPrice,productDetailsPojo.getFinalPrice());
		
		CheckoutAddressPage checkoutAddressPage = checkoutSummaryPage.clickOnProceedToCheckout();
		if(checkoutAddressPage.isDeliveryAndBillingAddressSame()) {
			Assert.assertEquals(checkoutAddressPage.getDeliveryAddress(), checkoutAddressPage.getBillingAddress(),"Delivery and Billing address are not same but checkbox is checked");
		}
		
		CheckoutShippingPage checkoutShippingPage = checkoutAddressPage.clickOnProceedToCheckout();
		Assert.assertEquals(productDetailsPojo.getShippingCost(),checkoutShippingPage.getShippingCharge(),"Shipping page - Mismatch in shipping charge");
		checkoutShippingPage.agreeTermsAndConditions();
		
		CheckoutPaymentPage checkoutPaymentPage = checkoutShippingPage.clickOnProceedToCheckout();
		checkoutPaymentPage.clickOnPayByBankWire();
		checkoutPaymentPage.clickOnConfirmOrderButton();
		Assert.assertEquals("Your order on My Store is complete.",checkoutPaymentPage.getOrderCompleteConfirmationText());
	}
}
