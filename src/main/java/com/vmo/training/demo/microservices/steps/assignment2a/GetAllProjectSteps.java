package com.vmo.training.demo.microservices.steps.assignment2a;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;

import static com.vmo.training.demo.handles.ResponseHandles.*;
import static com.vmo.training.demo.handles.ResponseHandles.sendPatchMethod;
import static com.vmo.training.demo.microservices.constants.Constant.TodoIst.*;

public class GetAllProjectSteps extends BaseSteps {

    @Step("Get projects")
    public Response getProjectWithValidAccessToken(String path) {
        return sendGetMethod(accessToken, path);
    }

    @Step("Get projects when getting accessToken with invalid url")
    public Response getProjectWithValidUrlToken(String path) {
        return sendGetMethod(getAccessTokenFail(), path);
    }

    @Step("Get projects with invalid accessToken")
    public Response getProjectWithInvalidToken(String path) {
        return sendGetMethod(invalid_accessToken, path);
    }

    @Step("Get projects without accessToken")
    public Response getProjectWithoutAccessToken(String path) {
        return sendGetMethodWithoutToken(path);
    }

    @Step("Get all projects with invalid method")
    public Response getProjectWithInvalidMethod(Map<String, Object> map, String path) {
        return sendPatchMethod(map, accessToken, path);
    }

    @Step("Verify status code")
    public GetAllProjectSteps verifyStatus(int exceptedCode, Response response) {
        showPretty(response);
        Assert.assertEquals(response.getStatusCode(), exceptedCode);
        return this;
    }

    @Step("Show pretty")
    public void showPretty(Response response) {
        response.prettyPrint();
    }
}
