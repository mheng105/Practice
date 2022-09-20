package com.vmo.training.demo.test.assignment2a;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vmo.training.demo.basetests.assignment2a.ProjectBaseTest;
import com.vmo.training.demo.microservices.steps.assignment2a.DeleteProjectSteps;
import com.vmo.training.demo.microservices.steps.assignment2a.GetProjectSteps;
import com.vmo.training.demo.microservices.steps.assignment2a.UpdateProjectSteps;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.vmo.training.demo.microservices.constants.Constant.*;


public class UpdateProjectTest extends ProjectBaseTest {
    UpdateProjectSteps updateProjectSteps=new UpdateProjectSteps();

    @BeforeMethod(description = "Get all project then getting id")
    @Step("Get id in project list")
    public void getId() {
        GetProjectSteps steps=new GetProjectSteps();
        response = steps.getProjectWithValidAccessToken(URL_PROJECT);
        Assert.assertEquals(response.statusCode(), 200);
        List re = response.as(List.class);
        String object = new Gson().toJson(re.get(2));
        JsonObject jObject = new Gson().fromJson(object, JsonObject.class);
        id= String.valueOf(jObject.get("id"));
    }

    @Test(description = "Update a project successfully")
    public void U_01(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","Update C5 Project");
        map.put("color",40);
        map.put("favorite",true);

        response=updateProjectSteps.updateProjectWithValidAccessToken(map,URL_PROJECT+"/"+id);
        updateProjectSteps.verifyStatus(204,response);
    }

    @Test(description = "Update a project with invalid accessToken")
    public void U_02(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project");

        response=updateProjectSteps.updateProjectWithInvalidToken(map,URL_PROJECT+"/"+id);
        updateProjectSteps.verifyStatus(401,response);
    }

    @Test(description = "Update a project with invalid id")
    public void U_03(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project");

        response=updateProjectSteps.updateProjectWithValidAccessToken(map,URL_PROJECT+"/"+invalid_id);
        updateProjectSteps.verifyStatus(400,response);
    }

    @Test(description = "Update a project with empty 'id'")
    public void U_04(){
        GetProjectSteps getSteps=new GetProjectSteps();
        DeleteProjectSteps deleteSteps=new DeleteProjectSteps();

        response=getSteps.getProjectWithValidAccessToken(URL_PROJECT);
        List list=response.as(List.class);
        String object = new Gson().toJson(list.get(2));
        JsonObject jObject = new Gson().fromJson(object, JsonObject.class);
        String id= String.valueOf(jObject.get("id"));
        if(list.size()>7){
            deleteSteps.deleteProject(URL_PROJECT+"/"+id);
        }
        
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project");
        response=updateProjectSteps.updateProjectWithValidAccessToken(map,URL_PROJECT+"/");
        updateProjectSteps.verifyStatus(200,response);
    }

    @Test(description = "Update a project when getting accessToken with invalid url")
    public void U_05(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project");

        response=updateProjectSteps.updateProjectWithInvalidUrlToken(map,URL_PROJECT+"/"+id);
        updateProjectSteps.verifyStatus(404,response);
    }

    @Test(description = "Update a project with invalid url")
    public void U_06(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project");

        response=updateProjectSteps.updateProjectWithValidAccessToken(map,URL_PROJECT+"/a/abc/"+id);
        updateProjectSteps.verifyStatus(404,response);
    }

    @Test(description = "Update a project with invalid name")
    public void U_07(){
        Map<String,Object> map=new HashMap<>();
        map.put("name",123456);

        response=updateProjectSteps.updateProjectWithValidAccessToken(map,URL_PROJECT+"/"+id);
        updateProjectSteps.verifyStatus(400,response);
    }

    @Test(description = "Update a project without accessToken")
    public void U_08(){
        Map<String,String> map=new HashMap<>();
        map.put("name","C5 Project");

        response=updateProjectSteps.updateProjectWithoutAccessToken(map,URL_PROJECT+"/"+id);
        updateProjectSteps.verifyStatus(401,response);
    }

    @Test(description = "Update a project with invalid method")
    public void U_09(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project");

        response=updateProjectSteps.updateProjectWithInvalidMethod(map,URL_PROJECT+"/"+id);
        updateProjectSteps.verifyStatus(405,response);
    }
}
