package com.vmo.training.demo.microservices.steps.assignment2a;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vmo.training.demo.configs.ConfigSetting;
import com.vmo.training.demo.utils.keywords.WebUI;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.vmo.training.demo.handles.ResponseHandles.*;
import static com.vmo.training.demo.microservices.constants.Constant.*;
import static com.vmo.training.demo.utils.JsonUtils.jsonValue;

public class CreateTaskSteps extends BaseSteps {
    ConfigSetting config=new ConfigSetting(System.getProperty("user.dir"));
    String email=config.getMail();
    String password=config.getPassword();


    WebUI action=new WebUI();
    final String GROUP_LI_LIST="//ul[@id='projects_list']/li/div/div/a";
    final String GROUP_SPAN_NAME_PROJECT="//ul[@id='projects_list']/li/div/div/a/span[2]";
    final String GROUP_DIV_NAME_TASK="//div[@class='list_holder']/ul/li/div/div[@class='task_list_item__content']//div[@class='markdown_content task_content']";
    final String GROUP_BUTTON_TASK="//div[@class='list_holder']/ul/li/div/button";
    final String DIV_NAME_TASK="//div['markdown_content task_content']";


    @Step("Go to login page")
    public CreateTaskSteps goToLogin(){
        action.open("Chrome","https://todoist.com/auth/login");
        action.maximizeWindow();
        return this;
    }

    @Step("Login page")
    public CreateTaskSteps login(){
        action.sendKey("//input[@id='element-0']",email);
        action.sendKey("//input[@id='element-3']",password);
        action.click("//button[@type='submit']");
        action.delayInSeconds(5);
        return this;
    }

    @Step("Go to created project")
    public CreateTaskSteps clickProject(String exceptedName){
        List<WebElement> elements=action.findElements(GROUP_LI_LIST);
        action.delayInSeconds(3);
        List<WebElement> nameList=action.findElements(GROUP_SPAN_NAME_PROJECT);
        action.delayInSeconds(3);
        for(int i=0;i<elements.size();i++){
            String name= elements.get(i).getText();
            if(name.toUpperCase().trim().equals(exceptedName.trim().toUpperCase())) {
                System.out.println("Clicking project");
                nameList.get(i).click();
                action.delayInSeconds(3);
                System.out.println("Clicked project successfully");
                break;
            }
        }
        return this;
    }

//    @Step("Compare task")
//    public boolean shouldBeTaskSuccessfully(String exceptedName){
//        List<WebElement> tasks=action.findElements(GROUP_DIV_NAME_TASK);
//        for(int i=0;i<tasks.size();i++){
//            String name= tasks.get(i).getText().trim();
//            if(name.toUpperCase().trim().equals(exceptedName.toUpperCase().trim())){
//                return true;
//            }
//        }
//        return false;
//    }

    @Step("Compare task")
    public boolean shouldBeTaskSuccessfully(){
        if(action.verifyElementDisplay(DIV_NAME_TASK)){
            return true;
        }
        return false;
    }

    @Step("Create a task successfully")
    public CreateTaskSteps createTask(){
        Assert.assertTrue(shouldBeTaskSuccessfully());
        return this;
    }

//    @Step("Click checkbox")
//    public CreateTaskSteps clickCheckbox(){
//        if(shouldBeTaskSuccessfully()){
//            action.click(DIV_NAME_TASK);
//            action.delayInSeconds(3);
//        }
//        return this;
//    }

