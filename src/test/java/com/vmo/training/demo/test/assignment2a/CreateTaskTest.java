package com.vmo.training.demo.test.assignment2a;

import com.vmo.training.demo.basetests.assignment2a.ProjectBaseTest;
import com.vmo.training.demo.microservices.steps.assignment2a.CreateTaskSteps;
import com.vmo.training.demo.utils.TestListener;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.vmo.training.demo.microservices.constants.Constant.*;
import static com.vmo.training.demo.utils.JsonUtils.jsonValue;

@Listeners(TestListener.class)
public class CreateTaskTest extends ProjectBaseTest {
    CreateTaskSteps createTaskSteps=new CreateTaskSteps();
    static String content;

    @Test
    public void createTask(){
        Map<String,Object> mapProject=new HashMap<>();
        mapProject.put("name","Project");
        createTaskSteps.validateNumberProject(URL_PROJECT);
        id=createTaskSteps.createProject(mapProject,URL_PROJECT);
        Map<String,Object> map=new HashMap<>();
        map.put("project_id",id);
        map.put("content","new task");
        response =createTaskSteps.createTask(map,URL_TASK);
        createTaskSteps.createTaskSuccessfully(response,200,(String) map.get("content"));
        id=jsonValue(response.asPrettyString(),"id");
        content=jsonValue(response.asPrettyString(),"content");
    }

    @BeforeTest
    public void open(){
        createTaskSteps.goToLogin();
    }

    @Test
    public void login() throws IOException {
        Map<String,Object> map=new HashMap<>();
        createTaskSteps.login().clickProject("Project").captureScreenshot("BeforeClick");
        createTaskSteps.createTask();
        createTaskSteps.clickCheckbox("new task").captureScreenshot("AfterClick").clickTaskSuccessfully();
        response= createTaskSteps.reopenTask(map,URL_TASK+"/"+id+"/reopen");
        createTaskSteps.captureScreenshot("ReopenSuccessfully");
        createTaskSteps.reopenSuccessfully(response,204).reopenTaskUI();
    }

    @AfterTest
    public void close(){
        createTaskSteps.close();
    }

//    @Test
//    public void verifyTask(){
//        steps.verifyTaskSuccessfully(URL_TASK+"/"+id,content);
//    }

}
