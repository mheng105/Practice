package com.vmo.training.demo.utils.driver;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
	protected WebDriver driver;
	
	protected abstract void createDriver();
	
	public WebDriver getDriver() {
		if(driver==null) {
			createDriver();
		}
		return driver;
	}
}


