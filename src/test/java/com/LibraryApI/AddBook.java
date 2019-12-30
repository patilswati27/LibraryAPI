package com.LibraryApI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;



import files.DataDriven;
import files.utilities;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AddBook {
	private Logger log=LogManager.getLogger(AddBook.class.getName());	
    @Test
	public void addBook() throws IOException {
    	DataDriven d=new DataDriven();
    	ArrayList data=d.getData("RestAddbook","RestAssured");
    	
    	RestAssured.baseURI="http://216.10.245.166";
    	HashMap<String,Object> map=new HashMap<String, Object>();
    	map.put("name",data.get(1));
    	map.put("isbn",data.get(2));
    	map.put("aisle",data.get(3));
    	map.put("author",data.get(4));
    	
    	String res=RestAssured.given()
        .header("Content-Type","application/json")
        .body(map)
        .when()
        .post("/Library/Addbook.php")
        .then().and()
        .assertThat()
        .extract().response().asString();
        System.out.println(res);
        JsonPath js=utilities.rawJson(res);
        String id=js.get("ID");
        System.out.println("book id "+id);
        JSONObject request = new JSONObject();
    	try {
    		request.put("ID", id);
    		log.info("Placed id in delete API to delete book");
    	} catch (JSONException e) {
    		e.printStackTrace();
    	}
        
         String resp=RestAssured.given()
        .header("Content-Type","application/json")
        .body(request.toString())
        .when()
        .post("Library/DeleteBook.php")
        .then().assertThat().statusCode(200)
        .extract().response().toString(); 
         System.out.println(resp);
		
	}
}
