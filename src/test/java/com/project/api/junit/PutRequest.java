package com.project.api.junit;

import com.project.api.model.CustomerPojo;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class PutRequest {

    @Test
    public void putJsonPlaceholder(){

        ArrayList<String> addresses = new ArrayList<String>();
        addresses.add("Union St 21");
        addresses.add("Baker Ave 32");
        addresses.add("Test Put");

        CustomerPojo customer = new CustomerPojo();
        customer.setFirstName("Alex");
        customer.setLastName("Kozlov");
        customer.setPhone("1123456789");
        customer.setAddresses(addresses);

        given()
                .contentType(ContentType.JSON)
                .when()
                .body(customer)
                .put("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .log()
                .all();

    }
}
