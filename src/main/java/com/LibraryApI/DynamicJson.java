package com.LibraryApI;
import files.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
	

public class DynamicJson { 
Properties prop=new Properties();

@BeforeTest
public void  getData() throws FileNotFoundException {
	FileInputStream fis =new FileInputStream("//home//v-swati.patil//eclipse-workspace//LibraryAPI//src//main//java//files//env.propperties");	
    try {
		prop.load(fis);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
@Test(dataProvider="BooksData")
public void AddBook(String isbn,String aisle) throws IOException {
    RestAssured.baseURI=prop.getProperty("HOST");
    
    Response res=RestAssured.given().
    header("Content-Type","application/json").
    body(Payload.addBook(isbn,aisle)).log().all().
    when().
    post(Resources.placePostData()).
    then().assertThat().statusCode(200).log().all().
    extract().response();
    JsonPath js=utilities.rawJson(res);
    String id=js.get("ID");
    System.out.println(id);
      		
	}

@DataProvider(name="BooksData")
public Object[][] getBookData(){
	return new Object[][] {{"def","123"},{"ghj","345"},{"jkl","987"}};//to pass multiple data  
	
}


}
