package com.vmo.training.demo.microservices.steps.assignment2a;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

import static com.vmo.training.demo.handles.ResponseHandles.*;
import static com.vmo.training.demo.utils.JsonUtils.*;
import static io.restassured.RestAssured.given;

public class steps2a {

    @Step("Create new project")
    public Response createNewProject(Map<String,Object> map,String accessToken,String path){
        return sendPostMethod(map,accessToken,path);
    }

    @Step("Create a new project without accessToken")
    public Response createNewProjectWithoutAccessToken(Map<String,Object> map,String path){
        return sendPostMethodWithoutToken(map,path);
    }

    public Response createNewProjectWithInvalidMethod(Map<String,Object>map,String accessToken,String path){
//        Response response=given()
//                .contentType(ContentType.JSON)
//                .header("Authorization", "Bearer " + accessToken)
//                .when()
//                .body(map)
//                .delete(path);
        return sendDeleteMethod(accessToken,path);
    }

    @Step("A project is created successfully")
    public steps2a createProjectSuccessfully(int exceptedCode, Response response, String name){
        showPretty(response);
        Assert.assertEquals(response.getStatusCode(),exceptedCode);
        verifyName(response,name);
        verifyId(response);
        return this;
    }

    @Step("Verify name")
    public steps2a verifyName(Response response, String name){
        String resString=response.asPrettyString();
        Assert.assertEquals(jsonValue(resString,"name"),name);
        return this;
    }

    @Step("Verify id")
    public steps2a verifyId(Response response){
        String resString=response.asPrettyString();
        String id=jsonValue(resString,"id");
        assert jsonValue(resString,"url").contains(id);
        return this;
    }

    @Step("Get a project/projects")
    public Response getProject(String accessToken,String path){
        return sendGetMethod(accessToken,path);
    }

    @Step("Get a project/projects without accessToken")
    public Response getProjectWithoutAccessToken(String path){
        return sendGetMethodWithoutToken(path);
    }

    public Response getProjectWithInvalidMethod(Map map,String accessToken,String path){
        return sendPatchMethod(map,accessToken,path);
    }

    @Step("Verify status code")
    public steps2a verifyStatus(int exceptedCode,Response response){
        showPretty(response);
        Assert.assertEquals(response.getStatusCode(),exceptedCode);
        return this;
    }

    @Step("Update a project")
    public Response updateProject(Map<String,Object> map,String accessToken,String path){
        return sendPostMethod(map,accessToken,path);
    }

    @Step("Update a project without accessToken")
    public Response updateProjectWithoutAccessToken(Map map,String path){
        return sendPostMethodWithoutToken(map,path);
    }

    @Step("Update project with invalid method")
    public Response updateProjectWithInvalidMethod(Map map,String accessToken,String path){
        return sendPutMethod(map,accessToken,path);
    }

    @Step("Delete a project")
    public Response deleteProject(String accessToken,String path){
        return sendDeleteMethod(accessToken,path);
    }

    @Step("Delete a project without token")
    public Response deleteProjectWithoutAccessToken(String path){
        return sendDeleteMethodWithoutToken(path);
    }

    @Step()
    public Response deleteProjectWithInvalidMethod(Map map,String accessToken,String path){
        return sendPutMethod(map,accessToken,path);
    }

    @Step("Show pretty")
    public void showPretty(Response response){
        response.prettyPrint();
    }

}
