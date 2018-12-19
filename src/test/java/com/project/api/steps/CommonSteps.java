package com.project.api.steps;

import com.project.api.cucumber.PropertyFileReader;
import com.project.api.cucumber.RuntimeData;
import com.project.api.model.CustomerPojo;
import com.project.api.util.ApiIntegrationUtil;
import cucumber.api.java.en.Given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class CommonSteps {
    private RequestSpecification request;
    private Response response;
    private CustomerPojo customer;


    public static void statusCode(Response response, int code) {
        response.then().log().all().statusCode(code);
    }

    @Given("I have extracted russian doll base firstname")
    public void i_have_extracted_russian_doll_base_firstname() {
        ArrayList<String> addresses = new ArrayList<String>();
        addresses.add("base1");
        addresses.add("base2");
        customer = new CustomerPojo();
        customer.setFirstName("DollBase");
        customer.setLastName("Base");
        customer.setPhone("HeaderBase");
        customer.setAddresses(addresses);
        request = given().headers(PropertyFileReader.getFullHeader()).log().headers();
        response = request.when()
                .body(customer)
                .log()
                .body()
                .post("https://jsonplaceholder.typicode.com/users");

        statusCode(response, 201);
        RuntimeData.getInstance().setRussianDollVariable(response.then().extract().path("firstName").toString());
        RuntimeData.getInstance().setDynamicHeader(response.then().extract().path("phone").toString());
    }
}
