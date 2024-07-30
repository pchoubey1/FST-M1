package Example;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;

public class Activity4 {
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	int petId;
	@BeforeClass
	public void stepUp() {
		requestSpec = new RequestSpecBuilder().
				setBaseUri("https://petstore.swagger.io/v2/pet").
				addHeader("Content-Type","application/json").
				
				build();
		
		responseSpec = new ResponseSpecBuilder().
				expectStatusCode(200).expectResponseTime(lessThanOrEqualTo(3000L)).build();
	}
	@Test(priority = 1)
	public void postRequest() {
		Map<String,Object> reqBody =new HashMap<String,Object>();
		reqBody.put("id",8896);
		reqBody.put("name","Aryaa");
		reqBody.put("status","alive");
		Response response =given().spec(requestSpec).body(reqBody).when().post();
		petId =response.then().extract().path("id");
		response.then().spec(responseSpec).body("status",equalTo("alive"));
		
	}
	//@Test (dependsOnMethods={"postReaquest"},priority = 2)
	@Test(priority = 2)
	public void getRequest() {
		given().spec(requestSpec).pathParam("petId",petId).when().get("/{petId}").then().spec(responseSpec).body("status",equalTo("alive"));
		
	}
	@Test(priority = 3)
	public void deleteRequest() {
		given().spec(requestSpec).pathParam("petId", petId).when().delete("/{petId}").then().spec(responseSpec).body("message", equalTo(""+petId));
	}
}
