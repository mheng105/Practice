package com.vmo.training.demo.basetests.assignment2a;

import com.vmo.training.demo.models.assignment2a.Account;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ProjectBaseTest {

    @BeforeMethod
    public void init(){
        RestAssured.baseURI="https://api.todoist.com/";
    }

    public String getAccessToken(){
        Account acc=new Account();
        acc.setEmail("mheng105@gmail.com");
        acc.setPassword("123456abc");
        Response response= (Response) given()
                .contentType(ContentType.JSON)
                .when()
                .body(acc)
                .post("/API/v8.7/user/login");
        response.then().statusCode(200);
        String token=response.path("token");
        return token;
    }

    public String getAccessTokenFail(){
        Account acc=new Account();
        acc.setEmail("mheng105@gmail.com");
        acc.setPassword("123456abc");
        Response response= (Response) given()
                .contentType(ContentType.JSON)
                .when()
                .body(acc)
                .post("/abc");
        response.then().statusCode(404);
        String token=response.path("token");
        return token;
    }

    public String getId(){
        Response response=given()
                .header("Authorization", "Bearer " + getAccessToken())
                .when()
                .get("/rest/v1/projects");
        response.then().statusCode(200);
        List<Integer> jsonResponse = response.jsonPath().getList("id");
        return String.valueOf(jsonResponse.get(3));
    }
}
