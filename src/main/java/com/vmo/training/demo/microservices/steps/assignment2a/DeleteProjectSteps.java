package com.vmo.training.demo.microservices.steps.assignment2a;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static com.vmo.training.demo.handles.ResponseHandles.*;
import static com.vmo.training.demo.microservices.constants.Constant.invalid_accessToken;
import static com.vmo.training.demo.utils.JsonUtils.getJsonObject;

public class DeleteProjectSteps {
    @Step("Get accessToken successfully")
    public String getAccessToken(){
        Map<String,String> map=new HashMap<>();
        map.put("email","mheng105@gmail.com");
        map.put("password","123456abc");

        Response response=sendPostMethodWithoutToken(map,"/API/v8.7/user/login");
        Assert.assertEquals(response.statusCode(),200);

        Object o=response.as(Object.class);
        String g=new Gson().toJson(o);
        JsonObject jObject=new Gson().fromJson(g,JsonObject.class);
        return String.valueOf(jObject.get("token"));
    }

    @Step("Get id")
    public Object getId(Response response){
        return getJsonObject(response).get("id");
    }


    @Step("Get accessToken unsuccessfully")
    public String getAccessTokenFail(){
        Map<String,String> map=new HashMap<>();
        map.put("email","mheng105@gmail.com");
        map.put("password","123456abc");

        Response response= sendPostMethodWithoutToken(map,"/abc");
        Assert.assertEquals(response.statusCode(),404);

        Object o=response.as(Object.class);
        String g=new Gson().toJson(o);
        JsonObject jObject=new Gson().fromJson(g,JsonObject.class);
        Object object=jObject.get("token");
        return String.valueOf(object);
    }

    @Step("Delete a project")
    public Response deleteProject(String path){
        return sendDeleteMethod(getAccessToken(),path);
    }

    @Step("Delete a project with invalid accessToken")
    public Response deleteProjectWithInvalidToken(String path){
        return sendDeleteMethod(invalid_accessToken,path);
    }

    @Step("Delete a project when getting accessToken with invalid url")
    public Response deleteProjectWithInvalidUrlToken(String path){
        return sendDeleteMethod(getAccessTokenFail(),path);
    }

    @Step("Delete a project without token")
    public Response deleteProjectWithoutAccessToken(String path){
        return sendDeleteMethodWithoutToken(path);
    }

    @Step("Delete a project with invalid method")
    public Response deleteProjectWithInvalidMethod(Map map, String path){
        return sendPutMethod(map,getAccessToken(),path);
    }

    @Step("Verify status code")
    public DeleteProjectSteps verifyStatus(int exceptedCode, Response response){
        showPretty(response);
        Assert.assertEquals(response.getStatusCode(),exceptedCode);
        return this;
    }

    @Step("Show pretty")
    public void showPretty(Response response){
        response.prettyPrint();
    }
}