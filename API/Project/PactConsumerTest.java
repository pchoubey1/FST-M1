package PACT;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



@ExtendWith(PactConsumerTestExt.class)
public class PactConsumerTest {
	
//--------add the following properties to the class:

	
	Map<String, String> headers = new HashMap<String, String>();// Create Map for the headers
	String createUser = "/api/users";// Set resource URI

//------Next add the Pact contract creation method:
	// Create Pact contract
	@Pact(provider = "UserProvider", consumer = "UserConsumer")
	public RequestResponsePact createPact(PactDslWithProvider builder)  { 

		// Add headers
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json");


		// Create request JSON
		DslPart bodySentCreateUser = new PactDslJsonBody()
		    .numberType("id", 1)
		    .stringType("firstName", "John")
		    .stringType("lastName", "Ross")
		    .stringType("email", "john.ross@abc.com");
		
		DslPart bodyReceivedCreateUser = new PactDslJsonBody().
				numberType("id", 1)
			    .stringType("firstName", "John")
			    .stringType("lastName", "Ross")
			    .stringType("email", "john.ross@abc.com");
      
		return builder.given("POST Request").
				uponReceiving("request to create a user").
				   method("POST").
				   path("/api/users").
				   headers(headers).
				   body(bodySentCreateUser).
				willRespondWith().
				   status(201).
				   body(bodyReceivedCreateUser).
				 toPact();
	}


@Test
@PactTestFor(providerName = "UserProvider", port = "8080")
public void runTest() {

	// Mock url
	RestAssured.baseURI = "http://localhost:8080";
	// Create request specification
	RequestSpecification rq = RestAssured.given().headers(headers).when();
	
	//Then, write the request body that will be sent:
		// Create request body
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 1);
		map.put("firstName", "John");
		map.put("lastName", "Ross");
		map.put("email", "john.ross@abc.com");

		// Send POST request
		Response response = rq.body(map).post(createUser);
		// Assertion
		assert (response.getStatusCode() == 201);


}



}
