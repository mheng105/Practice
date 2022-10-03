package com.vmo.training.demo.test.assignment1;

import com.vmo.training.demo.basetests.assignment1.BaseTest;
import com.vmo.training.demo.microservices.steps.assignment1.EcommerceSteps;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EcommerceTest extends BaseTest {
    EcommerceSteps obj;

    @Test(description = "Ecommerce Test")
    public void run() {
        obj = new EcommerceSteps();
        obj.goToPage();
        obj.searchProductEbay();
        obj.getProductEbay();
        obj.navigateToPage();
        obj.searchProductAmazon();
        obj.getProductAmazon();
        obj.show();
        Assert.assertTrue(true);
    }
}
