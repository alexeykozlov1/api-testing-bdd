package com.project.api.util;


import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;

import java.util.List;

public class ResponseHelper {

    //checker of Json: http://jsonviewer.stack.hu/
    public static void errorResponseValidation(Response response, String statusCode, String errorCode, String developerMessage, String errorType) {
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(Integer.parseInt(statusCode));
        Assert.assertEquals(errorCode, validatableResponse.extract().path("error.errorCode"));
        Assert.assertEquals(developerMessage, validatableResponse.extract().path("error.developerMessage"));
        Assert.assertEquals(errorType, validatableResponse.extract().path("error.errorType"));
    }

    public static void responseValidations(Response response, List<String> responseElements) {

        for (String check : responseElements) {
            Assert.assertNotNull(response.then().extract().path(check));
        }
    }

    public static void walmartErrorResponseValidation(Response response, String statusCode) {
        ValidatableResponse validatableResponse = response.then().log().all();
        Assert.assertEquals(statusCode, validatableResponse.extract().path("errors[0].message"));
    }

    public static void walmartErrorResponseValidation2(Response response, String statusCode, String code) {
        ValidatableResponse validatableResponse = response.then().log().all();
        Assert.assertEquals(statusCode, validatableResponse.extract().path("errors[0].message"));

        int extractor = validatableResponse.extract().path("errors[0].code");
        String y = Integer.toString(extractor);
        Assert.assertEquals(code, y);
    }

    public static void soapWalmartErrorResponseValidation(Response response, String statusCode, String code) {
        ValidatableResponse validatableResponse = response.then().log().all();
        Assert.assertEquals(statusCode, validatableResponse.extract().path("errors.error.message"));
        Assert.assertEquals(code, validatableResponse.extract().path("errors.error.code"));
    }
}
