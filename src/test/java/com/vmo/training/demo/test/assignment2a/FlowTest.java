package com.vmo.training.demo.test.assignment2a;

import com.vmo.training.demo.basetests.assignment2a.ProjectBaseTest;
import com.vmo.training.demo.microservices.steps.assignment2a.*;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.vmo.training.demo.microservices.constants.Constant.URL_PROJECT;



public class FlowTest extends ProjectBaseTest {
    @Test(priority = 0)
    public void createProject(){
        FlowSteps flowSteps=new FlowSteps();
        CreateProjectSteps steps=new CreateProjectSteps();
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project Test1");

        flowSteps.validateNumberProject(URL_PROJECT);
        response=steps.createNewProjectWithValidToken(map,URL_PROJECT);
        steps.createProjectSuccessfully(200,response,(String)map.get("name"));
        id=flowSteps.getCreatedId(response);

    }

    @Test(priority = 1)
    public void getProject(){
        GetAllProjectSteps steps=new GetAllProjectSteps();
        response=steps.getProjectWithValidAccessToken(URL_PROJECT+"/"+id);
        steps.verifyStatus(200,response);
    }

    @Test(priority = 2)
    public void updateProject(){
        UpdateProjectSteps steps=new UpdateProjectSteps();
        Map<String,Object> map=new HashMap<>();
        map.put("name","C5 Project Update1");

        response=steps.updateProjectWithValidAccessToken(map,URL_PROJECT+"/"+id);
        steps.verifyStatus(204,response);
    }

    @Test(priority = 3)
    public void getAllProject(){
        GetAllProjectSteps steps=new GetAllProjectSteps();
        response=steps.getProjectWithValidAccessToken(URL_PROJECT);
        steps.verifyStatus(200,response);
        id=String.valueOf(steps.getId(response));
    }

    @Test(priority = 4)
    public void deleteProject(){
        DeleteProjectSteps steps=new DeleteProjectSteps();
        response=steps.deleteProject(URL_PROJECT+"/"+id);
        steps.verifyStatus(204,response);

        getAllProject();
    }
}
