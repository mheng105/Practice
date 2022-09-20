package com.vmo.training.demo.test.assignment2a;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vmo.training.demo.basetests.assignment2a.ProjectBaseTest;
import com.vmo.training.demo.microservices.steps.assignment2a.GetProjectSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.vmo.training.demo.microservices.constants.Constant.*;

public class GetProjectTest extends ProjectBaseTest {
    GetProjectSteps getProjectSteps=new GetProjectSteps();

    @BeforeMethod(description = "Get all project")
    @Step("Get one of id in project list")
    @Description("Get id")
    public void getId() {
        response = getProjectSteps.getProjectWithValidAccessToken(URL_PROJECT);
        Assert.assertEquals(response.statusCode(), 200);
        List re = response.as(List.class);
        String object = new Gson().toJson(re.get(1));
        JsonObject jObject = new Gson().fromJson(object, JsonObject.class);
        id= String.valueOf(jObject.get("id"));
    }
    @Test(description = "Get a project successfully")
    public void G_01(){
        response=getProjectSteps.getProjectWithValidAccessToken(URL_PROJECT+"/"+id);
        getProjectSteps.verifyStatus(200,response);
    }

    @Test(description = "Get a project with invalid accessToken")
    public void G_02(){
        response=getProjectSteps.getProjectWithInvalidToken(URL_PROJECT+"/"+id);
        getProjectSteps.verifyStatus(401,response);
    }

    @Test(description = "Get a project without accessToken")
    public void G_03(){
        response=getProjectSteps.getProjectWithoutAccessToken(URL_PROJECT);
        getProjectSteps.verifyStatus(401,response);
    }

    @Test(description = "Get a project when getting accessToken with invalid url")
    public void G_04(){
        response=getProjectSteps.getProjectWithValidUrlToken(URL_PROJECT+"/"+id);
        getProjectSteps.verifyStatus(404,response);
    }

    @Test(description = "Get a project with invalid url")
    public void G_05(){
        response=getProjectSteps.getProjectWithValidAccessToken(URL_PROJECT+"/a/abc/"+id);
        getProjectSteps.verifyStatus(404,response);
    }

    @Test(description = "Get a project with invalid id")
    public void G_06(){
        response=getProjectSteps.getProjectWithValidAccessToken(URL_PROJECT+"/"+invalid_id);
        getProjectSteps.verifyStatus(404,response);
    }

    @Test(description = "Get a project without id")
    public void G_07(){
        response=getProjectSteps.getProjectWithValidAccessToken(URL_PROJECT+"/");
        getProjectSteps.verifyStatus(200,response);
    }

    @Test(description = "Get a project with invalid method")
    public void G_10(){
        Map<String,Object> map=new HashMap<>();
        response=getProjectSteps.getProjectWithInvalidMethod(map,URL_PROJECT+"/"+id);
        getProjectSteps.verifyStatus(405,response);
    }
}
