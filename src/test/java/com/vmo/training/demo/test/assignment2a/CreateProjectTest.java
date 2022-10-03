package com.vmo.training.demo.test.assignment2a;

import com.vmo.training.demo.basetests.assignment2a.ProjectBaseTest;
import com.vmo.training.demo.microservices.steps.assignment2a.CreateProjectSteps;
import com.vmo.training.demo.utils.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.vmo.training.demo.microservices.constants.Constant.TodoIst.*;

@Listeners(TestListener.class)
public class CreateProjectTest extends ProjectBaseTest {

    CreateProjectSteps createProjectSteps = new CreateProjectSteps();

    @Test(description = "Create a new project successfully")
    public void C_01() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", nameProject);
        createProjectSteps.deleteProjectWhenMaximumProjects(map);

        response = createProjectSteps.createNewProjectWithValidToken(map, URL_PROJECT);
        createProjectSteps.createProjectSuccessfully(200, response, (String) map.get("name"));
    }

    @Test(description = "Create a new project without 'name' field")
    public void C_02() {
        Map<String, Object> map = new HashMap<>();
        map.put("color", 40);
        map.put("favorite", true);

        response = createProjectSteps.createNewProjectWithValidToken(map, URL_PROJECT);
        createProjectSteps.verifyStatus(400, response);
    }

    @Test(description = "Create a new project with invalid accessToken")
    public void C_03() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "C5 project");

        response = createProjectSteps.createNewProjectWithInvalidToken(map, URL_PROJECT);
        createProjectSteps.verifyStatus(401, response);
    }

    @Test(description = "Create a new project without accessToken")
    public void C_04() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "C5 project");

        response = createProjectSteps.createNewProjectWithoutAccessToken(map, URL_PROJECT);
        createProjectSteps.verifyStatus(401, response);
    }

    @Test(description = "Create a new project when getting accessToken with invalid url")
    public void C_05() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "C5 Project");
        map.put("color", 40);
        map.put("favorite", true);

        response = createProjectSteps.createNewProjectWithInvalidUrl(map, URL_PROJECT);
        createProjectSteps.verifyStatus(404, response);
    }

    @Test(description = "Create a new project with invalid url")
    public void C_06() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "C5 Project");
        map.put("color", 40);
        map.put("favorite", true);

        response = createProjectSteps.createNewProjectWithValidToken(map, URL_PROJECT + "/a/abc");
        createProjectSteps.verifyStatus(404, response);
    }

    @Test(description = "Create a new project with invalid 'name' field")
    public void C_07() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", 123456);
        map.put("color", 40);
        map.put("favorite", true);

        response = createProjectSteps.createNewProjectWithValidToken(map, URL_PROJECT);
        createProjectSteps.verifyStatus(400, response);
    }

    @Test(description = "Create a new project with empty 'name' field")
    public void C_08() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "");
        map.put("color", 40);
        map.put("favorite", true);

        response = createProjectSteps.createNewProjectWithValidToken(map, URL_PROJECT);
        createProjectSteps.verifyStatus(400, response);
    }

    @Test(description = "Create a new project with invalid method")
    public void C_09() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "C5 Project");
        map.put("color", 40);
        map.put("favorite", true);

        response = createProjectSteps.createNewProjectWithInvalidMethod(map, accessToken, URL_PROJECT);
        createProjectSteps.verifyStatus(405, response);
    }

    @Test(description = "Create a new project when existed maximum projects")
    public void C_10() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "C5");
        createProjectSteps.validateNumberProject(map, URL_PROJECT);
    }
}
