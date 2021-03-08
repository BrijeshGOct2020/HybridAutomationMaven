package com.technocredits.automationpractice;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import base.PredefinedActions;
import listener.TestLogger;
import util.AutoLog;

public class TestBase implements TestLogger{
	private List<String> messages = new ArrayList<>();
	
	@BeforeClass
	public void beforeClass() {
		AutoLog.init(this.getClass(), this);
	}
	
	@BeforeMethod
	@Parameters("browser")
	public void setUp(String browser, Method method) {
		System.out.println(method.getName());
		AutoLog.getLogger().info("========================== Start Test: " + method.getName()+" ==================================");
		PredefinedActions.start(browser);  
	}

	@AfterMethod
	public void tearDown(ITestResult iTestResult) {
		if(iTestResult.getStatus() == ITestResult.FAILURE) {
			PredefinedActions.captureScreenshot(iTestResult.getName());
			AutoLog.getLogger().info("Screenshot captured for:"+iTestResult.getName());
		}
		PredefinedActions.quit();
		AutoLog.getLogger().info("========================== End Test: " + iTestResult.getName()+" ==================================");
	}

	@Override
	public List<String> getMessages() {
		return messages;
	}

	@Override
	public void addMessage(String message) {
		messages.add(message);
	}
}
