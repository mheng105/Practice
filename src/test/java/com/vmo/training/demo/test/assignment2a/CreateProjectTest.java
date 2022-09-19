package com.vmo.training.demo.test.assignment2a;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vmo.training.demo.basetests.assignment2a.ProjectBaseTest;
import com.vmo.training.demo.microservices.steps.assignment2a.steps2a;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateProjectTest extends ProjectBaseTest {

    steps2a createProjectSteps = new steps2a();
    @Test(description = "Create a new project successfully")
    public void C_01(){
        response=createProjectSteps.getProject(getAccessToken(),path);
        List list=response.as(List.class);
        String object = new Gson().toJson(list.get(2));
        JsonObject jObject = new Gson().fromJson(object, JsonObject.class);
        String id= String.valueOf(jObject.get("id"));
        if(list.size()>7){
            createProjectSteps.deleteProject(getAccessToken(),path+"/"+id);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project3");

        response= createProjectSteps.createNewProject(map,getAccessToken(),path);
        createProjectSteps.createProjectSuccessfully(200,response,(String) map.get("name"));
    }

    @Test(description = "Create a new project without 'name' field")
    public void C_02(){
        Map<String,Object> map=new HashMap<>();
        map.put("color",40);
        map.put("favorite",true);

        response= createProjectSteps.createNewProject(map,getAccessToken(),path);
        createProjectSteps.verifyStatus(400,response);
    }

    @Test(description = "Create a new project with invalid accessToken")
    public void C_03(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 project");

        response=createProjectSteps.createNewProject(map,invalid_accessToken,path);
        createProjectSteps.verifyStatus(401,response);
    }

    @Test(description = "Create a new project without accessToken")
    public void C_04(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 project");

        response=createProjectSteps.createNewProjectWithoutAccessToken(map,path);
        createProjectSteps.verifyStatus(401,response);
    }

    @Test(description = "Create a new project when getting accessToken with invalid url")
    public void C_05(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project");
        map.put("color",40);
        map.put("favorite",true);

        response=createProjectSteps.createNewProject(map,getAccessTokenFail(),path);
        createProjectSteps.verifyStatus(404,response);
    }
    @Test(description = "Create a new project with invalid url")
    public void C_06(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project");
        map.put("color",40);
        map.put("favorite",true);

        response=createProjectSteps.createNewProject(map,getAccessToken(),path+"/a/abc");
        createProjectSteps.verifyStatus(404,response);
    }

    @Test(description = "Create a new project with invalid 'name' field")
    public void C_07(){
        Map<String,Object> map=new HashMap<>();
        map.put("name",123456);
        map.put("color",40);
        map.put("favorite",true);

        response=createProjectSteps.createNewProject(map,getAccessToken(),path);
        createProjectSteps.verifyStatus(400,response);
    }

    @Test(description = "Create a new project with empty 'name' field")
    public void C_08(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","");
        map.put("color",40);
        map.put("favorite",true);

        response=createProjectSteps.createNewProject(map,getAccessToken(),path);
        createProjectSteps.verifyStatus(400,response);
    }

    @Test(description = "Create a new project with invalid method")
    public void C_09(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project");
        map.put("color",40);
        map.put("favorite",true);

        response=createProjectSteps.createNewProjectWithDeleteMethod(map,getAccessToken(),path);
        createProjectSteps.verifyStatus(405,response);
    }

    @Test(description = "Create a new project when existed maximum projects")
    public void C_10(){
        response=createProjectSteps.getProject(getAccessToken(),path);
        List list=response.as(List.class);

            if(list.size()<=7){
                for(int i=0;i<7;i++) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", "C5");
                    response = createProjectSteps.createNewProject(map, getAccessToken(), path);
                }
            }else {
                Map<String, Object> map = new HashMap<>();
                map.put("name", "maximum projects");
                response = createProjectSteps.createNewProject(map, getAccessToken(), path);
                createProjectSteps.verifyStatus(403, response);
            }
    }
}
