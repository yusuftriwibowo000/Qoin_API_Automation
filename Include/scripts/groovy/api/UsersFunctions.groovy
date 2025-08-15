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

import groovy.json.JsonSlurper
import internal.GlobalVariable

public class UsersFunctions extends Global{
	
	// ================================= FUNGSI REGISTER USER =======================================

	// Fungsi Post Register User
	static ResponseObject postRegisterUser(String email, String password) {
		return WS.sendRequest(findTestObject('Object Repository/ReqresIn/postRegisterSuccessful', [
			('email') : email,
			('password') : password
		]))
	}

	// Fungsi Get Email dari Single User
	static String getEmailFromSingleUser(String id) {
		ResponseObject responseGet = WS.sendRequest(findTestObject('Object Repository/ReqresIn/getSingleUser', [('id') : id]))
		return WS.getElementPropertyValue(responseGet, 'data.email')
	}

	// Fungsi Validasi Body Register Successful
	static void validateRegisterSuccessfulBody(ResponseObject response) {
		def jsonResponse = new JsonSlurper().parseText(response.getResponseText())
		assert jsonResponse.containsKey("id")
		assert jsonResponse.containsKey("token")

		if (!(jsonResponse.id instanceof Integer)) {
			println "Id bukan Integer : " + jsonResponse.id
		}
		if (!(jsonResponse.token instanceof String)) {
			println "Token bukan String : " + jsonResponse.token
		}
	}
	
	// ================================= FUNGSI UPDATE USER =======================================
	
	static ResponseObject putUpdateUser(String id, String firstName, String lastName) {
		return WS.sendRequest(findTestObject('Object Repository/ReqresIn/updateUser', [
			('id') : id,
			('first_name') : firstName,
			('last_name') : lastName
		]))
	}
	
	// Fungsi Validasi Body Register Successful
	static void validateUpdateUserBody(ResponseObject response) {
		def jsonResponse = new JsonSlurper().parseText(response.getResponseText())
	
		// Memastikan Struktur Body Setelah Update User
		assert jsonResponse.containsKey("updatedAt")
		
		// Cek Updated At
		if(!(jsonResponse.updatedAt instanceof String) || jsonResponse.updatedAt.isEmpty()) {
			println "Updated At tidak sesuai : " + jsonResponse.updatedAt
		}
	}
	
	// ================================= FUNGSI SINGLE USER =======================================
	
	static ResponseObject getSingleUser(String id) {
		return WS.sendRequest(findTestObject('Object Repository/ReqresIn/getSingleUser', [('id') : id]))
	}
	
	// Fungsi Validasi Body Register Successful
	static void validateGetSingleUserBody(ResponseObject response, String id) {
		def jsonResponse = new JsonSlurper().parseText(response.getResponseText())
	
		def user = jsonResponse.data
		
		// Memastikan Struktur Body Utama Setelah Get Single User
		assert user.containsKey("id")
		assert user.containsKey("email")
		assert user.containsKey("first_name")
		assert user.containsKey("last_name")
		assert user.containsKey("avatar")
		
		if(user != null) {
			println "Data User Dengan ID : " + id + " Berhasil Show"
		} else {
			println "Data User Dengan ID : " + id + " Gagal Show"
		}
		
		// Validasi Data Single User
		if(!(user.id.toString() == id) || !(user.id instanceof Integer)) {
			println "Id tidak sesuai : " + user.id
		}
		
		if(!(user.email instanceof String) || !user.email.contains('@')) {
		println "Email tidak sesuai : " + user.email
		}
		
		if(!(user.first_name instanceof String)) {
			println "Format first_name Tidak Valid : " + user.first_name
		}

		if(!(user.last_name instanceof String)) {
			println "Format last_name Tidak Valid : " + user.last_name
		}

		if(!(user.avatar instanceof String) && 
		    !(user.avatar.toLowerCase().contains(".jpg") || 
		  user.avatar.toLowerCase().contains(".jpeg") || 
		  user.avatar.toLowerCase().contains(".png"))) {
		println "Format avatar Tidak Valid : " + user.avatar
		}
	}
	
	// ================================= FUNGSI LIST USERS =======================================
	
	static ResponseObject getListUsers(String page) {
		return WS.sendRequest(findTestObject('Object Repository/ReqresIn/getUsers', [('page') : page]))
	}
	
	// Fungsi Validasi Body Register Successful
	static void validateGetListUsersBody(ResponseObject response) {
		def jsonResponse = new JsonSlurper().parseText(response.getResponseText())
	
		// Memastikan Struktur Body Setelah Get List Users
		assert jsonResponse.containsKey('page')
		assert jsonResponse.containsKey('per_page')
		assert jsonResponse.containsKey('total')
		assert jsonResponse.containsKey('total_pages')
		assert jsonResponse.containsKey('data')
		assert jsonResponse.containsKey('support')
		
		
		// Validasi Data Users
		jsonResponse.data.each { user ->

		if(!(user.id instanceof Integer)) {
			println "Format id Tidak Valid : " + user.id
		}

		if(!(user.email instanceof String) || !user.email.contains("@")) {
			println "Format email Tidak Valid : " + user.email
		}
		
		if(!(user.first_name instanceof String)) {
			println "Format first_name Tidak Valid : " + user.first_name
		}

		if(!(user.last_name instanceof String)) {
			println "Format last_name Tidak Valid : " + user.last_name
		}

		if(!(user.avatar instanceof String) && 
		    !(user.avatar.toLowerCase().contains(".jpg") || 
		      user.avatar.toLowerCase().contains(".jpeg") || 
		      user.avatar.toLowerCase().contains(".png"))) {
			println "Format avatar Tidak Valid : " + user.avatar
			}	
		}
	}
	
}
