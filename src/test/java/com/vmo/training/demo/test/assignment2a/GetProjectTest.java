package com.vmo.training.demo.test.assignment2a;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vmo.training.demo.basetests.assignment2a.ProjectBaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class GetProjectTest extends ProjectBaseTest {
    @BeforeMethod(description = "Get all project")
    public void getId() {
        response = responseHandles.sendGetMethod(getAccessToken(), path);
        Assert.assertEquals(response.statusCode(), 200);
        List re = response.as(List.class);
        String object = new Gson().toJson(re.get(1));
        JsonObject jObject = new Gson().fromJson(object, JsonObject.class);
        id= String.valueOf(jObject.get("id"));
    }
    @Test(description = "Get a project successfully")
    public void G_01(){
        response=responseHandles.sendGetMethod(getAccessToken(),path+"/"+id);
        Assert.assertEquals(response.statusCode(),200);
        response.prettyPrint();
    }

    @Test(description = "Get a project with invalid accessToken")
    public void G_02(){
        response=responseHandles.sendGetMethod(invalid_accessToken,path+"/"+id);
        Assert.assertEquals(response.statusCode(),401);
        response.prettyPrint();
    }

    @Test(description = "Get a project without accessToken")
    public void G_03(){
        response=responseHandles.sendGetMethodWithoutToken(path+"/"+id);
        Assert.assertEquals(response.statusCode(),401);
        response.prettyPrint();
    }

    @Test(description = "Get a project when getting accessToken with invalid url")
    public void G_04(){
        response=responseHandles.sendGetMethod(getAccessTokenFail(),path+"/"+id);
        Assert.assertEquals(response.statusCode(),404);
    }

    @Test(description = "Get a project with invalid url")
    public void G_05(){
        response=responseHandles.sendGetMethod(getAccessToken(),path+"/abc"+id);
        Assert.assertEquals(response.statusCode(),404);
    }

    @Test(description = "Get a project with invalid id")
    public void G_06(){
        response=responseHandles.sendGetMethod(getAccessToken(),path+"/"+invalid_id);
        Assert.assertEquals(response.statusCode(),404);
    }

    @Test(description = "Get a project without id")
    public void G_07(){
        response=responseHandles.sendGetMethod(getAccessToken(),path+"/");
        Assert.assertEquals(response.statusCode(),200);
        response.prettyPrint();
    }

    @Test(description = "Get a project with invalid method")
    public void G_10(){
        response=given().header("Authorization","Bearer "+getAccessToken())
                .when()
                .patch(path+"/"+id);
        Assert.assertEquals(response.statusCode(),405);
    }
}
