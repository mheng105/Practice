package com.vmo.training.demo.microservices.steps.assignment2a;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

import static com.vmo.training.demo.handles.ResponseHandles.*;
import static com.vmo.training.demo.microservices.constants.Constant.*;
import static com.vmo.training.demo.utils.JsonUtils.*;

public class CreateProjectSteps extends BaseSteps {

    @Step("Delete project when maximum projects")
    public CreateProjectSteps deleteProjectWhenMaximumProjects(Map map){
        Response response=sendGetMethod(accessToken,URL_PROJECT);
        List list=response.as(List.class);
        if(list.size()<2) {
            sendPostMethod(map,accessToken,URL_PROJECT);
        }else {
            String object = new Gson().toJson(list.get(1));
            JsonObject jObject = new Gson().fromJson(object, JsonObject.class);
            String id = String.valueOf(jObject.get("id"));
            if (list.size() > 7) {
                sendDeleteMethod( accessToken,URL_PROJECT + "/" + id);
            }
        }
        return this;
    }

    @Step("Get id")
    public Object getId(Response response){
        return getJsonObject(response).get("id");
    }

    @Step("Create new project with valid accessToken")
    public Response createNewProjectWithValidToken(Map<String,Object> map,String path){
        return sendPostMethod(map,accessToken,path);
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
    public Response createNewProjectWithInvalidMethod(Map map,String accessToken,String path){
        return sendDeleteMethod(accessToken,path);
    }

    @Step("A project is created successfully")
    public CreateProjectSteps createProjectSuccessfully(int exceptedCode, Response response, String name){
        showPretty(response);
        Assert.assertEquals(response.getStatusCode(),exceptedCode);
        verifyName(response,name);
        verifyId(response);
        return this;
    }

    public CreateProjectSteps validateNumberProject(Map map,String path){
        Response response=sendGetMethod(getAccessTokenSuccessfully(),path);
        List list=response.as(List.class);
        if(list.size()<=7){
                response = createNewProjectWithValidToken(map,path);
        }else {
            response = createNewProjectWithValidToken(map, path);
            verifyStatus(403, response);
        }
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
