package com.vmo.training.demo.test.assignment2a;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vmo.training.demo.basetests.assignment2a.ProjectBaseTest;
import com.vmo.training.demo.microservices.steps.assignment2a.DeleteProjectSteps;
import com.vmo.training.demo.microservices.steps.assignment2a.GetAllProjectSteps;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.vmo.training.demo.microservices.constants.Constant.*;


public class DeleteProjectTest extends ProjectBaseTest {
    DeleteProjectSteps deleteProjectSteps=new DeleteProjectSteps();
    GetAllProjectSteps getAllProjectSteps=new GetAllProjectSteps();
    @BeforeMethod(description = "Get all project then getting id of a project")
    @Step("Get project id")
    public void getId() {
        response = getAllProjectSteps.getProjectWithValidAccessToken(URL_PROJECT);
        Assert.assertEquals(response.statusCode(), 200);
        List re = response.as(List.class);
        String object = new Gson().toJson(re.get(2));
        JsonObject jObject = new Gson().fromJson(object, JsonObject.class);
        id= String.valueOf(jObject.get("id"));
    }

    public void getAllProject(){
        response=getAllProjectSteps.getProjectWithValidAccessToken(URL_PROJECT);
        getAllProjectSteps.showPretty(response);
    }
    @Test(description = "Delete a project successfully")
    public void D_01(){
        response=deleteProjectSteps.deleteProject(URL_PROJECT+"/"+id);
        deleteProjectSteps.verifyStatus(204,response);
        getAllProject();
    }

    @Test(description = "Delete a project with invalid accessToken")
    public void D_02(){
        response=deleteProjectSteps.deleteProjectWithInvalidToken(URL_PROJECT+"/"+id);
        deleteProjectSteps.verifyStatus(401,response);
        getAllProject();
    }

    @Test(description = "Delete a project with invalid id")
    public void D_03(){
        response=deleteProjectSteps.deleteProject(URL_PROJECT+"/"+invalid_id);
        deleteProjectSteps.verifyStatus(400,response);
        getAllProject();
    }

    @Test(description = "Delete a project without accessToken")
    public void D_04(){
        response= deleteProjectSteps.deleteProjectWithoutAccessToken(URL_PROJECT+"/"+id);
        deleteProjectSteps.verifyStatus(401,response);
        getAllProject();
    }

    @Test(description = "Delete a project with empty 'id'")
    public void D_05(){
        response=deleteProjectSteps.deleteProject(URL_PROJECT+"/");
        deleteProjectSteps.verifyStatus(405,response);
        getAllProject();
    }

    @Test(description = "Delete a project when getting accessToken with invalid url")
    public void D_06(){
        response=deleteProjectSteps.deleteProjectWithInvalidUrlToken(URL_PROJECT+"/"+id);
        deleteProjectSteps.verifyStatus(404,response);
        getAllProject();
    }

    @Test(description = "Delete a project with invalid url")
    public void D_07(){
        response=deleteProjectSteps.deleteProject(URL_PROJECT+"/a/abc/"+id);
        deleteProjectSteps.verifyStatus(404,response);
        getAllProject();
    }

    @Test(description = "Delete a project with invalid method")
    public void D_08(){
        Map<String,Object> map=new HashMap<>();
        response=deleteProjectSteps.deleteProjectWithInvalidMethod(map,URL_PROJECT+"/"+id);
        deleteProjectSteps.verifyStatus(405,response);
        getAllProject();
    }
}
