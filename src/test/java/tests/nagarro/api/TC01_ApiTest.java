package tests.nagarro.api;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import setup.nagarro.utilities.ExtentTestManager;

import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TC01_ApiTest {
    public static final Logger log = Logger.getLogger(TC01_ApiTest.class.getName());
    public static ExtentTest test;

    @BeforeClass
    public void setup(){
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .setBaseUri("https://reqres.in/")
                .setBasePath("api/users")
                .build();

        ResponseSpecification responseSpec = new ResponseSpecBuilder()
                .expectResponseTime(lessThan(6000L))
                .build();

        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;

    }


    @Test(priority = 1)
    public void getUsers(){
        test = ExtentTestManager.startTest("Verify get user response", "To verify get user response");

        test.log(Status.INFO, "Verify status code is 200");
        Response response = given().queryParam("page", "2").
                get().
                then().
                statusCode(200).extract().response();

        test.log(Status.INFO, "Verify that first name is Byron for user id 10");

        JsonPath jsp = new JsonPath(response.asString());
        int count_of_data = jsp.getInt("data.size()");
        for (int i=0; i<count_of_data; i++){
            if(jsp.getInt("data["+i+"].id") == 10){
                String actual_first_name =  jsp.get("data["+i+"].first_name");
                String expected_first_name = "Byron";
                Assert.assertEquals(actual_first_name,expected_first_name);
            }
        }
    }

    @Test(priority = 2)
    public void addNewUser(){
        test = ExtentTestManager.startTest("Verify post user response", "To add a new user");
        String payLoad = "{\"name\":\"Bryant\",\"job\":\"BA\"}";

        test.log(Status.INFO, "Verify status code is 201");
        given().
                body(payLoad)
                .when()
                .post()
                .then()
                .assertThat().statusCode(201).body(matchesJsonSchemaInClasspath("schema.json"));
    }

}
