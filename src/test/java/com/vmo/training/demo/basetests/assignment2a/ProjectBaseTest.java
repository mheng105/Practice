package com.vmo.training.demo.basetests.assignment2a;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.vmo.training.demo.handles.ResponseHandles.sendPostMethodWithoutToken;


public class ProjectBaseTest{

    protected static String id;
    protected Response response;

    @BeforeMethod
    public void init(){
        RestAssured.baseURI="https://api.todoist.com";
    }

    public String getAccessToken(){
        Map<String,String> map=new HashMap<>();
        map.put("email","mheng105@gmail.com");
        map.put("password","123456abc");

        response=sendPostMethodWithoutToken(map,"/API/v8.7/user/login");
        Assert.assertEquals(response.statusCode(),200);

        Object o=response.as(Object.class);
        String g=new Gson().toJson(o);
        JsonObject jObject=new Gson().fromJson(g,JsonObject.class);
        return String.valueOf(jObject.get("token"));
    }

    public JsonObject getJsonObject(Response response) {
        List re = response.as(List.class);
        JsonObject jObject=null;
        for (int i = 0; i < re.size(); i++) {
            String object = new Gson().toJson(re.get(i));
            jObject = new Gson().fromJson(object, JsonObject.class);
        }
        return jObject;
    }

    public Object getId(Response response){
        return getJsonObject(response).get("id");
    }

    public String getAccessTokenFail(){
        Map<String,String> map=new HashMap<>();
        map.put("email","mheng105@gmail.com");
        map.put("password","123456abc");

        response= sendPostMethodWithoutToken(map,"/abc");
        Assert.assertEquals(response.statusCode(),"404");

        Object o=response.as(Object.class);
        String g=new Gson().toJson(o);
        JsonObject jObject=new Gson().fromJson(g,JsonObject.class);
        Object object=jObject.get("token");
        return String.valueOf(object);
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
