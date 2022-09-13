package com.vmo.training.demo.microservices.steps.assignment1;

import com.vmo.training.demo.microservices.pages.assignment1.EcommercePage;
import io.qameta.allure.Step;

public class EcommerceSteps extends EcommercePage {
    @Step("Go to ''{0}''")
    public void goToPage(String url){
        getUrl(url);
    }

    @Step("Search product name")
    public void searchProduct(String locator){
        search(locator);
    }

    @Step("Get products in page")
    public void getProduct(String list,String name, String price, String nameWebsite, String link, String attribute){
        getProducts(list,name,price,nameWebsite,link,attribute);
    }

    @Step("Navigate to ''{0}''")
    public void navigateToPage(String url){
        navigateToUrl(url);
    }

    @Step("Show ''{0}'' list in descending order of price")
    public void show(String value){
        showProducts(value);
    }

}
