package com.vmo.training.demo.utils.driver;


import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EdgeDriverManager extends DriverManager {
	@Override
	public void createDriver() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
	}
}
