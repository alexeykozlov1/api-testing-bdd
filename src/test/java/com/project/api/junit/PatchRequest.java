package com.project.api.junit;

import com.project.api.model.CustomerPojo;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class PatchRequest {
    @Test
    public void patchJsonPlaceholder(){

        ArrayList<String> addresses = new ArrayList<String>();
        addresses.add("Union St 21");
        addresses.add("Baker Ave 32");

        CustomerPojo customer = new CustomerPojo();
        customer.setFirstName("Alex");
        customer.setLastName("Kozlov");
        customer.setPhone("1123456789");
        customer.setAddresses(addresses);

        given()
                .contentType(ContentType.JSON)
                .when()
                .body(customer)
                .patch("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .log()
                .all();

    }
}
