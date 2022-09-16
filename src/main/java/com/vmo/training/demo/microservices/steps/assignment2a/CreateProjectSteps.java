package com.vmo.training.demo.microservices.steps.assignment2a;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vmo.training.demo.handles.ResponseHandles;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class CreateProjectSteps extends ResponseHandles{

    static Response response;
    public Map setValue(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 project test1");
        return map;
    }

    @Step("Create project")
    public CreateProjectSteps sendRequestCreateProject(String accessToken,String path){
        response=sendPostMethod(setValue(),accessToken,path);
        return this;
    }
}
