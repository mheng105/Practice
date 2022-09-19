package com.vmo.training.demo.test.assignment2a;

import com.vmo.training.demo.basetests.assignment2a.ProjectBaseTest;
import com.vmo.training.demo.microservices.steps.assignment2a.steps2a;
import org.testng.annotations.Test;


import static com.vmo.training.demo.microservices.constants.Constant.*;


public class GetProjectListTest extends ProjectBaseTest {

    steps2a getProjectsSteps=new steps2a();
    @Test(description="Get all projects successfully")
    public void getAll_01(){
        response= getProjectsSteps.getProject(getAccessToken(),URL_PROJECT+"/");
        getProjectsSteps.verifyStatus(200,response);
    }

    @Test(description = "Get all projects with invalid accessToken")
    public void getAll_02(){
        response= getProjectsSteps.getProject(invalid_accessToken, URL_PROJECT +"/");
        getProjectsSteps.verifyStatus(401,response);
    }

    @Test(description = "Get all projects without accessToken")
    public void getAll_03(){
        response= getProjectsSteps.getProjectWithoutAccessToken(URL_PROJECT+"/");
        getProjectsSteps.verifyStatus(401,response);
    }

    @Test(description = "Get all projects when getting accessToken with invalid url")
    public void getAll_04(){
        response= getProjectsSteps.getProject(getAccessTokenFail(),URL_PROJECT+"/");
        getProjectsSteps.verifyStatus(404,response);
    }

    @Test(description = "Get all projects with invalid url")
    public void getAll_05(){
        response= getProjectsSteps.getProject(getAccessToken(),URL_PROJECT+"/a/abc");
        getProjectsSteps.verifyStatus(404,response);
    }
}
