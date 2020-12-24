package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils{
	RequestSpecification r;
	ResponseSpecification responsecode;
	TestDataBuild data=new TestDataBuild();
	Response response;
	static String place_id;
@Given("Add Place Payload with {string} {string} {string}")
public void add_place_payload_with(String name, String language, String address)throws IOException {
    // Write code here that turns the phrase above into concrete actions
    
    r=given().spec(requestSpecification())
    		.body(data.addPlacePayLoad(name,language,address));
}


@When("user calls {string} with {string} http request")
public void user_calls_with_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
//constructor will be called with value of resource which you pass
		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		
		responsecode =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
		 response =r.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
			 response =r.when().get(resourceAPI.getResource());
	    }

	    @Then("^the API call got success with status code 200$")
	    public void the_api_call_got_success_with_status_code_200() throws Throwable {
	    	assertEquals(response.getStatusCode(),200);
	    	
	    }

	    @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	    public void something_in_response_body_is_something(String keyvalue, String exceptedvalue) throws Throwable {
	    
	   assertEquals(getJsonPath(response, keyvalue), exceptedvalue); 
	    }

	    	@Then("verify place Id created maps to {string} using {string}")
	    	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	    	
	    		//requestspec
	    		 place_id=getJsonPath(response, "place_id");
	    		r=given().spec(requestSpecification()).queryParam("place_id", place_id);
	    		user_calls_with_http_request(resource,"GET");
	    		 String actualName=getJsonPath(response,"name");
	    		 assertEquals(actualName,expectedName);
	    	}

	    		@Given("DeletePlace Payload")
	    		public void delete_place_payload() throws IOException 
	    		{
	    			r=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));	
	    		}




	}

