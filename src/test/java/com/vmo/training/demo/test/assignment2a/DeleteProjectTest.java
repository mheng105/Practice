package com.vmo.training.demo.test.assignment2a;

import com.vmo.training.demo.basetests.ProjectBaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class DeleteProjectTest extends ProjectBaseTest {
    static String invalid_accessToken="a1223a344b";
    static String invalid_id="102002020a";
    @Test(description = "Delete a project successfully")
    public void D_01(){
        Response response=given().header("Authorization","Bearer "+getAccessToken())
                .when().delete("/rest/v1/projects/"+getId());
        response.then().statusCode(204);
    }

    @Test(description = "Delete a project with invalid accessToken")
    public void D_02(){
        Response response=given().header("Authorization","Bearer "+invalid_accessToken)
                .when().delete("/rest/v1/projects/"+getId());
        response.then().statusCode(401);
    }

    @Test(description = "Delete a project with invalid id")
    public void D_03(){
        Response response=given().header("Authorization","Bearer "+getAccessToken())
                .when().delete("/rest/v1/projects/"+invalid_id);
        response.then().statusCode(400);
    }

    @Test(description = "Delete a project without accessToken")
    public void D_04(){
        Response response=given()
                .when().delete("/rest/v1/projects/"+getId());
        response.then().statusCode(401);
    }

    @Test(description = "Delete a project with empty 'id'")
    public void D_05(){
        Response response=given().header("Authorization","Bearer "+getAccessToken())
                .when().delete("/rest/v1/projects/");
        response.then().statusCode(405);
    }

    @Test(description = "Delete a project when getting accessToken with invalid url")
    public void D_06(){
        Response response=given().header("Authorization","Bearer "+getAccessTokenFail())
                .when().delete("/rest/v1/projects/"+getId());
        response.then().statusCode(404);
    }

    @Test(description = "Delete a project when getting accessToken with invalid url")
    public void D_07(){
        Response response=given().header("Authorization","Bearer "+getAccessToken())
                .when().delete("/rest/v1/abc/"+getId());
        response.then().statusCode(404);
    }

    @Test(description = "Delete a project with invalid method")
    public void D_08(){
        Response response=given().header("Authorization","Bearer "+getAccessToken())
                .when().put("/rest/v1/projects/"+getId());
        response.then().statusCode(405);
    }

}
