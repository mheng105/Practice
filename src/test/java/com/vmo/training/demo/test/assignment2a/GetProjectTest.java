package com.vmo.training.demo.test.assignment2a;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vmo.training.demo.basetests.assignment2a.ProjectBaseTest;
import com.vmo.training.demo.microservices.steps.assignment2a.steps2a;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class GetProjectTest extends ProjectBaseTest {
    steps2a getProjectSteps=new steps2a();

    @BeforeMethod(description = "Get all project")
    @Step("Get one of id in project list")
    @Description("Get id")
    public void getId() {
        response = responseHandles.sendGetMethod(getAccessToken(), path);
        Assert.assertEquals(response.statusCode(), 200);
        List re = response.as(List.class);
        String object = new Gson().toJson(re.get(1));
        JsonObject jObject = new Gson().fromJson(object, JsonObject.class);
        id= String.valueOf(jObject.get("id"));
    }
    @Test(description = "Get a project successfully")
    public void G_01(){
        response=getProjectSteps.getProject(getAccessToken(),path+"/"+id);
        getProjectSteps.verifyStatus(200,response);
    }

    @Test(description = "Get a project with invalid accessToken")
    public void G_02(){
        response=getProjectSteps.getProject(invalid_accessToken,path+"/"+id);
        getProjectSteps.verifyStatus(401,response);
    }

    @Test(description = "Get a project without accessToken")
    public void G_03(){
        response=getProjectSteps.getProjectWithoutAccessToken(path);
        getProjectSteps.verifyStatus(401,response);
    }

    @Test(description = "Get a project when getting accessToken with invalid url")
    public void G_04(){
        response=getProjectSteps.getProject(getAccessTokenFail(),path+"/"+id);
        getProjectSteps.verifyStatus(404,response);
    }

    @Test(description = "Get a project with invalid url")
    public void G_05(){
        response=getProjectSteps.getProject(getAccessToken(),path+"/a/abc/"+id);
        getProjectSteps.verifyStatus(404,response);
    }

    @Test(description = "Get a project with invalid id")
    public void G_06(){
        response=getProjectSteps.getProject(getAccessToken(),path+"/"+invalid_id);
        getProjectSteps.verifyStatus(404,response);
    }

    @Test(description = "Get a project without id")
    public void G_07(){
        response=getProjectSteps.getProject(getAccessToken(),path+"/");
        getProjectSteps.verifyStatus(200,response);
    }

    @Test(description = "Get a project with invalid method")
    public void G_10(){
        response=getProjectSteps.getProjectWithInvalidMethod(getAccessToken(),path+"/"+id);
        getProjectSteps.verifyStatus(405,response);
    }
}
