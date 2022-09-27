package com.vmo.training.demo.test.assignment2a;

import com.vmo.training.demo.basetests.assignment2a.ProjectBaseTest;
import com.vmo.training.demo.microservices.steps.assignment2a.DeleteProjectSteps;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

import static com.vmo.training.demo.microservices.constants.Constant.*;

@Listeners(TestListener.class)
public class DeleteProjectTest extends ProjectBaseTest {
    DeleteProjectSteps deleteProjectSteps=new DeleteProjectSteps();

    @Test(description = "Delete a project successfully")
    public void D_01(){
        id= deleteProjectSteps.getIdProject();
        response=deleteProjectSteps.deleteProject(URL_PROJECT+"/"+id);
        deleteProjectSteps.verifyStatus(204,response);
        deleteProjectSteps.getAllProject(URL_PROJECT);
    }

    @Test(description = "Delete a project with invalid accessToken")
    public void D_02(){
        id=deleteProjectSteps.getIdProject();
        response=deleteProjectSteps.deleteProjectWithInvalidToken(URL_PROJECT+"/"+id);
        deleteProjectSteps.verifyStatus(401,response);
        deleteProjectSteps.getAllProject(URL_PROJECT);
    }

    @Test(description = "Delete a project with invalid id")
    public void D_03(){
        id=deleteProjectSteps.getIdProject();
        response=deleteProjectSteps.deleteProject(URL_PROJECT+"/"+invalid_id);
        deleteProjectSteps.verifyStatus(400,response);
        deleteProjectSteps.getAllProject(URL_PROJECT);
    }

    @Test(description = "Delete a project without accessToken")
    public void D_04(){
        id=deleteProjectSteps.getIdProject();
        response= deleteProjectSteps.deleteProjectWithoutAccessToken(URL_PROJECT+"/"+id);
        deleteProjectSteps.verifyStatus(401,response);
        deleteProjectSteps.getAllProject(URL_PROJECT);
    }

    @Test(description = "Delete a project with empty 'id'")
    public void D_05(){
        response=deleteProjectSteps.deleteProject(URL_PROJECT+"/");
        deleteProjectSteps.verifyStatus(405,response);
        deleteProjectSteps.getAllProject(URL_PROJECT);
    }

    @Test(description = "Delete a project when getting accessToken with invalid url")
    public void D_06(){
        id=deleteProjectSteps.getIdProject();
        response=deleteProjectSteps.deleteProjectWithInvalidUrlToken(URL_PROJECT+"/"+id);
        deleteProjectSteps.verifyStatus(404,response);
        deleteProjectSteps.getAllProject(URL_PROJECT);
    }

    @Test(description = "Delete a project with invalid url")
    public void D_07(){
        id=deleteProjectSteps.getIdProject();
        response=deleteProjectSteps.deleteProject(URL_PROJECT+"/a/abc/"+id);
        deleteProjectSteps.verifyStatus(404,response);
        deleteProjectSteps.getAllProject(URL_PROJECT);
    }

    @Test(description = "Delete a project with invalid method")
    public void D_08(){
        id=deleteProjectSteps.getIdProject();
        System.out.println(id);
        Map<String,Object> map=new HashMap<>();
        response=deleteProjectSteps.deleteProjectWithInvalidMethod(map,URL_PROJECT+"/"+id);
        deleteProjectSteps.verifyStatus(405,response);
        deleteProjectSteps.getAllProject(URL_PROJECT);
    }
}
