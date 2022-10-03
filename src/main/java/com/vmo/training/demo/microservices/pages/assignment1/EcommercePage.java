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
    protected static String TXT_SEARCH_EBAY = "//input[@id='gh-ac']";
    protected static String DIV_LIST_EBAY = "//div[@id='srp-river-results']/ul/li/div/div/a";
    protected static String DIV_PRODUCT_NAME_EBAY = "//div[@id='srp-river-results']/ul/li/div/div/a/div/span";
    protected static String DIV_PRICE_EBAY = "//div[@id='srp-river-results']/ul/li/div/div/div/div/span[@class='s-item__price']";
    protected static String DIV_WEBSITE_NAME_EBAY = "//a[@id='gh-la']";
    protected static String DIV_LINK_PRODUCT_EBAY = "//div[@id='srp-river-results']/ul/li/div/div/a";
    protected static String ATTRIBUTE = "href";
    protected static String DIV_LIST_AMAZON = "//div[@class='s-main-slot s-result-list s-search-results sg-row']/div//h2/a/span[@class='a-size-medium a-color-base a-text-normal']";
    protected static String TXT_SEARCH_AMAZON = "//input[@id='twotabsearchtextbox' or @id='nav-bb-search']";
    protected static String TXT_PRODUCT_NAME_AMAZON = "//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']/a/span[@class='a-size-medium a-color-base a-text-normal']";
    protected static String DIV_PRICE_AMAZON = "//div[@class='a-section a-spacing-small a-spacing-top-small']/div[@class='sg-row']/div[1]/div/div[1]/div[1]//a/span";
    protected static String GROUP_WEBSITE_NAME_AMAZON = "//a[@id='nav-logo-sprites']";
    protected static String DIV_LINK_PRODUCT_AMAZON = "//div[@class='sg-row']/div[2]/div/div[1]/div/h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']/a";
    protected String[] URL = {"https://www.ebay.com/", "https://www.amazon.com/"};

    public void search(String locator) {
        sendKey(locator, "Iphone 11");
        sendKey(locator, Keys.ENTER);
        delayInSeconds(3);
    }

    public void getProducts(String listLocator, String nameProductLocator, String priceLocator, String nameWebsiteLocator, String linkLocator, String attribute) {
        List<WebElement> list = findElements(listLocator);
        List<WebElement> nameProducts = findElements(nameProductLocator);
        List<WebElement> prices = findElements(priceLocator);
        String nameWebsite = getAttribute(nameWebsiteLocator, attribute);
        String link = getAttribute(linkLocator, attribute);

        for (int i = 0; i < list.size(); i++) {
            Product product = new Product();
            product.nameProduct = nameProducts.get(i).getText().trim();
            String price = prices.get(i).getText().trim();
            if (price.contains("$") || price.contains("VND")) {
                if (!price.toUpperCase().contains("to".toUpperCase())) {
                    product.price = convertMoney(price.replace("\n", "."));
                } else {
                    product.price = convertMoney(price.replace("\n", ".").split("to")[0]);
                }
            } else {
                price = "0";
                product.price = price;
            }

            product.nameWebsite = nameWebsite;
            product.link = link;
            products.add(product);
        }
    }

    public String convertMoney(String price) {
        if (price.contains("VND")) {
            String p = "$" + convert(replace(price)) / 23000;
            return p;
        } else {
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
                System.out.println("Website: " + product.nameWebsite + "\tName: " + product.nameProduct + "\tPrice: " + product.price + "\tLink: " + product.link);
            }
        }
    }
}
