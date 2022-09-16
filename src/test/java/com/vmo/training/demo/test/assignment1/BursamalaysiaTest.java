package com.vmo.training.demo.test.assignment1;

import com.vmo.training.demo.basetests.assignment1.BaseTest;
import com.vmo.training.demo.microservices.steps.assignment1.BursamalaysiaSteps;
import com.vmo.training.demo.utils.keywords.WebUI;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class BursamalaysiaTest extends BaseTest {
	protected BursamalaysiaSteps obj;
	protected static String url;
	protected static String status;

	public BursamalaysiaTest() {
		super();
		url=configSetting.getUrl();
		status=configSetting.getStatus();
	}

	public BursamalaysiaTest(WebUI action) {
		super(action);
	}

	@Test(description = "Bursamalaysia Test")
	@Description("Bursamalaysia Test")
	public void test() {
		obj=new BursamalaysiaSteps();
		obj.goTo(url);
		obj.getCounters(status);
		obj.showList();
	}
}
