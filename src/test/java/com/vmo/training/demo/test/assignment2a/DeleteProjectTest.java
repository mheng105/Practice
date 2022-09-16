package com.vmo.training.demo.test.assignment2a;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vmo.training.demo.basetests.assignment2a.ProjectBaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class DeleteProjectTest extends ProjectBaseTest {

    @BeforeMethod(description = "Get all project then getting id of a project")
    public void getId() {
        response = responseHandles.sendGetMethod(getAccessToken(), path);
        Assert.assertEquals(response.statusCode(), 200);
        List re = response.as(List.class);
        String object = new Gson().toJson(re.get(2));
        JsonObject jObject = new Gson().fromJson(object, JsonObject.class);
        id= String.valueOf(jObject.get("id"));
    }
    @Test(description = "Delete a project successfully")
    public void D_01(){
        response=responseHandles.sendDeleteMethod(getAccessToken(),path+"/"+id);
        Assert.assertEquals(response.statusCode(),204);
    }

    @Test(description = "Delete a project with invalid accessToken")
    public void D_02(){
        response=responseHandles.sendDeleteMethod(invalid_accessToken,path+"/"+id);
        Assert.assertEquals(response.statusCode(),401);
        response.prettyPrint();
    }

    @Test(description = "Delete a project with invalid id")
    public void D_03(){
        response= responseHandles.sendDeleteMethod(getAccessToken(),path+"/"+invalid_id);
        Assert.assertEquals(response.statusCode(),400);
        response.prettyPrint();
    }

    @Test(description = "Delete a project without accessToken")
    public void D_04(){
        response=responseHandles.sendDeleteMethodWithoutToken(path+"/"+id);
        Assert.assertEquals(response.statusCode(),401);
        response.prettyPrint();
    }

    @Test(description = "Delete a project with empty 'id'")
    public void D_05(){
        response =responseHandles.sendDeleteMethod(getAccessToken(),path+"/");
        Assert.assertEquals(response.statusCode(),405);
        response.prettyPrint();
    }

    @Test(description = "Delete a project when getting accessToken with invalid url")
    public void D_06(){
        response=responseHandles.sendDeleteMethod(getAccessTokenFail(),path+"/"+id);
        Assert.assertEquals(response.statusCode(),405);
    }

    @Test(description = "Delete a project when getting accessToken with invalid url")
    public void D_07(){
        response=responseHandles.sendDeleteMethod(getAccessToken(),path+"/abc/"+id);
        Assert.assertEquals(response.statusCode(),404);
        response.prettyPrint();
    }

    @Test(description = "Delete a project with invalid method")
    public void D_08(){
        response=given().header("Authorization","Bearer "+getAccessToken())
                .when().put(path);
        Assert.assertEquals(response.statusCode(),405);
        response.prettyPrint();
    }
}
