package com.vmo.training.demo.test.assignment2a;

import com.vmo.training.demo.basetests.ProjectBaseTest;
import com.vmo.training.demo.models.assignment2a.ProjectInfo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateProjectTest extends ProjectBaseTest {
    static String invalid_accessToken="a1223a344b";

    @Test(description = "Create a new project successfully")
    public void C_01(){
        ProjectInfo project=new ProjectInfo();
        project.setName("C5 Project");
        Response response=given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getAccessToken())
                .when()
                .body(project)
                .post("/rest/v1/projects");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test(description = "Create a new project without 'name' field")
    public void C_02(){
        ProjectInfo project=new ProjectInfo();
        project.setColor(40);
        project.setFavorite(true);
        Response response=given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getAccessToken())
                .when()
                .body(project)
                .post("/rest/v1/projects");
        response.prettyPrint();
        response.then().statusCode(400);
    }

    @Test(description = "Create a new project with invalid accessToken")
    public void C_03(){
        ProjectInfo project=new ProjectInfo();
        project.setName("C5 Project");
        Response response=given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + invalid_accessToken)
                .when()
                .body(project)
                .post("/rest/v1/projects");
        response.prettyPrint();
        response.then().statusCode(401);
    }

    @Test(description = "Create a new project without accessToken")
    public void C_04(){
        ProjectInfo project=new ProjectInfo();
        project.setName("C5 Project");
        Response response=given()
                .contentType(ContentType.JSON)
                .when()
                .body(project)
                .post("/rest/v1/projects");
        response.prettyPrint();
        response.then().statusCode(401);
    }

    @Test(description = "Create a new project when getting accessToken with invalid url")
    public void C_05(){
        ProjectInfo project=new ProjectInfo();
        project.setName("C5 Project");
        project.setColor(40);
        project.setFavorite(true);
        Response response=given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getAccessTokenFail())
                .when()
                .body(project)
                .post("/rest/v1/projects");
        response.prettyPrint();
        response.then().statusCode(404);
    }
    @Test(description = "Create a new project with invalid url")
    public void C_06(){
        ProjectInfo project=new ProjectInfo();
        project.setName("C5 Project");
        project.setColor(40);
        project.setFavorite(true);
        Response response=given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getAccessToken())
                .when()
                .body(project)
                .post("/rest/v1");
//        response.prettyPrint();
        response.then().statusCode(404);
    }

    @Test(description = "Create a new project with invalid 'name' field")
    public void C_07(){
        ProjectInfo project=new ProjectInfo();
        project.setName1(123456);
        project.setColor(40);
        project.setFavorite(true);
        Response response=given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getAccessToken())
                .when()
                .body(project)
                .post("/rest/v1/projects");
        response.prettyPrint();
        response.then().statusCode(400);
    }

    @Test(description = "Create a new project with empty 'name' field")
    public void C_08(){
        ProjectInfo project=new ProjectInfo();
        project.setName("");
        project.setColor(40);
        project.setFavorite(true);
        Response response=given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getAccessToken())
                .when()
                .body(project)
                .post("/rest/v1/projects");
        response.prettyPrint();
        response.then().statusCode(400);
    }

    @Test(description = "Create a new project with invalid method")
    public void C_09(){
        ProjectInfo project=new ProjectInfo();
        project.setName("");
        project.setColor(40);
        project.setFavorite(true);
        Response response=given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getAccessToken())
                .when()
                .body(project)
                .delete("/rest/v1/projects");
        response.prettyPrint();
        response.then().statusCode(405);
    }

    @Test(description = "Create a new project when existed maximum projects")
    public void C_10(){
        ProjectInfo project=new ProjectInfo();
        project.setName("C5 Project");
        Response response=given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getAccessToken())
                .when()
                .body(project)
                .post("/rest/v1/projects");
        response.prettyPrint();
        response.then().statusCode(403);
    }
}
