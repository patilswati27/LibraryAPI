package files;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import org.apache.xmlbeans.XmlObject;
import org.w3c.dom.Document;

import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;

public class utilities {
	public static XmlPath rawXml(Response resp) {
		
		String response=resp.asString();
		System.out.println(response);
		XmlPath xml=new XmlPath(response);
		
		return xml;
		}
	public static JsonPath rawJson(Response res) {
		String response=res.asString();
		//System.out.println(response);
		JsonPath json=new JsonPath(response);
		return json;
	}

	
}




