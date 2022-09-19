package com.vmo.training.demo.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class JsonUtils {
    public static String getJsonValue(String filePath, String node) {
        File input = new File(filePath);

        JsonElement fileElement = null;
        try {
            fileElement = JsonParser.parseReader(new FileReader(input));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert fileElement != null;
        JsonObject fileObject = fileElement.getAsJsonObject();
        return fileObject.get(node).getAsString();
    }

    public static Object getJsonValue(JsonObject object, String node) {
        return object.get(node).getAsString();
    }

    public static String jsonValue(String fileName, String node) {
        JsonObject jsonObject = new Gson().fromJson(fileName, JsonObject.class);
        return jsonObject.get(node).getAsString();
    }

    public static JsonObject getJSV(String filePath, String node) {
        File input = new File(filePath);

        JsonElement fileElement = null;
        try {
            fileElement = JsonParser.parseReader(new FileReader(input));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert fileElement != null;
        JsonObject fileObject = fileElement.getAsJsonObject();
        return fileObject.get(node).getAsJsonObject();
    }

    public static Object getObject(String filePath, String node) {
        File input = new File(filePath);

        JsonElement fileElement = null;
        try {
            fileElement = JsonParser.parseReader(new FileReader(input));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert fileElement != null;
        JsonObject fileObject = fileElement.getAsJsonObject();
        return fileObject.get(node).getAsJsonNull();
    }

    public static String jsonValue(JsonObject json, String node) {
        return json.get(node).getAsString();
    }

    public static String updateValue(String jsonName, String node, String newValue) {
        Gson gson = new Gson();
        Map<String, String> map = gson.fromJson(jsonName, Map.class);
//        for (Map.Entry<?, ?> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + "=" + entry.getValue());
//        }
        map.put(node, newValue);
//        System.out.println("---------");
//        for (Map.Entry<?, ?> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + "=" + entry.getValue());
//        }
        return gson.toJson(map);

    }

    public static String updateValue(JsonObject body, String node, String newValue) {
        Gson gson = new Gson();
        Map<String, String> map = gson.fromJson(body, Map.class);
        map.put(node, newValue);
        return gson.toJson(map);
    }

    public static String convertJsonToString(Object body) {
        return new Gson().toJson(body);
    }
}
