package com.vmo.training.demo.microservices.steps.assignment2a;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

import static com.vmo.training.demo.handles.ResponseHandles.*;
import static com.vmo.training.demo.microservices.constants.Constant.TodoIst.*;


public class UpdateProjectSteps extends BaseSteps {

    @Step("Update a project")
    public Response updateProjectWithValidAccessToken(Map<String, Object> map, String path) {
        return sendPostMethod(map, accessToken, path);
    }

    @Step("Update a project with invalid accessToken")
    public Response updateProjectWithInvalidToken(Map<String, Object> map, String path) {
        return sendPostMethod(map, invalid_accessToken, path);
    }

    @Step("Update a project when getting accessToken with invalid url")
    public Response updateProjectWithInvalidUrlToken(Map<String, Object> map, String path) {
        return sendPostMethod(map, getAccessTokenFail(), path);
    }

    @Step("Update a project without accessToken")
    public Response updateProjectWithoutAccessToken(Map<String,Object> map, String path) {
        return sendPostMethodWithoutToken(map, path);
    }

    @Step("Update project with invalid method")
    public Response updateProjectWithInvalidMethod(Map<String,Object> map, String path) {
        return sendPutMethod(map, accessToken, path);
    }

    @Step("Show pretty")
    public void showPretty(Response response) {
        response.prettyPrint();
    }

    @Step("Verify status code")
    public UpdateProjectSteps verifyStatus(int exceptedCode, Response response) {
        showPretty(response);
        Assert.assertEquals(response.getStatusCode(), exceptedCode);
        return this;
    }

    @Step("Get id from projects")
    public String getIdProject() {
        Response response = sendGetMethod(accessToken, URL_PROJECT);
        List<Object> re = response.as(List.class);
        String object = new Gson().toJson(re.get(1));
        JsonObject jObject = new Gson().fromJson(object, JsonObject.class);
        return String.valueOf(jObject.get("id"));
    }

    public UpdateProjectSteps validateNumberProject(Map<String,Object> map, String path) {
        Response response = sendGetMethod(accessToken, path);
        List<Object> list = response.as(List.class);
        if (list.size() > 7) {
            sendDeleteMethod(accessToken, path + "/" + getIdProject());
        }
        return this;
    }
}
