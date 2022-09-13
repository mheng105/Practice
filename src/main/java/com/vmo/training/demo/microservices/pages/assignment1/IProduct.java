package com.vmo.training.demo.microservices.pages.assignment1;

public interface IProduct {
	String[] URL={"https://www.ebay.com/","https://www.amazon.com/"};
	String TXT_SEARCH_EBAY = "//input[@id='gh-ac']";
	String list = "//div[@id='srp-river-results']/ul/li/div/div/a";
    String nameProduct = "//div[@id='srp-river-results']/ul/li/div/div/a/div/span";
	String price = "//div[@id='srp-river-results']/ul/li/div/div/div/div/span[@class='s-item__price']";
	String nameWebsite = "//a[@id='gh-la']";
	String link="//div[@id='srp-river-results']/ul/li/div/div/a";
	String attribute = "href";
	String list2 = "//div[@class='s-main-slot s-result-list s-search-results sg-row']/div//h2/a/span[@class='a-size-medium a-color-base a-text-normal']";
	String TXT_SEARCH_AMAZON="//input[@id='twotabsearchtextbox' or @id='nav-bb-search']";
	String nameProduct2 = "//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']/a/span[@class='a-size-medium a-color-base a-text-normal']";
	String price2 = "//div[@class='a-section a-spacing-small a-spacing-top-small']/div[@class='sg-row']/div[1]/div/div[1]/div[1]//a/span";
	String nameWebsite2 = "//a[@id='nav-logo-sprites']";
	String link2="//div[@class='sg-row']/div[2]/div/div[1]/div/h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']/a";
}
