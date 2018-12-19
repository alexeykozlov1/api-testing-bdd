package com.project.api.steps;

import com.project.api.cucumber.PropertyFileReader;
import com.project.api.model.CustomerPojo;
import com.project.api.util.ResponseHelper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CustomerSteps {

    private CustomerPojo customer;
    private RequestSpecification request;
    private Response response;
    private Connection connection;
    private static final String DbName = "MSDB";
    private static final String DdName = "123";
    static private String nameID = "";

    @Given("I have the data to create customer with {string},{string},{string},{string},{string}")
    public void i_have_the_data_to_create_customer_with(String firstName, String lastName, String phone, String address1, String address2) {
        ArrayList<String> addresses = new ArrayList<String>();
        addresses.add(address1);
        addresses.add(address2);
        customer = new CustomerPojo();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPhone(phone);
        customer.setAddresses(addresses);


    }

 /*   @Given("I use customer header")
    public void i_use_customer_header() {
        request = given().headers("Content-Type",PropertyFileReader.getContentType());
    }*/

    @Given("I use customer header")
    public void i_use_customer_header() {
        request = given().headers(PropertyFileReader.getFullHeader()).log().headers();
    }

    @When("I create post request to create customer")
    public void i_create_post_request_to_create_customer() {
        response = request.when()
                .body(customer)
                .log()
                .body()
                .post("https://jsonplaceholder.typicode.com/users");
    }

    @Then("I get status code {int} from database")
    public void i_get_status_code_from_database(Integer statusCode) {
        response.then().statusCode(statusCode).log().all();
    }

    @Then("response body should contain")
    public void response_body_should_contain(List<String> responseMap) {
        ResponseHelper.responseValidations(response, responseMap);
    }


    @When("I make the request to Walmart")
    public void i_make_the_request_to_Walmart() {
        response = request
                .when()
                .log()
                .body()
                .get("http://api.walmartlabs.com/v1/search?query=ipod&format=json&apiKey=");

    }

    @Then("getting {string} in the body with")
    public void getting_in_the_body_with(String message) {
        ResponseHelper.walmartErrorResponseValidation(response, message);
    }

    @Then("getting {string} in the body with {string}")
    public void getting_in_the_body_with(String message, String code) {
        ResponseHelper.walmartErrorResponseValidation2(response, message, code);
    }

    @Then("I had stored the customer response")
    public void i_had_stored_the_customer_response() {
        JsonPath jsonPathEvaluator = response.jsonPath();
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("------------");

        customer.setFirstName(jsonPathEvaluator.get("firstName").toString());
        System.out.println("First Name is "+ jsonPathEvaluator.get("firstName").toString());

        customer.setFirstName(jsonPathEvaluator.get("lastName").toString());
        System.out.println("Last Name is "+ jsonPathEvaluator.get("lastName").toString());

        customer.setFirstName(jsonPathEvaluator.get("phone").toString());
        System.out.println("Phone is "+ jsonPathEvaluator.get("phone").toString());
    }

    @Then("I send query to database to verify the request")
    public void i_send_query_to_database_to_verify_the_request() {
        try{

            connection = DriverManager.getConnection("dtabase URL", DbName, DdName);

            if (customer.getFirstName() !=null){
                String selectSQL = "select TABLE1, TABLE2 from 123 where 234 = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
                preparedStatement.setString(1, customer.getFirstName());
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()){
                    nameID = rs.getString("12312");
                }
                connection.close();
            }

        }
        catch (Exception e){
            System.out.println("Database connection could not be made");
        }
    }

    @Then("I should get the data that matching the request")
    public void i_should_get_the_data_that_matching_the_request() {
        if(DdName !=null){
            System.out.println("First Name is "+ nameID);
            Assert.assertEquals(nameID, "");
        }
        else
        {
            System.out.println("No results from database");
        }
    }
}
