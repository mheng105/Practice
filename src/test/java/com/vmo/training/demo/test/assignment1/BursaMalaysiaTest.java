package com.vmo.training.demo.test.assignment1;

import com.vmo.training.demo.basetests.assignment1.BaseTest;
import com.vmo.training.demo.microservices.steps.assignment1.BursaMalaysiaSteps;
import com.vmo.training.demo.utils.keywords.WebUI;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BursaMalaysiaTest extends BaseTest {
    private BursaMalaysiaSteps obj;
    private String url;

    public BursaMalaysiaTest() {
        super();
        url = configSetting.getUrl();
    }

    @Test(description = "Bursa Malaysia Test")
    @Description("Bursa Malaysia Test")
    public void test() {
        obj = new BursaMalaysiaSteps();
        obj.goTo(url);
        Assert.assertTrue(false);
        obj.getCounters();
        obj.showList();
    }

    @Test
    public void sida() {
        WebUI action = new WebUI();
        obj = new BursaMalaysiaSteps();

        obj.goTo("https://www.google.com/");
        Assert.assertEquals(action.getTitle(), "ggg");
    }
}
