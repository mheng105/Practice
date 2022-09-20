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

public class UpdateProjectSteps {
    @Step("Get accessToken successfully")
    public String getAccessTokenSuccessfully(){
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

    @Step("Update a project")
    public Response updateProjectWithValidAccessToken(Map<String,Object> map, String path){
        return sendPostMethod(map,getAccessTokenSuccessfully(),path);
    }

    @Step("Update a project with invalid accessToken")
    public Response updateProjectWithInvalidToken(Map<String,Object> map, String path){
        return sendPostMethod(map,invalid_accessToken,path);
    }

    @Step("Update a project when getting accessToken with invalid url")
    public Response updateProjectWithInvalidUrlToken(Map<String,Object> map, String path){
        return sendPostMethod(map,getAccessTokenFail(),path);
    }

    @Step("Update a project without accessToken")
    public Response updateProjectWithoutAccessToken(Map map,String path){
        return sendPostMethodWithoutToken(map,path);
    }

    @Step("Update project with invalid method")
    public Response updateProjectWithInvalidMethod(Map map,String path){
        return sendPutMethod(map,getAccessTokenSuccessfully(),path);
    }

    @Step("Show pretty")
    public void showPretty(Response response){
        response.prettyPrint();
    }

    @Step("Verify status code")
    public UpdateProjectSteps verifyStatus(int exceptedCode, Response response){
        showPretty(response);
        Assert.assertEquals(response.getStatusCode(),exceptedCode);
        return this;
    }
}
