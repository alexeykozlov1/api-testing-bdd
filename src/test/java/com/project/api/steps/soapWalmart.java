package com.project.api.steps;

import com.project.api.util.ResponseHelper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class soapWalmart {
    private Response response;
    private RequestSpecification request;

    String walmartUrl = "http://api.walmartlabs.com/v1/reviews/11?format=xml&apiKey=";
    String payload = "<SOAP-ENV:Envelope\n" +
            "   xmlns:SOAP-ENV = \"http://www.w3.org/2001/12/soap-envelope\"\n" +
            "   SOAP-ENV:encodingStyle = \"http://www.w3.org/2001/12/soap-encoding\">\n" +
            "\n" +
            "   <SOAP-ENV:Body xmlns:m = \"http://www.xyz.org/quotations\">\n" +
            "      <m:GetQuotation>\n" +
            "         <m:QuotationsName>MiscroSoft</m:QuotationsName>\n" +
            "      </m:GetQuotation>\n" +
            "   </SOAP-ENV:Body>\n" +
            "</SOAP-ENV:Envelope>";

    @Given("I have xml content")
    public void i_have_xml_content() {
        request = given().contentType("text/xml");
    }

    @When("I create POST request to Walmart")
    public void i_create_POST_request_to_Walmart() {
        response = request.when()
                .body(payload)
                .log()
                .body()
                .post(walmartUrl);
    }

    @Then("I get {int} from Walmart")
    public void i_get_from_Walmart(Integer code) {
        CommonSteps.statusCode(response, code);
    }

    @Then("getting {string} in the body with {string} for SOAP")
    public void getting_in_the_body_with_for_SOAP(String message, String code) {
        ResponseHelper.soapWalmartErrorResponseValidation(response, message, code);
    }
}
