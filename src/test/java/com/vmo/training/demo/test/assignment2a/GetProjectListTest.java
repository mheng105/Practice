package com.vmo.training.demo.test.assignment2a;

import com.vmo.training.demo.basetests.assignment2a.ProjectBaseTest;
import com.vmo.training.demo.microservices.steps.assignment2a.steps2a;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class GetProjectListTest extends ProjectBaseTest {

    steps2a getProjectsSteps=new steps2a();
    @Test(description="Get all projects successfully")
    public void getAll_01(){
        response= getProjectsSteps.getProject(getAccessToken(),path+"/");
        getProjectsSteps.verifyStatus(200,response);
    }

    @Test(description = "Get all projects with invalid accessToken")
    public void getAll_02(){
        response= getProjectsSteps.getProject(invalid_accessToken,path+"/");
        getProjectsSteps.verifyStatus(401,response);
    }

    @Test(description = "Get all projects without accessToken")
    public void getAll_03(){
        response= getProjectsSteps.getProjectWithoutAccessToken(path+"/");
        getProjectsSteps.verifyStatus(401,response);
    }

    @Test(description = "Get all projects when getting accessToken with invalid url")
    public void getAll_04(){
        response= getProjectsSteps.getProject(getAccessTokenFail(),path+"/");
        getProjectsSteps.verifyStatus(404,response);
    }

    @Test(description = "Get all projects with invalid url")
    public void getAll_05(){
        response= getProjectsSteps.getProject(getAccessToken(),path+"/a/abc");
        getProjectsSteps.verifyStatus(404,response);
    }
}
