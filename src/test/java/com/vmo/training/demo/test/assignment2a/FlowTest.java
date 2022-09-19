package com.vmo.training.demo.test.assignment2a;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vmo.training.demo.basetests.assignment2a.ProjectBaseTest;
import com.vmo.training.demo.microservices.steps.assignment2a.steps2a;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;


public class FlowTest extends ProjectBaseTest {
    steps2a steps=new steps2a();
    @Test(priority = 0)
    public void createProject(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project Test1");

        response=steps.createNewProject(map,getAccessToken(),path);
        steps.createProjectSuccessfully(200,response,(String)map.get("name"));
        id=getCreatedId(response);

    }

    public String getCreatedId(Response response){
        Object o=response.as(Object.class);
        String g=new Gson().toJson(o);
        JsonObject jObject=new Gson().fromJson(g,JsonObject.class);
        Object object=jObject.get("id");
        id=String.valueOf(object);
        return id;
    }

    @Test(priority = 1)
    public void getProject(){
        response=steps.getProject(getAccessToken(),path);
        steps.verifyStatus(200,response);
    }

    @Test(priority = 2)
    public void updateProject(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project Update1");

        response=steps.updateProject(map,getAccessToken(),path+"/"+id);
        steps.verifyStatus(204,response);
    }

    @Test(priority = 3)
    public void getAllProject(){
        response=steps.getProject(getAccessToken(),path);
        steps.verifyStatus(200,response);
        id=String.valueOf(getId(response));
    }

    @Test(priority = 4)
    public void deleteProject(){
        response=steps.deleteProject(getAccessToken(),path+"/"+id);
        steps.verifyStatus(204,response);
    }
}
