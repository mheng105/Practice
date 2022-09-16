package com.vmo.training.demo.test.assignment2a;

import com.vmo.training.demo.basetests.assignment2a.ProjectBaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class GetProjectListTest extends ProjectBaseTest {

    @Test(description="Get all projects successfully")
    public void getAll_01(){
        Response response=responseHandles.sendGetMethod(getAccessToken(),path+"/");
        Assert.assertEquals(response.statusCode(),200);
        response.prettyPrint();
    }

    @Test(description = "Get all projects with invalid accessToken")
    public void getAll_02(){
        Response response=responseHandles.sendGetMethod(invalid_accessToken,path+"/");
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),401);
    }

    @Test(description = "Get all projects without accessToken")
    public void getAll_03(){
        Response response=responseHandles.sendGetMethodWithoutToken(path+"/");
        Assert.assertEquals(response.statusCode(),401);
        response.prettyPrint();
    }

    @Test(description = "Get all projects when getting accessToken with invalid url")
    public void getAll_04(){
        Response response=responseHandles.sendGetMethod(getAccessTokenFail(),path+"/");
        Assert.assertEquals(response.statusCode(),404);
        response.prettyPrint();
    }

    @Test(description = "Get all projects with invalid url")
    public void getAll_05(){
        Response response=responseHandles.sendGetMethod(getAccessToken(),path+"/abc");
        Assert.assertEquals(response.statusCode(),404);
        response.prettyPrint();
    }
}
