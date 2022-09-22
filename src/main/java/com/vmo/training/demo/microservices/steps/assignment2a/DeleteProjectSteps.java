package com.vmo.training.demo.microservices.steps.assignment2a;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

import static com.vmo.training.demo.handles.ResponseHandles.*;
import static com.vmo.training.demo.microservices.constants.Constant.URL_PROJECT;
import static com.vmo.training.demo.microservices.constants.Constant.invalid_accessToken;

public class DeleteProjectSteps extends BaseSteps {

    @Step("Delete a project")
    public Response deleteProject(String path){
        return sendDeleteMethod(getAccessTokenSuccessfully(),path);
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
        return sendPutMethod(map,getAccessTokenSuccessfully(),path);
    }

    @Step("Verify status code")
    public DeleteProjectSteps verifyStatus(int exceptedCode, Response response){
        showPretty(response);
        Assert.assertEquals(response.getStatusCode(),exceptedCode);
        return this;
    }

    @Step("Get all project")
    public DeleteProjectSteps getAllProject(String path){
        Response response=sendGetMethod(getAccessTokenSuccessfully(),path);
        showPretty(response);
        return this;
    }
    @Step("Show pretty")
    public void showPretty(Response response){
        response.prettyPrint();
    }

    @Step("Get id from projects")
    public String getIdProject(){
        Response response = sendGetMethod(getAccessTokenSuccessfully(),URL_PROJECT);
        List re = response.as(List.class);
        String object = new Gson().toJson(re.get(1));
        JsonObject jObject = new Gson().fromJson(object, JsonObject.class);
        return String.valueOf(jObject.get("id"));
    }
}