    @Step("Click checkbox")
    public CreateTaskSteps clickCheckbox(String exceptedName){
        List<WebElement> tasks=action.findElements(GROUP_DIV_NAME_TASK);
        action.delayInSeconds(3);
        List<WebElement> cbList=action.findElements(GROUP_BUTTON_TASK);
        action.delayInSeconds(3);
        if(shouldBeTaskSuccessfully()){
            for(int i=0;i<tasks.size();i++){
                String nameTask=action.getText(tasks.get(i));
                if(nameTask.toUpperCase().trim().equals(exceptedName.toUpperCase().trim())){
                    System.out.println("Clicking the task");
                    cbList.get(i).click();
                    System.out.println("Clicked the task successfully");
                    action.delayInSeconds(5);
                }
            }
        }
        return this;
    }

//    @Step("Click checkbox successfully")
//    public boolean shouldBeNotDisplayTask(String nameTask){
//        List<WebElement> tasks=action.findElements(GROUP_DIV_NAME_TASK);
//            for(WebElement we:tasks){
//                if(action.verifyElementDisplay(GROUP_DIV_NAME_TASK)){
//                    if(action.getText(we).equals(nameTask)){
//                        return false;
//                    }
//                }
//            }
//        return false;
//    }

    @Step("Clicked task successfully")
    public CreateTaskSteps clickTaskSuccessfully(){
        Assert.assertTrue(action.findWebElement(DIV_NAME_TASK).isDisplayed());
        return this;
    }

    @Step("Close browser")
    public CreateTaskSteps close(){
        action.delayInSeconds(3);
        action.close("Chrome");
        return this;
    }
    @Step("Create a task")
    public Response createTask(Map map, String path){
        return sendPostMethod(map,accessToken,path);
    }

    @Step("Reopen task")
    public Response reopenTask(Map map,String path){
        return sendPostMethod(map,accessToken,path);
    }

    @Step("Reopened task successfully")
    public CreateTaskSteps reopenSuccessfully(Response response,int exceptedCode){
        Assert.assertEquals(response.getStatusCode(),exceptedCode);
        return this;
    }

    @Step("Verify 'task is reopen' on UI")
    public CreateTaskSteps reopenTaskUI(){
        Assert.assertTrue(action.findWebElement(DIV_NAME_TASK).isDisplayed());
        return this;
    }

    @Step("Screenshot is captured")
    public CreateTaskSteps captureScreenshot(String name) throws IOException {
        action.delayInSeconds(5);
        action.takeScreenShot(name);
        return this;
    }

    @Step("Get id project after creating a new project")
    public String getCreatedId(Response response){
        String reString=response.asPrettyString();
        return jsonValue(reString,"id");
    }

    @Step("Create a project then getting id")
    public String createProject(Map map,String path){
        Response response=sendPostMethod(map,accessToken,path);
        return getCreatedId(response);
    }

    @Step("Get id from projects")
    public String getIdProject(){
        Response response = sendGetMethod(accessToken,URL_PROJECT);
        List re = response.as(List.class);
        String object = new Gson().toJson(re.get(1));
        JsonObject jObject = new Gson().fromJson(object, JsonObject.class);
        return String.valueOf(jObject.get("id"));
    }

    @Step("Create a new task successfully")
    public CreateTaskSteps createTaskSuccessfully(Response response,int exceptedCode,String content){
        Assert.assertEquals(response.getStatusCode(),exceptedCode);
        verifyContent(response,content);
        showPretty(response);
        return this;
    }

    @Step("Verify content")
    public CreateTaskSteps verifyContent(Response response, String content){
        String resString=response.asPrettyString();
        Assert.assertEquals(jsonValue(resString,"content"),content);
        return this;
    }

    @Step("Show pretty")
    public void showPretty(Response response){
        response.prettyPrint();
    }

    @Step("Validate the number of projects")
    public CreateTaskSteps validateNumberProject(String path){
        Response response=sendGetMethod(accessToken,path);
        List list=response.as(List.class);
        if(list.size()>7){
            sendDeleteMethod(accessToken,path+"/"+getIdProject());
        }
        return this;
    }

    @Step("Verify task")
    public CreateTaskSteps verifyTaskSuccessfully(String path,String content){
        Response response=sendGetMethod(accessToken,path);
        String reString=response.asPrettyString();
        Assert.assertEquals(jsonValue(reString,"content"),content);
        return this;
    }
}
