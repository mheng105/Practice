package com.vmo.training.demo.utils.driver;

import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverManager extends DriverManager {
	@Override
	protected void createDriver() {
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions firefoxOptions=new FirefoxOptions();
		firefoxOptions.setHeadless(true);
		driver=new FirefoxDriver(firefoxOptions);
	}

}
