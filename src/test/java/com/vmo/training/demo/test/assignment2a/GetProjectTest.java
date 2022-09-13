package com.vmo.training.demo.test.assignment2a;

import com.vmo.training.demo.basetests.ProjectBaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class GetProjectTest extends ProjectBaseTest {
    static String invalid_accessToken="a1223a344b";
    static String invalid_id="102002020a";

    @Test(description = "Get a project successfully")
    public void G_01(){
        Response response=given().header("Authorization","Bearer "+getAccessToken())
                .when()
                .get("/rest/v1/projects/"+getId());
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test(description = "Get a project with invalid accessToken")
    public void G_02(){
        Response response=given().header("Authorization","Bearer "+invalid_accessToken)
                .when()
                .get("/rest/v1/projects/"+getId());
        response.then().statusCode(401);
        response.prettyPrint();
    }

    @Test(description = "Get a project without accessToken")
    public void G_03(){
        Response response=given()
                .when()
                .get("/rest/v1/projects/"+getId());
        response.then().statusCode(401);
        response.prettyPrint();
    }

    @Test(description = "Get a project when getting accessToken with invalid url")
    public void G_04(){
        Response response=given().header("Authorization","Bearer "+getAccessTokenFail())
                .when()
                .get("/rest/v1/projects/"+getId());
        response.then().statusCode(404);
    }

    @Test(description = "Get a project with invalid url")
    public void G_05(){
        Response response=given().header("Authorization","Bearer "+getAccessToken())
                .when()
                .get("/rest/v1/abc/"+getId());
        response.then().statusCode(404);
    }

    @Test(description = "Get a project with invalid id")
    public void G_06(){
        Response response=given().header("Authorization","Bearer "+getAccessToken())
                .when()
                .get("/rest/v1/projects/"+invalid_id);
        response.then().statusCode(404);
    }

    @Test(description = "Get a project without id")
    public void G_07(){
        Response response=given().header("Authorization","Bearer "+getAccessToken())
                .when()
                .get("/rest/v1/projects/");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test(description = "Get a project with invalid method")
    public void G_10(){
        Response response=given().header("Authorization","Bearer "+getAccessToken())
                .when()
                .patch("/rest/v1/projects/"+getId());
        response.then().statusCode(405);
    }
}
