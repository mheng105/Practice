package com.vmo.training.demo.microservices.steps.assignment2a;

import com.vmo.training.demo.configs.ConfigSetting;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static com.vmo.training.demo.handles.ResponseHandles.sendPostMethodWithoutToken;
import static com.vmo.training.demo.utils.JsonUtils.getJsonObject;
import static com.vmo.training.demo.utils.JsonUtils.jsonValue;

public class BaseSteps {
   ConfigSetting config=new ConfigSetting(System.getProperty("user.dir"));
   String email=config.getMail();
   String password=config.getPassword();

    @Step("Get accessToken successfully")
    public String getAccessTokenSuccessfully(){
        Map<String,String> map=new HashMap<>();
        map.put("email",email);
        map.put("password",password);

        Response response=sendPostMethodWithoutToken(map,"/API/v8.7/user/login");
        Assert.assertEquals(response.statusCode(),200);

//        Object o=response.as(Object.class);
//        String g=new Gson().toJson(o);
//        JsonObject jObject=new Gson().fromJson(g,JsonObject.class);
//        return String.valueOf(jObject.get("token"));
        String reString=response.asPrettyString();
        return jsonValue(reString,"token");
    }

    @Step("Get accessToken unsuccessfully")
    public String getAccessTokenFail(){
        Map<String,String> map=new HashMap<>();
        map.put("email",email);
        map.put("password",password);

        Response response= sendPostMethodWithoutToken(map,"/abc");
        Assert.assertEquals(response.statusCode(),404);

//        Object o=response.as(Object.class);
//        String g=new Gson().toJson(o);
//        JsonObject jObject=new Gson().fromJson(g,JsonObject.class);
//        Object object=jObject.get("token");
//        return String.valueOf(object);
        String reString=response.asPrettyString();
        return jsonValue(reString,"token");
    }

    @Step("Get id")
    public Object getId(Response response){
        return getJsonObject(response).get("id");
    }
}
