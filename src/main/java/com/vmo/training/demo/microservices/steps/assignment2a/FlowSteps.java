package com.vmo.training.demo.microservices.steps.assignment2a;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;

import static com.vmo.training.demo.handles.ResponseHandles.*;
import static com.vmo.training.demo.microservices.constants.Constant.*;
import static com.vmo.training.demo.utils.JsonUtils.jsonValue;

public class FlowSteps extends BaseSteps {
    @Step("Get id project after creating a new project")
    public String getCreatedId(Response response){
        String reString=response.asPrettyString();
        return jsonValue(reString,"id");
    }

    @Step("Get id from projects")
    public String getIdProject(){
        Response response = sendGetMethod(accessToken,URL_PROJECT);
        List re = response.as(List.class);
        String object = new Gson().toJson(re.get(1));
        JsonObject jObject = new Gson().fromJson(object, JsonObject.class);
        return String.valueOf(jObject.get("id"));
    }

    @Step("Validate number project")
    public FlowSteps validateNumberProject(String path){
        Response response=sendGetMethod(accessToken,path);
        List list=response.as(List.class);
        if(list.size()>7){
            sendDeleteMethod(accessToken,path+"/"+getIdProject());
        }
        return this;
    }
}
