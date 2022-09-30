package com.vmo.training.demo.utils.driver;

import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager extends DriverManager {

	@Override
	public void createDriver() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions=new ChromeOptions();
		chromeOptions.addArguments("lang=en-US");
//		chromeOptions.setHeadless(true);
		driver = new ChromeDriver(chromeOptions);
	}
}
