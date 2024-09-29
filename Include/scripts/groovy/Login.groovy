import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import com.kms.katalon.core.configuration.RunConfiguration



class Login {
	TestObject createTestObject(String xpath, String id) {
		TestObject testObject = new TestObject()
		// Menambahkan properti ID
		testObject.addProperty("id", com.kms.katalon.core.testobject.ConditionType.EQUALS, id)
		// Menambahkan properti XPath
		testObject.addProperty("xpath", com.kms.katalon.core.testobject.ConditionType.EQUALS, xpath)
		return testObject
	}

	
	@Given("User is on the login page")
	def navigateToLoginPage() {
		RunConfiguration.setWebDriverPreferencesProperty("args", [
			"--windows-size=1280,1024",
			"--incognito"
		])
		WebUI.openBrowser('')
		WebUI.maximizeWindow()
		WebUI.navigateToUrl(GlobalVariable.url)
	}

	@When("User input username (.*) and password (.*)")
	def enterCredentials(String username, String password) {
		WebUI.waitForElementVisible(new TestObject().addProperty('id', ConditionType.EQUALS, 'user-name'), 1)
		
		'Input username'
		WebUI.setText(new TestObject().addProperty('id', ConditionType.EQUALS, 'user-name'), username)
		
		'Input password'
		WebUI.setText(new TestObject().addProperty('id', ConditionType.EQUALS, 'password'), password)
		
		'Klick login button'
		WebUI.click(new TestObject().addProperty('id', ConditionType.EQUALS, 'login-button'))
		
	}

	@Then("User successfully logged in")
	def successfullyLogin() {
		WebUI.waitForElementVisible(new TestObject().addProperty('id', ConditionType.EQUALS, 'react-burger-menu-btn'), 1)
		WebUI.verifyElementText(new TestObject().addProperty('xpath', ConditionType.EQUALS, "//div[@class='app_logo']"), "Swag Labs")
	}
}