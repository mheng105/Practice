package com.vmo.training.demo.basetests.assignment2a;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

import static com.vmo.training.demo.handles.ResponseHandles.sendPostMethodWithoutToken;
import static com.vmo.training.demo.utils.JsonUtils.*;


public class ProjectBaseTest{

    protected static String id;
    protected Response response;

    @BeforeMethod
    public void init(){
        RestAssured.baseURI="https://api.todoist.com";
    }

//    public String getId(){
//        Response response=given()
//                .header("Authorization", "Bearer " + getAccessToken())
//                .when()
//                .get();
//        Assert.assertEquals(response.statusCode(),200);
//        response.prettyPrint();
//        List<Integer> jsonResponse = response.jsonPath().getList("id");
//        return String.valueOf(jsonResponse.get(0));
//    }

//    public String getId(){
//        response= responseHandles.sendGetMethod(getAccessToken(),path);
//        Assert.assertEquals(response.statusCode(),200);
//        List re=response.as(List.class);
//        String object=new Gson().toJson(re.get(1));
//        JsonObject jObject=new Gson().fromJson(object,JsonObject.class);
//        return String.valueOf(jObject.get("id"));
//    }


}
