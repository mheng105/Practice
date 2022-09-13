package com.vmo.training.demo.test.assignment2a;

import com.vmo.training.demo.basetests.ProjectBaseTest;
import com.vmo.training.demo.models.assignment2a.ProjectInfo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class UpdateProjectTest extends ProjectBaseTest {
    static String invalid_accessToken="a1223a344b";
    static String invalid_id="102002020a";

    @Test(description = "Update a project successfully")
    public void U_01(){
        ProjectInfo project=new ProjectInfo();
        project.setName("C5 Project1");
        project.setColor(40);
        project.setFavorite(true);
        Response response=given().header("Authorization","Bearer "+getAccessToken())
                .contentType(ContentType.JSON)
                .when()
                .body(project)
                .post("/rest/v1/projects/"+getId());
        response.then().statusCode(204);
        response.prettyPrint();
    }

    @Test(description = "Update a project with invalid accessToken")
    public void U_02(){
        ProjectInfo project=new ProjectInfo();
        project.setName("C5 Project2");
        Response response=given().header("Authorization","Bearer "+invalid_accessToken)
                .contentType(ContentType.JSON)
                .when()
                .body(project)
                .post("/rest/v1/projects/"+getId());
        response.then().statusCode(401);
        response.prettyPrint();
    }

    @Test(description = "Update a project with invalid id")
    public void U_03(){
        ProjectInfo project=new ProjectInfo();
        project.setName("C5 Project2");
        Response response=given().header("Authorization","Bearer "+getAccessToken())
                .contentType(ContentType.JSON)
                .when()
                .body(project)
                .post("/rest/v1/projects/"+invalid_id);
        response.then().statusCode(400);
        response.prettyPrint();
    }

    @Test(description = "Update a project with invalid id")
    public void U_04(){
        ProjectInfo project=new ProjectInfo();
        project.setName("C5 Project2");
        Response response=given().header("Authorization","Bearer "+getAccessToken())
                .contentType(ContentType.JSON)
                .when()
                .body(project)
                .post("/rest/v1/projects/");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test(description = "Update a project when getting accessToken with invalid url")
    public void U_05(){
        ProjectInfo project=new ProjectInfo();
        project.setName("C5 Project2");
        Response response=given().header("Authorization","Bearer "+getAccessTokenFail())
                .contentType(ContentType.JSON)
                .when()
                .body(project)
                .post("/rest/v1/projects/"+getId());
        response.then().statusCode(404);
        response.prettyPrint();
    }

    @Test(description = "Update a project with invalid url")
    public void U_06(){
        ProjectInfo project=new ProjectInfo();
        project.setName("C5 Project2");
        Response response=given().header("Authorization","Bearer "+getAccessToken())
                .contentType(ContentType.JSON)
                .when()
                .body(project)
                .post("/rest/v1/abc/"+getId());
        response.then().statusCode(404);
        response.prettyPrint();
    }

    @Test(description = "Update a project with invalid name")
    public void U_07(){
        ProjectInfo project=new ProjectInfo();
        project.setName1(123456);
        Response response=given().header("Authorization","Bearer "+getAccessToken())
                .contentType(ContentType.JSON)
                .when()
                .body(project)
                .post("/rest/v1/projects/"+getId());
        response.then().statusCode(400);
        response.prettyPrint();
    }

    @Test(description = "Update a project without accessToken")
    public void U_08(){
        ProjectInfo project=new ProjectInfo();
        project.setName("C5 Project2");
        Response response=given()
                .contentType(ContentType.JSON)
                .when()
                .body(project)
                .post("/rest/v1/projects/"+getId());
        response.then().statusCode(401);
        response.prettyPrint();
    }

    @Test(description = "Update a project with invalid method")
    public void U_09(){
        ProjectInfo project=new ProjectInfo();
        project.setName("C5 Project2");
        Response response=given().header("Authorization","Bearer "+getAccessToken())
                .contentType(ContentType.JSON)
                .when()
                .body(project)
                .put("/rest/v1/projects/"+getId());
        response.then().statusCode(405);
        response.prettyPrint();
    }

}
