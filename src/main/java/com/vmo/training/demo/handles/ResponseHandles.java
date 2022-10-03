package com.vmo.training.demo.handles;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ResponseHandles {
    public static Response sendPostMethodWithoutToken(Object object, String path) {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(object)
                .post(path);
        return response;
    }

    public static Response sendGetMethodWithoutToken(String path) {
        Response response = given()
                .when()
                .get(path);
        return response;
    }

    public static Response sendDeleteMethodWithoutToken(String path) {
        Response response = given()
                .when()
                .delete(path);
        return response;
    }

    public static Response sendPostMethod(Object object, String accessToken, String path) {
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .body(object)
                .post(path);
        return response;
    }

    public static Response sendGetMethod(String accessToken, String path) {
        Response response = given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get(path);
        return response;
    }

    public static Response sendDeleteMethod(String accessToken, String path) {
        Response response = given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .delete(path);
        return response;
    }

    public static Response sendPutMethod(Object object, String accessToken, String path) {
        Response response = given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .put(path);
        return response;
    }

    public static Response sendPatchMethod(Object object, String accessToken, String path) {
        Response response = given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .patch(path);
        return response;
    }

}
