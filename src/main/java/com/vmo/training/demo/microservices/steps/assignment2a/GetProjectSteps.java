package com.vmo.training.demo.microservices.steps.assignment2a;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

import static com.vmo.training.demo.handles.ResponseHandles.*;
import static com.vmo.training.demo.handles.ResponseHandles.sendPatchMethod;
import static com.vmo.training.demo.microservices.constants.Constant.*;
public class GetProjectSteps extends BaseSteps {

    @Step("Get a project")
    public Response getProjectWithValidAccessToken(String path){
        return sendGetMethod(accessToken,path);
    }

    @Step("Get a project when getting accessToken with invalid url")
    public Response getProjectWithValidUrlToken(String path){
        return sendGetMethod(getAccessTokenFail(),path);
    }

    @Step("Get a project with invalid accessToken")
    public Response getProjectWithInvalidToken(String path){
        return sendGetMethod(invalid_accessToken,path);
    }
    @Step("Get a project without accessToken")
    public Response getProjectWithoutAccessToken(String path){
        return sendGetMethodWithoutToken(path);
    }

    @Step("Get a project with invalid method")
    public Response getProjectWithInvalidMethod(Map map,String path){
        return sendPatchMethod(map,accessToken,path);
    }

    @Step("Verify status code")
    public GetProjectSteps verifyStatus(int exceptedCode, Response response){
        showPretty(response);
        Assert.assertEquals(response.getStatusCode(),exceptedCode);
        return this;
    }

    @Step("Show pretty")
    public void showPretty(Response response){
        response.prettyPrint();
    }

    @Step("Get id from projects")
    public String getIdProject(){
        Response response = sendGetMethod(accessToken,URL_PROJECT);
        List re = response.as(List.class);
        String object = new Gson().toJson(re.get(1));
        JsonObject jObject = new Gson().fromJson(object, JsonObject.class);
        return String.valueOf(jObject.get("id"));
    }
}
