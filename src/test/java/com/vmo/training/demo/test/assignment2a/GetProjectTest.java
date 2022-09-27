package com.vmo.training.demo.test.assignment2a;

import com.vmo.training.demo.basetests.assignment2a.ProjectBaseTest;
import com.vmo.training.demo.microservices.steps.assignment2a.GetProjectSteps;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.vmo.training.demo.microservices.constants.Constant.*;

@Listeners(TestListener.class)
public class GetProjectTest extends ProjectBaseTest {
    GetProjectSteps getProjectSteps=new GetProjectSteps();

    @Test(description = "Get a project successfully")
    public void G_01(){
        id= getProjectSteps.getIdProject();
        response=getProjectSteps.getProjectWithValidAccessToken(URL_PROJECT+"/"+id);
        getProjectSteps.verifyStatus(200,response);
    }

    @Test(description = "Get a project with invalid accessToken")
    public void G_02(){
        id= getProjectSteps.getIdProject();
        response=getProjectSteps.getProjectWithInvalidToken(URL_PROJECT+"/"+id);
        getProjectSteps.verifyStatus(401,response);
    }

    @Test(description = "Get a project without accessToken")
    public void G_03(){
        id= getProjectSteps.getIdProject();
        response=getProjectSteps.getProjectWithoutAccessToken(URL_PROJECT);
        getProjectSteps.verifyStatus(401,response);
    }

    @Test(description = "Get a project when getting accessToken with invalid url")
    public void G_04(){
        id= getProjectSteps.getIdProject();
        response=getProjectSteps.getProjectWithValidUrlToken(URL_PROJECT+"/"+id);
        getProjectSteps.verifyStatus(404,response);
    }

    @Test(description = "Get a project with invalid url")
    public void G_05(){
        id= getProjectSteps.getIdProject();
        response=getProjectSteps.getProjectWithValidAccessToken(URL_PROJECT+"/a/abc/"+id);
        getProjectSteps.verifyStatus(404,response);
    }

    @Test(description = "Get a project with invalid id")
    public void G_06(){
        id= getProjectSteps.getIdProject();
        response=getProjectSteps.getProjectWithValidAccessToken(URL_PROJECT+"/"+invalid_id);
        getProjectSteps.verifyStatus(404,response);
    }

    @Test(description = "Get a project without id")
    public void G_07(){
        id= getProjectSteps.getIdProject();
        response=getProjectSteps.getProjectWithValidAccessToken(URL_PROJECT+"/");
        getProjectSteps.verifyStatus(200,response);
    }

    @Test(description = "Get a project with invalid method")
    public void G_10(){
        id= getProjectSteps.getIdProject();
        Map<String,Object> map=new HashMap<>();
        response=getProjectSteps.getProjectWithInvalidMethod(map,URL_PROJECT+"/"+id);
        getProjectSteps.verifyStatus(405,response);
    }
}
