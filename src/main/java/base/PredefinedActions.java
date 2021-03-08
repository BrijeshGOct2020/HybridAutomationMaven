package base;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import constantPath.ConfigFilePath;
import exceptionHandling.InvalidLocatorStrategy;
import util.AutoLog;
import util.TimeUtil;

public class PredefinedActions {
	protected static WebDriver driver;
	private static WebDriverWait wait;
	static Logger log = AutoLog.getLogger();
	public static void start(String browser) {
		switch (browser.toUpperCase()) {
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", ConfigFilePath.WINDOWS_CHROME);
			driver = new ChromeDriver();
			break;
		case "FIREFOX":
			System.setProperty("webdriver.gecko.driver",ConfigFilePath.WINDOWS_FIREFOX); 
			driver = new FirefoxDriver();
			break;
		case "EDGE":
			System.setProperty("webdriver.edge.driver", ConfigFilePath.WINDOWS_EDGE);
			driver = new EdgeDriver();
			break;
		default:
			System.setProperty("webdriver.chrome.driver", ConfigFilePath.WINDOWS_CHROME);
			driver = new ChromeDriver();
			break;
		}
		wait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");
		log.info("Navigate to Application");
	}

	public static void quit() {
		driver.quit();
	}

	private String getLocatorType(String locator) {
		return locator.split("]:-")[0].substring(1);
	}

	private String getLocatorValue(String locator) {
		return locator.split("]:-")[1];
	}

	protected WebElement getElement(String locator, boolean isWaitRequired) {
		String locatorType = getLocatorType(locator);
		String locatorValue = getLocatorValue(locator);
		WebElement element = null;
		switch (locatorType.toUpperCase()) {
		case "CSS":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
			else
				element = driver.findElement(By.cssSelector(locatorValue));
			break;
		case "XPATH":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			else
				element = driver.findElement(By.xpath(locatorValue));
			break;
		case "ID":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
			else
				element = driver.findElement(By.id(locatorValue));
			break;
		case "CLASS":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
			else
				element = driver.findElement(By.className(locatorValue));
			break;
		case "TAG":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
			else
				element = driver.findElement(By.tagName(locatorValue));
			break;
		case "NAME":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
			else
				element = driver.findElement(By.name(locatorValue));
			break;
		case "LINK":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
			else
				element = driver.findElement(By.linkText(locatorValue));
			break;
		case "PARTIALLINK":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
			else
				element = driver.findElement(By.partialLinkText(locatorValue));
			break;
		default:
			throw new InvalidLocatorStrategy(
					"Please use valid locator strategy from below\nclass, id, link, partialLink, name, tag, xpath, css");
		}
		return element;
	}

	protected List<WebElement> getElements(String locator, boolean isWaitRequired) {
		String locatorType = getLocatorType(locator);
		String locatorValue = getLocatorValue(locator);
		List<WebElement> elements = null;
		switch (locatorType.toUpperCase()) {
		case "CSS":
			if (isWaitRequired)
				elements = wait
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(locatorValue)));
			else
				elements = driver.findElements(By.cssSelector(locatorValue));
			break;
		case "XPATH":
			if (isWaitRequired)
				elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locatorValue)));
			else
				elements = driver.findElements(By.xpath(locatorValue));
			break;
		case "ID":
			if (isWaitRequired)
				elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(locatorValue)));
			else
				elements = driver.findElements(By.id(locatorValue));
			break;
		case "CLASS":
			if (isWaitRequired)
				elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(locatorValue)));
			else
				elements = driver.findElements(By.className(locatorValue));
			break;
		case "TAG":
			if (isWaitRequired)
				elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName(locatorValue)));
			else
				elements = driver.findElements(By.tagName(locatorValue));
			break;
		case "NAME":
			if (isWaitRequired)
				elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(locatorValue)));
			else
				elements = driver.findElements(By.name(locatorValue));
			break;
		case "LINK":
			if (isWaitRequired)
				elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText(locatorValue)));
			else
				elements = driver.findElements(By.linkText(locatorValue));
			break;
		case "PARTIALLINK":
			if (isWaitRequired)
				elements = wait
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.partialLinkText(locatorValue)));
			else
				elements = driver.findElements(By.partialLinkText(locatorValue));
			break;
		default:
			throw new InvalidLocatorStrategy(
					"Please use valid locator strategy from below\nclass, id, link, partialLink, name, tag, xpath, css");
		}
		return elements;
	}

	protected void clickOnElement(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	protected void clickOnElement(String locator, boolean isWaitRequired) {
		WebElement element = getElement(locator, isWaitRequired);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	protected void clickOnRadioButton(String locator) {
		WebElement element = getElement(locator, false);
		element.click();
	}
	
	protected void clearText(WebElement element) {
		element.clear();
	}

	protected void scrollToElement(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true)", element);
	}

	protected void selectValueFromDropdownByVisibleText(String locator, String value, boolean isWaitRequired) {
		WebElement element = getElement(locator, isWaitRequired);
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	protected void selectValueFromDropdownByValue(String locator, String value, boolean isWaitRequired) {
		WebElement element = getElement(locator, isWaitRequired);
		Select select = new Select(element);
		select.selectByValue(value);
	}

	protected boolean isElementDisplayed(String locator, boolean isWaitRequired) {
		WebElement element = getElement(locator, isWaitRequired);
		if (!element.isDisplayed())
			scrollToElement(element);
		return element.isDisplayed();
	}

	protected List<String> getElementsText(String locator, boolean isWaitRequired) {
		List<WebElement> elements = getElements(locator, isWaitRequired);
		List<String> textList = new ArrayList<String>();
		for (WebElement element : elements) {
			textList.add(element.getText().trim());
		}
		return textList;
	}

	protected boolean isElementSelected(String locator, boolean isWaitRequired) {
		WebElement element = getElement(locator, isWaitRequired);
		return element.isSelected();
	}

	protected String getElementText(String locator, boolean isWaitRequired) {
		WebElement element = getElement(locator, isWaitRequired);
		return element.getText();
	}

	protected void enterText(String locator, String text, boolean isWaitRequired) {
		WebElement element = getElement(locator, isWaitRequired);
		if (element.isEnabled()) {
			clearText(element);
			element.sendKeys(text);
		} else
			throw new ElementNotInteractableException("Locator:" + locator + ", Element is not enabled");
	}

	protected String getAttribute(String locator, String attribute, boolean isWaitRequired) {
		WebElement element = getElement(locator, isWaitRequired);
		return element.getAttribute(attribute);
	}

	protected void enterText(WebElement element, String text) {
		if (element.isEnabled())
			element.sendKeys(text);
		else
			throw new ElementNotInteractableException("Element is not enabled");
	}

	public static void captureScreenshot(String name) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File file = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File("./Screenshots/" + name + "_" + TimeUtil.getTimeStamp() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
