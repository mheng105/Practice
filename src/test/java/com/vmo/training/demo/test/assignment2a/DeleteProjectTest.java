package com.vmo.training.demo.test.assignment2a;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vmo.training.demo.basetests.assignment2a.ProjectBaseTest;
import com.vmo.training.demo.microservices.steps.assignment2a.steps2a;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.vmo.training.demo.handles.ResponseHandles.sendGetMethod;
import static com.vmo.training.demo.microservices.constants.Constant.*;


public class DeleteProjectTest extends ProjectBaseTest {
    steps2a deleteProjectSteps=new steps2a();
    @BeforeMethod(description = "Get all project then getting id of a project")
    @Step("Get project id")
    public void getId() {
        response = sendGetMethod(getAccessToken(), URL_PROJECT);
        Assert.assertEquals(response.statusCode(), 200);
        List re = response.as(List.class);
        String object = new Gson().toJson(re.get(2));
        JsonObject jObject = new Gson().fromJson(object, JsonObject.class);
        id= String.valueOf(jObject.get("id"));
    }
    @Test(description = "Delete a project successfully")
    public void D_01(){
        response=deleteProjectSteps.deleteProject(getAccessToken(),URL_PROJECT+"/"+id);
        deleteProjectSteps.verifyStatus(204,response);
    }

    @Test(description = "Delete a project with invalid accessToken")
    public void D_02(){
        response=deleteProjectSteps.deleteProject(invalid_accessToken,URL_PROJECT+"/"+id);
        deleteProjectSteps.verifyStatus(401,response);
    }

    @Test(description = "Delete a project with invalid id")
    public void D_03(){
        response=deleteProjectSteps.deleteProject(getAccessToken(),URL_PROJECT+"/"+invalid_id);
        deleteProjectSteps.verifyStatus(400,response);
    }

    @Test(description = "Delete a project without accessToken")
    public void D_04(){
        response= deleteProjectSteps.deleteProjectWithoutAccessToken(URL_PROJECT+"/"+id);
        deleteProjectSteps.verifyStatus(401,response);
    }

    @Test(description = "Delete a project with empty 'id'")
    public void D_05(){
        response=deleteProjectSteps.deleteProject(getAccessToken(),URL_PROJECT+"/");
        deleteProjectSteps.verifyStatus(405,response);
    }

    @Test(description = "Delete a project when getting accessToken with invalid url")
    public void D_06(){
        response=deleteProjectSteps.deleteProject(getAccessTokenFail(),URL_PROJECT+"/"+id);
        deleteProjectSteps.verifyStatus(405,response);
    }

    @Test(description = "Delete a project with invalid url")
    public void D_07(){
        response=deleteProjectSteps.deleteProject(getAccessToken(),URL_PROJECT+"/a/abc/"+id);
        deleteProjectSteps.verifyStatus(404,response);
    }

    @Test(description = "Delete a project with invalid method")
    public void D_08(){
        Map<String,Object> map=new HashMap<>();
        response=deleteProjectSteps.deleteProjectWithInvalidMethod(map,getAccessToken(),URL_PROJECT+"/"+id);
        deleteProjectSteps.verifyStatus(405,response);
    }
}
