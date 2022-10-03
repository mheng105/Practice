package com.vmo.training.demo.test.assignment2a;

import com.vmo.training.demo.basetests.assignment2a.ProjectBaseTest;
import com.vmo.training.demo.microservices.steps.assignment2a.*;
import com.vmo.training.demo.utils.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.vmo.training.demo.microservices.constants.Constant.TodoIst.*;


@Listeners(TestListener.class)
public class FlowTest extends ProjectBaseTest {
    @Test(priority = 1)
    public void createProject() {
        FlowSteps flowSteps = new FlowSteps();
        CreateProjectSteps createProjectSteps = new CreateProjectSteps();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "C5 Project Test1");

        flowSteps.validateNumberProject(URL_PROJECT);
        response = createProjectSteps.createNewProjectWithValidToken(map, URL_PROJECT);
        createProjectSteps.createProjectSuccessfully(200, response, (String) map.get("name"));
        id = flowSteps.getCreatedId(response);

    }

    @Test(priority = 2)
    public void getProject() {
        GetAllProjectSteps getAllProjectSteps = new GetAllProjectSteps();
        response = getAllProjectSteps.getProjectWithValidAccessToken(URL_PROJECT + "/" + id);
        getAllProjectSteps.verifyStatus(200, response);
    }

    @Test(priority = 3)
    public void updateProject() {
        UpdateProjectSteps updateProjectSteps = new UpdateProjectSteps();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "C5 Project Update1");

        response = updateProjectSteps.updateProjectWithValidAccessToken(map, URL_PROJECT + "/" + id);
        updateProjectSteps.verifyStatus(204, response);
    }

    @Test(priority = 4)
    public void getAllProject() {
        GetAllProjectSteps getAllProjectSteps = new GetAllProjectSteps();
        response = getAllProjectSteps.getProjectWithValidAccessToken(URL_PROJECT);
        getAllProjectSteps.verifyStatus(200, response);
        id = String.valueOf(getAllProjectSteps.getId(response));
    }

    @Test(priority = 5)
    public void deleteProject() {
        DeleteProjectSteps deleteProjectSteps = new DeleteProjectSteps();
        response = deleteProjectSteps.deleteProject(URL_PROJECT + "/" + id);
        deleteProjectSteps.verifyStatus(204, response);

        getAllProject();
    }
}
