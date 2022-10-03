package com.vmo.training.demo.microservices.steps.assignment1;

import com.vmo.training.demo.microservices.pages.assignment1.BursamalaysiaPage;
import io.qameta.allure.Step;

public class BursamalaysiaSteps extends BursamalaysiaPage {

    @Step("Go to ''{0}''")
    public void goTo(String url){
        getUrl(url);
    }

    @Step("Get counters in ''{0}''")
    public void getCounters(String status){
        getCounter(status);
    }

    @Step("Show counter list in descending order of change")
    public void showList(){
        showCounters();
    }

}
