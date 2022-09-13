package com.vmo.training.demo.basetests.assignment1;

import com.vmo.training.demo.configs.ConfigSetting;
import com.vmo.training.demo.utils.keywords.WebUI;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest{
    protected WebUI action;
    protected static String browser;
    protected static int defaultTimeout;

    protected static ConfigSetting configSetting;

    public BaseTest() {
        action = new WebUI();
        configSetting=new ConfigSetting(System.getProperty("user.dir"));
        browser=configSetting.getBrowser();
        defaultTimeout= Integer.parseInt(configSetting.getDefaultTimeout());

    }
    public BaseTest(WebUI action) {
        this.action = action;
    }
    @BeforeMethod
    @Parameters({"browserTest"})
    public void open(String browserTest){
        browserTest=browser;
        action.open(browserTest);
        action.maximizeWindow();
    }
    @AfterMethod
    @Parameters({"browserTest"})
    public void close(String browserTest){
        browserTest=browser;
        action.close(browserTest);
    }
}
