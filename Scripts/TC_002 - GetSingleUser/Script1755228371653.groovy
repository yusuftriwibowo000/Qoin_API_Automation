import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import api.UsersFunctions
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

// PARAMETERS
int statusCode		= 200
String contentType	= 'application/json; charset=utf-8'
String id			= '4'


// Kirim Endpoint
def response = UsersFunctions.getSingleUser(id)

// Cetak Response
println "Response Body : "
println JsonOutput.prettyPrint(JsonOutput.toJson(new JsonSlurper().parseText(response.getResponseText())))

// Validasi Content-Type
UsersFunctions.validateContentType(response, contentType)
// Validasi Status Code
UsersFunctions.validateStatusCode(response, statusCode)
// Validasi Response Body
UsersFunctions.validateGetSingleUserBody(response, id)

println "Validasi untuk GET Single Users dengan Id : ${id} berhasil."


