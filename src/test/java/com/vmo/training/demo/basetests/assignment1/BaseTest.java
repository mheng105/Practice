package com.vmo.training.demo.basetests.assignment1;

import com.vmo.training.demo.configs.ConfigSetting;
import com.vmo.training.demo.utils.TestListener;
import com.vmo.training.demo.utils.keywords.WebUI;
import org.testng.annotations.*;

@Listeners(TestListener.class)
public class BaseTest {
    private WebUI action = new WebUI();
    ;
    private String browser;
    private int defaultTimeout;
    protected ConfigSetting configSetting;

    public BaseTest() {
        configSetting = new ConfigSetting(System.getProperty("user.dir"));
        browser = configSetting.getBrowser();
        defaultTimeout = Integer.parseInt(configSetting.getDefaultTimeout());
    }

    @BeforeMethod
    @Parameters({"browserTest"})
    public void open(@Optional String browserTest) {
        browserTest = browser;
        action.open(browserTest);
        action.maximizeWindow();
    }

    @AfterMethod
    @Parameters({"browserTest"})
    public void close(@Optional String browserTest) {
        browserTest = browser;
        action.close(browserTest);
    }
}
