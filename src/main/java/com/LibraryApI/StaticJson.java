package com.LibraryApI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import files.Payload;
import files.Resources;
import files.utilities;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class StaticJson {
	@Test
	public void AddBook() throws IOException {
	    RestAssured.baseURI="http://216.10.245.166";
	    
	    Response res=RestAssured.given().
	    header("Content-Type","application/json").
	    body(GenerateStringFromResponse("//home//v-swati.patil//eclipse-workspace//LibraryAPI//src//main//resources//payload.json")).log().all().
	    when().
	    post(Resources.placePostData()).
	    then().assertThat().statusCode(200).log().all().
	    extract().response();
	    JsonPath js=utilities.rawJson(res);
	    String id=js.get("ID");
	    System.out.println(id);
	      		
		}
public static String GenerateStringFromResponse(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}

