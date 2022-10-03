package com.vmo.training.demo.microservices.constants;

import org.openqa.selenium.By;

import java.time.LocalDateTime;

public class Constant {
    public static class TodoIst{
        public static final String invalid_accessToken="a1223a344b";
        public static final String invalid_id="102002020a";
        public static final String URL_PROJECT="/rest/v1/projects";
        public static final String URL_TASK="/rest/v1/tasks";
        public static final String accessToken="d0482f58caef913260f2a172dd8619d17798836d";
        public static String nameProject="Project"+LocalDateTime.now();

        public static final String GROUP_LI_LIST = "//ul[@id='projects_list']/li/div/div/a";
        public static final String GROUP_SPAN_NAME_PROJECT = "//ul[@id='projects_list']/li/div/div/a/span[2]";
        public static final String GROUP_DIV_NAME_TASK = "//div[@class='list_holder']/ul/li/div/div[@class='task_list_item__content']//div[@class='markdown_content task_content']";
        public static final String GROUP_BUTTON_TASK = "//div[@class='list_holder']/ul/li/div/button";
        public static final String DIV_NAME_TASK = "//div['markdown_content task_content']";

        public static By img_Profile = By.xpath("//div[@class='user_avatar big settings_avatar']");
    }

}
