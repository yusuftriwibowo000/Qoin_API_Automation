package api

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class Global {
	
	static void validateContentType(ResponseObject response, String expectedContentType) {
		String actual = response.getHeaderFields()['Content-Type'][0]
		if (actual == expectedContentType) {
			println "Content-Type Sudah Sesuai : " + actual
		} else {
			println "Content-Type Tidak Sesuai! Expected : " + expectedContentType + ", Actual : " + actual
		}
	}
	
	static void validateStatusCode(ResponseObject response, int expectedStatusCode) {
		int actual = response.getStatusCode()
		WS.verifyResponseStatusCode(response, expectedStatusCode, FailureHandling.CONTINUE_ON_FAILURE)
		if (actual == expectedStatusCode) {
			println "Status Code Sudah Sesuai : " + actual
		} else {
			println "Status Code Tidak Sesuai! Expected : " + expectedStatusCode + ", Actual : " + actual
		}
	}
	
	
	
	
}
