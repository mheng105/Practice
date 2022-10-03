package com.vmo.training.demo.microservices.steps.assignment1;

import com.vmo.training.demo.microservices.pages.assignment1.EcommercePage;
import io.qameta.allure.Step;

public class EcommerceSteps extends EcommercePage {
    @Step("Go to ''Ebay''")
    public void goToPage() {
        getUrl(URL[0]);
    }

    @Step("Search product name in Ebay page")
    public void searchProductEbay() {
        search(TXT_SEARCH_EBAY);
    }

    @Step("Get products in EBay page")
    public void getProductEbay() {
        getProducts(DIV_LIST_EBAY, DIV_PRODUCT_NAME_EBAY, DIV_PRICE_EBAY, DIV_WEBSITE_NAME_EBAY, DIV_LINK_PRODUCT_EBAY, ATTRIBUTE);
    }

    @Step("Navigate to ''Amazon''")
    public void navigateToPage() {
        navigateToUrl(URL[1]);
    }

    @Step("Search product name")
    public void searchProductAmazon() {
        search(TXT_SEARCH_AMAZON);
    }

    @Step("Get products in page")
    public void getProductAmazon() {
        getProducts(DIV_LIST_AMAZON, TXT_PRODUCT_NAME_AMAZON, DIV_PRICE_AMAZON, GROUP_WEBSITE_NAME_AMAZON, DIV_LINK_PRODUCT_AMAZON, ATTRIBUTE);
    }

    @Step("Show ''Iphone 11'' list in descending order of price")
    public void show() {
        showProducts("Iphone 11");
    }
}
