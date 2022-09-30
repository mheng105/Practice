package com.vmo.training.demo.microservices.pages.assignment1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.vmo.training.demo.utils.keywords.WebUI;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.http.Routable;

public class EcommercePage extends WebUI {
	List<Product> products = new ArrayList<Product>();

	public void search(String locator) {
		sendKey(locator, "Iphone 11");
		sendKey(locator, Keys.ENTER);
		delayInSeconds(3);
	}

	public void getProducts(String listLocator, String nameProductLocator, String priceLocator,String nameWebsiteLocator,String linkLocator, String attribute) {
		List<WebElement> list = findElements(listLocator);
		List<WebElement> nameProducts = findElements(nameProductLocator);
		List<WebElement> prices = findElements(priceLocator);
		String nameWebsite = getAttribute(nameWebsiteLocator, attribute);
		String link= getAttribute(linkLocator, attribute);

		for (int i = 0; i < list.size(); i++) {
			Product product = new Product();
			product.nameProduct = nameProducts.get(i).getText().trim();
			String price=prices.get(i).getText().trim();
			if(price.contains("$") || price.contains("VND")){
				if (!price.toUpperCase().contains("to".toUpperCase())) {
					product.price = convertMoney(price.replace("\n", "."));
				} else {
					product.price = convertMoney(price.replace("\n", ".").split("to")[0]);
				}
			}else{
				price="0";
				product.price = price;
			}

			product.nameWebsite = nameWebsite;
			product.link=link;
			products.add(product);
		}
	}

	public String convertMoney(String price){
		if(price.contains("VND")){
			String p="$"+convert(replace(price))/23000;
			return p;
		}else {
			return price;
		}
	}

	public String replace(String price) {
		return price.replace("$", "").replace(",", "").replace("VND", "").trim();
	}


	public void showProducts(String value) {
		Collections.sort(products, new Comparator<Product>() {
			@Override
			public int compare(Product product1, Product product2) {
				return Float.compare(convert(replace(product2.price)), convert(replace(product1.price)));
			}

		});
		System.out.println("\n\n'Iphone 11' List\n");
		for (Product product : products) {
			if (product.nameProduct.toUpperCase().contains(value.toUpperCase())) {
				System.out.println("Website: " + product.nameWebsite + "\tName: " + product.nameProduct + "\tPrice: "+ product.price+"\tLink: "+product.link);
			}
		}
	}
}
