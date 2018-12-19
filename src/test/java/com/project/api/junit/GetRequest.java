package com.project.api.junit;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest {

    @Test
    public void getJsonPlaceholder(){
    Response response = given()
            .when()
            .get("https://jsonplaceholder.typicode.com/posts");

        System.out.println(response.body().asString());

        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(200);

    }

    @Test
    public void getJsonPlaceholder1(){
        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/1");

        System.out.println(response.body().asString());

        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200);

    }

    @Test
    public void getJsonPlaceholderSearch(){

        String UrlJsonPlaceholerSearch = "https://jsonplaceholder.typicode.com/posts?userId=1&title=qui est esse";
       Response response = given()
                .when()
                .get(UrlJsonPlaceholerSearch);
        System.out.println(response.body().asString());


        given()
                .when()
                .get(UrlJsonPlaceholerSearch)
                .then()
                .statusCode(200);

    }
}
