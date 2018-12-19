package com.project.api.junit;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class DeleteRequest {

    @Test
    public void deleteJsonPlaceholder(){
        given()
                .when()
                .delete("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .log()
                .all();

    }
}
