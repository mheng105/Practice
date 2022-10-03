package com.vmo.training.demo.test.assignment2a;

import com.vmo.training.demo.basetests.assignment2a.ProjectBaseTest;
import com.vmo.training.demo.microservices.steps.assignment2a.CreateTaskSteps;
import com.vmo.training.demo.models.assignment2a.ProjectInfo;
import com.vmo.training.demo.utils.TestListener;
import io.qameta.allure.Description;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.vmo.training.demo.microservices.constants.Constant.TodoIst.*;
import static com.vmo.training.demo.utils.JsonUtils.jsonValue;

public class CreateTaskTest extends ProjectBaseTest {
    CreateTaskSteps createTaskSteps = new CreateTaskSteps();

    @Test
    public void createTask() {
//        Map<String, Object> mapProject = new HashMap<>();
//        mapProject.put("name", nameProject);
        ProjectInfo info=new ProjectInfo();
        info.setName(nameProject);
        System.out.println("Info project");
        System.out.println(info);
        createTaskSteps.validateNumberProject(URL_PROJECT);
        id = createTaskSteps.createProject(info, URL_PROJECT);
        Map<String, Object> map = new HashMap<>();
        map.put("project_id", id);
        map.put("content", "new task");
        response = createTaskSteps.createTask(map, URL_TASK);
        createTaskSteps.createTaskSuccessfully(response, 200, (String) map.get("content"));
        id = jsonValue(response.asPrettyString(), "id");
//        content = jsonValue(response.asPrettyString(), "content");
    }

    @BeforeTest
    public void open() {
        createTaskSteps.goToLogin();
    }

    @Test
    @Description("Create project then creating task in that project")
    public void login() throws IOException {
        ProjectInfo info=new ProjectInfo();
        createTaskSteps.login().clickProject(nameProject).captureScreenshot("BeforeClick");
        createTaskSteps.createTask();
        createTaskSteps.clickCheckbox("new task").captureScreenshot("AfterClick").clickTaskSuccessfully();
        response = createTaskSteps.reopenTask(info, URL_TASK + "/" + id + "/reopen");
        createTaskSteps.captureScreenshot("ReopenSuccessfully");
        createTaskSteps.reopenSuccessfully(response, 204).reopenTaskUI();
    }

    @AfterTest
    public void close() {
        createTaskSteps.close();
    }

//    @Test
//    public void verifyTask(){
//        steps.verifyTaskSuccessfully(URL_TASK+"/"+id,content);
//    }

}
