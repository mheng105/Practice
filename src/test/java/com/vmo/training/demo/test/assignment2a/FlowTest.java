package com.vmo.training.demo.test.assignment2a;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vmo.training.demo.basetests.assignment2a.ProjectBaseTest;
import com.vmo.training.demo.microservices.steps.assignment2a.CreateProjectSteps;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;


public class FlowTest extends ProjectBaseTest {
    CreateProjectSteps project;
    @Test(priority = 0)
    public void createProject(){
        Map<Object,String> map=new HashMap<>();
        map.put("name","C5 Project Test1");

        response=responseHandles.sendPostMethod(map,getAccessToken(),path);
        Assert.assertEquals(response.statusCode(),200);
        response.prettyPrint();

//        response=(Response) project.sendRequestCreateProject(getAccessToken(),path).verifyStatusCode().printPretty();
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
        response=responseHandles.sendGetMethod(getAccessToken(),path+"/"+id);
        Assert.assertEquals(response.statusCode(),200);
        response.prettyPrint();
    }

    @Test(priority = 2)
    public void updateProject(){
        Map<Object,String> map=new HashMap<>();
        map.put("name","C5 Project Update1");
        response=responseHandles.sendPostMethod(map,getAccessToken(),path+"/"+id);
        Assert.assertEquals(response.statusCode(),204);
        response.prettyPrint();
    }

    @Test(priority = 3)
    public void getAllProject(){
        response=responseHandles.sendGetMethod(getAccessToken(),path);
        Assert.assertEquals(response.statusCode(),200);
        response.prettyPrint();
        id=String.valueOf(getId(response));
    }

    @Test(priority = 4)
    public void deleteProject(){
        response=responseHandles.sendDeleteMethod(getAccessToken(),path+"/"+id);
        Assert.assertEquals(response.statusCode(),204);
        response.prettyPrint();
    }
}
