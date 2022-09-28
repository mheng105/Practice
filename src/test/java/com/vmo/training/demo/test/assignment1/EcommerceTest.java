package com.vmo.training.demo.test.assignment1;

import com.vmo.training.demo.basetests.assignment1.BaseTest;
import com.vmo.training.demo.microservices.steps.assignment1.EcommerceSteps;
import com.vmo.training.demo.utils.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.vmo.training.demo.microservices.pages.assignment1.IProduct;

@Listeners(TestListener.class)
public class EcommerceTest extends BaseTest implements IProduct{
	EcommerceSteps objEcommerce;

	@Test(description = "Ecommerce Test")
	public void run() {
		objEcommerce = new EcommerceSteps();
		objEcommerce.goToPage(URL[0]);
		objEcommerce.searchProduct(TXT_SEARCH_EBAY);
		objEcommerce.getProduct(list, nameProduct, price, nameWebsite, link, attribute);
		objEcommerce.navigateToPage(URL[1]);
		objEcommerce.searchProduct(TXT_SEARCH_AMAZON);
		objEcommerce.getProduct(list2, nameProduct2, price2, nameWebsite2, link2, attribute);
		objEcommerce.show("iphone 11");
	}
}
