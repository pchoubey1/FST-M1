package Project;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import java.util.HashMap;
import java.util.Map;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GitHubProjectRest {
	//SSH Key to test with
	String sshKey ="ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAID5kDb0OwjB33LBTRqT/bXLH76UCgJLxG/CoNCawjqPx";
					
	int sshKeyId;
	//Request Specification
	RequestSpecification requestSpec = new RequestSpecBuilder().
			setBaseUri("https://api.github.com/user/keys").
			addHeader("Authorization","").
			addHeader("Content-Type","application/json").
			build();
	//Response Specification

	ResponseSpecification responseSpec = new ResponseSpecBuilder().
			expectResponseTime(lessThanOrEqualTo(4000L)).
			expectBody("key",equalTo(sshKey)).
			expectBody("title",equalTo("TestAPIKey")).
			build();
	@Test(priority= 1)
	public void postRequestTest() {
		//path:https://api.github.com/user/keys
		//Request body
		Map<String,Object> reqBody =new HashMap<>();
		reqBody.put("title","TestAPIKey");
		reqBody.put("key",sshKey);
		//Generate response
		Response response =given().spec(requestSpec).body(reqBody).when().post();
		//Extract the id
		//Reporter.log(response.asPrettyString());
		response.prettyPrint();
		sshKeyId =response.then().extract().path("id");
		
		//Assertions
		response.then().statusCode(201).spec(responseSpec);
	}
	@Test(priority= 2)
	public void getRequestTest()
	{
		//path:https://api.github.com/user/keys{keyId}
		//Generated response and assert
		given().spec(requestSpec).pathParam("keyId",sshKeyId).
		when().get("/{keyId}").then().statusCode(200).spec(responseSpec);	
	}
	@Test(priority=3)
	public void deleteRequestTest() {
		given().spec(requestSpec).pathParam("keyId", sshKeyId).
		when().delete("/{keyId}").
		then().statusCode(204).time(Matchers.lessThan(3000L));
	
	}
}
