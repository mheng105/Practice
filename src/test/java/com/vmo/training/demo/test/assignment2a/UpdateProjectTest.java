package com.vmo.training.demo.test.assignment2a;

import com.vmo.training.demo.basetests.assignment2a.ProjectBaseTest;
import com.vmo.training.demo.microservices.steps.assignment2a.UpdateProjectSteps;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.vmo.training.demo.microservices.constants.Constant.*;


public class UpdateProjectTest extends ProjectBaseTest {
    UpdateProjectSteps updateProjectSteps=new UpdateProjectSteps();

    @Test(description = "Update a project successfully")
    public void U_01(){
        id= updateProjectSteps.getIdProject();
        Map<String,Object> map=new HashMap<>();
        map.put("name","Update C5 Project");
        map.put("color",40);
        map.put("favorite",true);

        response=updateProjectSteps.updateProjectWithValidAccessToken(map,URL_PROJECT+"/"+id);
        updateProjectSteps.verifyStatus(204,response);
    }

    @Test(description = "Update a project with invalid accessToken")
    public void U_02(){
        id= updateProjectSteps.getIdProject();
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project");

        response=updateProjectSteps.updateProjectWithInvalidToken(map,URL_PROJECT+"/"+id);
        updateProjectSteps.verifyStatus(401,response);
    }

    @Test(description = "Update a project with invalid id")
    public void U_03(){
        id= updateProjectSteps.getIdProject();
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project");

        response=updateProjectSteps.updateProjectWithValidAccessToken(map,URL_PROJECT+"/"+invalid_id);
        updateProjectSteps.verifyStatus(400,response);
    }

    @Test(description = "Update a project with empty 'id'")
    public void U_04(){
        id= updateProjectSteps.getIdProject();
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project4");
        updateProjectSteps.validateNumberProject(map,URL_PROJECT);
        response=updateProjectSteps.updateProjectWithValidAccessToken(map,URL_PROJECT+"/");
        updateProjectSteps.verifyStatus(200,response);
    }

    @Test(description = "Update a project when getting accessToken with invalid url")
    public void U_05(){
        id= updateProjectSteps.getIdProject();
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project");

        response=updateProjectSteps.updateProjectWithInvalidUrlToken(map,URL_PROJECT+"/"+id);
        updateProjectSteps.verifyStatus(404,response);
    }

    @Test(description = "Update a project with invalid url")
    public void U_06(){
        id= updateProjectSteps.getIdProject();
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project");

        response=updateProjectSteps.updateProjectWithValidAccessToken(map,URL_PROJECT+"/a/abc/"+id);
        updateProjectSteps.verifyStatus(404,response);
    }

    @Test(description = "Update a project with invalid name")
    public void U_07(){
        id= updateProjectSteps.getIdProject();
        Map<String,Object> map=new HashMap<>();
        map.put("name",123456);

        response=updateProjectSteps.updateProjectWithValidAccessToken(map,URL_PROJECT+"/"+id);
        updateProjectSteps.verifyStatus(400,response);
    }

    @Test(description = "Update a project without accessToken")
    public void U_08(){
        id= updateProjectSteps.getIdProject();
        Map<String,String> map=new HashMap<>();
        map.put("name","C5 Project");

        response=updateProjectSteps.updateProjectWithoutAccessToken(map,URL_PROJECT+"/"+id);
        updateProjectSteps.verifyStatus(401,response);
    }

    @Test(description = "Update a project with invalid method")
    public void U_09(){
        id= updateProjectSteps.getIdProject();
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project");

        response=updateProjectSteps.updateProjectWithInvalidMethod(map,URL_PROJECT+"/"+id);
        updateProjectSteps.verifyStatus(405,response);
    }
}
