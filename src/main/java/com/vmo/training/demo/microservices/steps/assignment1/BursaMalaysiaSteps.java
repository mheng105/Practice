package com.vmo.training.demo.microservices.steps.assignment1;

import com.vmo.training.demo.microservices.pages.assignment1.BursaMalaysiaPage;
import io.qameta.allure.Step;

public class BursaMalaysiaSteps extends BursaMalaysiaPage {

    @Step("Go to ''{0}'' ")
    public void goTo(String url) {
        getUrl(url);
    }

    @Step("Get counters in BursaMalaysia Page")
    public void getCounters() {
        getCounter();
    }

    @Step("Show counter list in descending order of change")
    public void showList() {
        showCounters();
    }

}
