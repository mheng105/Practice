package com.vmo.training.demo.test.assignment2a;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vmo.training.demo.basetests.assignment2a.ProjectBaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class UpdateProjectTest extends ProjectBaseTest {
    @BeforeMethod(description = "Get all project then getting id")
    public void getId() {
        response = responseHandles.sendGetMethod(getAccessToken(), path);
        Assert.assertEquals(response.statusCode(), 200);
        List re = response.as(List.class);
        String object = new Gson().toJson(re.get(2));
        JsonObject jObject = new Gson().fromJson(object, JsonObject.class);
        id= String.valueOf(jObject.get("id"));
    }

    @Test(description = "Update a project successfully")
    public void U_01(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project");
        map.put("color",40);
        map.put("favorite",true);

        Response response=responseHandles.sendPostMethod(map,getAccessToken(),path+"/"+id);
        Assert.assertEquals(response.statusCode(),204);
        response.prettyPrint();
    }

    @Test(description = "Update a project with invalid accessToken")
    public void U_02(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project");

        Response response=responseHandles.sendPostMethod(map,invalid_accessToken,path+"/"+id);
        Assert.assertEquals(response.statusCode(),401);
        response.prettyPrint();
    }

    @Test(description = "Update a project with invalid id")
    public void U_03(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project");

        Response response=responseHandles.sendPostMethod(map,getAccessToken(),path+"/"+invalid_id);
        Assert.assertEquals(response.statusCode(),400);
        response.prettyPrint();
    }

    @Test(description = "Update a project with empty 'id'")
    public void U_04(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project");

        Response response=responseHandles.sendPostMethod(map,getAccessToken(),path+"/");
        Assert.assertEquals(response.statusCode(),200);
        response.prettyPrint();
    }

    @Test(description = "Update a project when getting accessToken with invalid url")
    public void U_05(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project");

        Response response=responseHandles.sendPostMethod(map,getAccessTokenFail(),path+"/"+id);
        Assert.assertEquals(response.statusCode(),404);
        response.prettyPrint();
    }

    @Test(description = "Update a project with invalid url")
    public void U_06(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project");

        Response response=responseHandles.sendPostMethod(map,getAccessToken(),path+"/abc/"+id);
        Assert.assertEquals(response.statusCode(),404);
        response.prettyPrint();
    }

    @Test(description = "Update a project with invalid name")
    public void U_07(){
        Map<String,Object> map=new HashMap<>();
        map.put("name",123456);

        Response response=responseHandles.sendPostMethod(map,getAccessToken(),path+"/"+id);
        Assert.assertEquals(response.statusCode(),400);
        response.prettyPrint();
    }

    @Test(description = "Update a project without accessToken")
    public void U_08(){
        Map<String,String> map=new HashMap<>();
        map.put("name","C5 Project");

        Response response=responseHandles.sendPostMethodWithoutToken(map,path+"/"+id);
        Assert.assertEquals(response.statusCode(),401);
        response.prettyPrint();
    }

    @Test(description = "Update a project with invalid method")
    public void U_09(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project");

        Response response=given().header("Authorization","Bearer "+getAccessToken())
                .contentType(ContentType.JSON)
                .when()
                .body(map)
                .put(path+"/"+id);
        Assert.assertEquals(response.statusCode(),405);
        response.prettyPrint();
    }
}
