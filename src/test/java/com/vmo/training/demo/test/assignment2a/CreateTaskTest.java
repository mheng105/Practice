package com.vmo.training.demo.test.assignment2a;

import com.vmo.training.demo.basetests.assignment2a.ProjectBaseTest;
import com.vmo.training.demo.microservices.steps.assignment2a.CreateTaskSteps;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.vmo.training.demo.microservices.constants.Constant.*;
import static com.vmo.training.demo.utils.JsonUtils.jsonValue;

public class CreateTaskTest extends ProjectBaseTest {
    CreateTaskSteps steps=new CreateTaskSteps();
    static String content;

    @Test
    public void createTask(){
        Map<String,Object> mapProject=new HashMap<>();
        mapProject.put("name","Project");
        steps.validateNumberProject(URL_PROJECT);
        id=steps.createProject(mapProject,URL_PROJECT);
        Map<String,Object> map=new HashMap<>();
        map.put("project_id",id);
        map.put("content","new task");
        response =steps.createTask(map,URL_TASK);
        steps.createTaskSuccessfully(response,200,(String) map.get("content"));
        id=jsonValue(response.asPrettyString(),"id");
        content=jsonValue(response.asPrettyString(),"content");
    }

    @BeforeTest
    public void open(){
        steps.goToLogin();
    }

    @Test
    public void login(){
        Map<String,Object> map=new HashMap<>();
        steps.login().clickProject("Project")
        .createTask()
        .clickCheckbox("new task").clickTaskSuccessfully();
        response= steps.reopenTask(map,URL_TASK+"/"+id+"/reopen");
        steps.reopenSuccessfully(response,204).reopenTaskUI();
    }

    @AfterTest
    public void close(){
        steps.close();
    }

//    @Test
//    public void verifyTask(){
//        steps.verifyTaskSuccessfully(URL_TASK+"/"+id,content);
//    }

}
