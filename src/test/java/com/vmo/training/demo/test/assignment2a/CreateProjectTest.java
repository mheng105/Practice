package com.vmo.training.demo.test.assignment2a;
import com.vmo.training.demo.basetests.assignment2a.ProjectBaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateProjectTest extends ProjectBaseTest {

    @Test(description = "Create a new project successfully")
    public void C_01(){
        Map<String,String> map=new HashMap<>();
        map.put("name","C5 Project");

        Response response=responseHandles.sendPostMethod(map,getAccessToken(),path);
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test(description = "Create a new project without 'name' field")
    public void C_02(){
        Map<String,Object> map=new HashMap<>();
        map.put("color",40);
        map.put("favorite",true);

        Response response=responseHandles.sendPostMethod(map,getAccessToken(),path);
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),400);
    }

    @Test(description = "Create a new project with invalid accessToken")
    public void C_03(){
        Map<String,String> map=new HashMap<>();
        map.put("name","C5 project");

        Response response=responseHandles.sendPostMethod(map,invalid_accessToken,path);
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),401);
    }

    @Test(description = "Create a new project without accessToken")
    public void C_04(){
        Map<String,String> map=new HashMap<>();
        map.put("name","C5 project");

        Response response=responseHandles.sendPostMethod(map,"",path);
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),401);
    }

    @Test(description = "Create a new project when getting accessToken with invalid url")
    public void C_05(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project");
        map.put("color",40);
        map.put("favorite",true);

        Response response=responseHandles.sendPostMethod(map,getAccessTokenFail(),path);
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),404);
    }
    @Test(description = "Create a new project with invalid url")
    public void C_06(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project");
        map.put("color",40);
        map.put("favorite",true);

        Response response=responseHandles.sendPostMethod(map,getAccessToken(),path+"/abc");
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),404);
    }

    @Test(description = "Create a new project with invalid 'name' field")
    public void C_07(){
        Map<String,Object> map=new HashMap<>();
        map.put("name",123456);
        map.put("color",40);
        map.put("favorite",true);

        Response response=responseHandles.sendPostMethod(map,getAccessToken(),path);
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),400);
    }

    @Test(description = "Create a new project with empty 'name' field")
    public void C_08(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","");
        map.put("color",40);
        map.put("favorite",true);

        Response response=responseHandles.sendPostMethod(map,getAccessToken(),path);
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),400);
    }

    @Test(description = "Create a new project with invalid method")
    public void C_09(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project");
        map.put("color",40);
        map.put("favorite",true);

        Response response=given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getAccessToken())
                .when()
                .body(map)
                .delete(path);
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),405);
    }

    @Test(description = "Create a new project when existed maximum projects")
    public void C_10(){
        Map<String,String> map=new HashMap<>();
        map.put("name","C5");

        Response response=responseHandles.sendPostMethod(map,getAccessToken(),path);
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),403);
    }
}
