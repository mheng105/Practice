package com.vmo.training.demo.handles;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ResponseHandles {
    public Response sendPostMethodWithoutToken(Map map,String path){
        Response response=given()
                .contentType(ContentType.JSON)
                .when()
                .body(map)
                .post(path);
        return response;
    }

    public Response sendGetMethodWithoutToken(String path){
        Response response=given()
                .when()
                .get(path);
        return response;
    }

    public Response sendDeleteMethodWithoutToken(String path){
        Response response=given()
                .when()
                .delete(path);
        return response;
    }
    public Response sendPostMethod(Map map, String accessToken,String path){
        Response response=given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken.replaceAll("\"",""))
                .when()
                .body(map)
                .post(path);
        return response;
    }

    public Response sendGetMethod(String accessToken, String path){
        Response response=given()
                .header("Authorization","Bearer "+accessToken.replaceAll("\"",""))
                .when()
                .get(path);
        return response;
    }

    public Response sendDeleteMethod(String accessToken,String path){
        Response response=given()
                .header("Authorization","Bearer "+accessToken.replaceAll("\"",""))
                .when()
                .delete(path);
        return response;
    }

}
