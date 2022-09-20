package com.vmo.training.demo.microservices.steps.assignment2a;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static com.vmo.training.demo.handles.ResponseHandles.*;
import static com.vmo.training.demo.microservices.constants.Constant.*;
import static com.vmo.training.demo.utils.JsonUtils.*;

public class CreateProjectSteps {

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

    @Step("Create new project with valid accessToken")
    public Response createNewProjectWithValidToken(Map<String,Object> map,String path){
        return sendPostMethod(map,getAccessTokenSuccessfully(),path);
    }

    @Step("Create new project with valid accessToken")
    public Response createNewProjectWithInvalidToken(Map<String,Object> map,String path){
        return sendPostMethod(map,invalid_accessToken,path);
    }

    @Step("Create a new project when getting accessToken with invalid url")
    public Response createNewProjectWithInvalidUrl(Map<String,Object> map,String path){
        return sendPostMethod(map,getAccessTokenFail(),path);
    }

    @Step("Create a new project without accessToken")
    public Response createNewProjectWithoutAccessToken(Map<String,Object> map,String path){
        return sendPostMethodWithoutToken(map,path);
    }

    @Step("Create a new project with invalid method")
    public Response createNewProjectWithInvalidMethod(Map map,String path){
        return sendDeleteMethod(getAccessTokenSuccessfully(),path);
    }

    @Step("A project is created successfully")
    public CreateProjectSteps createProjectSuccessfully(int exceptedCode, Response response, String name){
        showPretty(response);
        Assert.assertEquals(response.getStatusCode(),exceptedCode);
        verifyName(response,name);
        verifyId(response);
        return this;
    }

    @Step("Verify name")
    public CreateProjectSteps verifyName(Response response, String name){
        String resString=response.asPrettyString();
        Assert.assertEquals(jsonValue(resString,"name"),name);
        return this;
    }

    @Step("Verify id")
    public CreateProjectSteps verifyId(Response response){
        String resString=response.asPrettyString();
        String id=jsonValue(resString,"id");
        assert jsonValue(resString,"url").contains(id);
        return this;
    }

    @Step("Verify status code")
    public CreateProjectSteps verifyStatus(int exceptedCode, Response response){
        showPretty(response);
        Assert.assertEquals(response.getStatusCode(),exceptedCode);
        return this;
    }

    @Step("Show pretty")
    public void showPretty(Response response){
        response.prettyPrint();
    }

}
