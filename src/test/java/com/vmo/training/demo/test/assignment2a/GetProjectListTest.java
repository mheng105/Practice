package com.vmo.training.demo.test.assignment2a;

import com.vmo.training.demo.basetests.ProjectBaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class GetProjectListTest extends ProjectBaseTest {

    String invalid_accessToken="a1223a344b";

    @Test(description="Get all projects successfully")
    public void getAll_01(){
        Response response=given().header("Authorization", "Bearer " + getAccessToken()).when().get("/rest/v1/projects/");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test(description = "Get all projects with invalid accessToken")
    public void getAll_02(){
        Response response=given().header("Authorization", "Bearer " + invalid_accessToken).when().get("/rest/v1/projects/");
        response.prettyPrint();
        response.then().statusCode(401);
    }

    @Test(description = "Get all projects without accessToken")
    public void getAll_03(){
        Response response=given().when().get("/rest/v1/projects/");
        response.prettyPrint();
        response.then().statusCode(401);
    }

    @Test(description = "Get all projects when getting accessToken with invalid url")
    public void getAll_04(){
        Response response=given().header("Authorization", "Bearer " + getAccessTokenFail()).when().get("/rest/v1/projects");
        response.then().statusCode(404);
        response.prettyPrint();
    }

    @Test(description = "Get all projects with invalid url")
    public void getAll_05(){
        Response response=given().header("Authorization", "Bearer " + getAccessToken()).when().get("/rest/v1/");
        response.then().statusCode(404);
        response.prettyPrint();
    }


}
