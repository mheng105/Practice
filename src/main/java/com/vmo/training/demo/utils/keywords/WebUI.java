package com.vmo.training.demo.utils.keywords;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.http.Routable;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;

import java.util.function.Function;
import com.vmo.training.demo.utils.driver.DriverManager;
import com.vmo.training.demo.utils.driver.DriverManagerFactory;
import com.vmo.training.demo.utils.driver.DriverType;
import com.vmo.training.demo.utils.log.LogHelper;

public class WebUI {
    private static final Logger logger = LogHelper.getLogger();
    private static DriverManager driverManager;

    public WebUI() {
    }

    public void clear(String locator) {
        WebElement we = findWebElement(locator);
        try {
            logger.info(MessageFormat.format("Clearing the content of the element located by {0}", locator));
            we.clear();
            logger.info(
                    MessageFormat.format("Cleared the content of the element located by {0} successfully", locator));
        } catch (Exception e) {
            logger.error(
                    MessageFormat.format("Cannot clear the content for the web element located by {0}. Root cause: {1}",
                            locator, e.getMessage()));
        }
    }

    // web element command
    public void click(String locator) {
        try {

            WebElement we = findWebElement(locator);
            logger.info(MessageFormat.format("Clicking web element located by ''{0}'' ", locator));
            if (we.isEnabled()) {
                we.click();
                logger.info(MessageFormat.format("Clicked web element located by ''{0}'' successfully", locator));
            } else {
                logger.error(MessageFormat.format(
                        "Cannot click web element located by ''{0}''. Root cause: Web element is not enable", locator));
            }

        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot click web element located by ''{0}''. Root cause: {1}", locator,
                    e.getMessage()));
        }

    }

    public void click(WebElement we) {
        try {
            logger.info(MessageFormat.format("Clicking web element located by ''{0}'' ", we));
            if (we.isEnabled()) {
                we.click();
                logger.info(MessageFormat.format("Clicked web element located by ''{0}'' successfully", we));
            } else {
                logger.error(MessageFormat.format(
                        "Cannot click web element located by ''{0}''. Root cause: Web element is not enable", we));
            }

        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot click web element located by ''{0}''. Root cause: {1}", we,
                    e.getMessage()));
        }
    }

    public void close(String browser) {
        logger.info(MessageFormat.format("Closing {0} browser ", browser.toUpperCase()));
        try {
            WebDriver driver = driverManager.getDriver();
            driver.close();
            logger.info(MessageFormat.format("Closed {0} browser successfully", browser.toUpperCase()));
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot close browser {0}. Root cause: {1}", browser, e.getMessage()));
        }
    }

    public void delayInSeconds(int seconds) {
        logger.info(MessageFormat.format("Delaying ''{0}'' seconds", seconds));
        try {
            Thread.sleep(seconds * 1000L);
            logger.info(MessageFormat.format("Delayed ''{0}'' seconds successfully", seconds));
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot delay ''{0}'' seconds. Root cause: {0} ", e.getMessage()));
        }
    }

    public void deselectAllOption(String locator) {
        WebElement we = findWebElement(locator);
        Select se = new Select(we);
        try {
            if (verifyElementSelected(locator)) {
                logger.info(MessageFormat.format("Deselecting all options located by {0}", locator));
                se.deselectAll();
                logger.info(MessageFormat.format("Deselected all options located by {0} successfully.", locator));
            } else {
                logger.error(MessageFormat.format("No option in select tag located by {0} is selected", locator));
            }
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot deselect all options. Root cause: {0}", e.getMessage()));
        }

    }

    public void deselectByText(String locator, String text, String text2) {
        WebElement we = findWebElement(locator);
        logger.info(MessageFormat.format("Deselecting by text ''{0},{1}'' for the element located by ''{2}''", text,
                text2, locator));
        try {
            Select se = new Select(we);
            if (verifyElementSelected(locator)) {
                if (se.isMultiple()) {
                    se.deselectByVisibleText(text);
                    se.deselectByVisibleText(text2);
                    logger.info(MessageFormat.format("Deselected the element by text ''{0},{1}'' successfully.", text,
                            text2));
                } else {
                    se.deselectByVisibleText(text);
                    logger.info(
                            MessageFormat.format("Deselected the element by visible text ''{0}'' successfully.", text));
                }
            } else {
                logger.error(MessageFormat.format("No option in select tag located by {0} is selected", locator));
            }

        } catch (Exception e) {
            logger.error(MessageFormat.format(
                    "Cannot deselect the element located by ''{0}'' by text {1} or {2}. Root cause: {3}", locator, text,
                    text2, e.getMessage()));
        }
    }

    public void deselectByIndex(String locator, int index, int index2) {
        WebElement we = findWebElement(locator);
        logger.info(MessageFormat.format("Deselecting by index ''{0},{1}'' for the element located by ''{2}''", index,
                index2, locator));
        try {
            Select se = new Select(we);
            if (verifyElementSelected(locator)) {
                if (se.isMultiple()) {
                    se.deselectByIndex(index);
                    se.deselectByIndex(index2);
                    logger.info(MessageFormat.format("Deselected the element by index ''{0},{1}'' successfully.", index,
                            index2));
                } else {
                    se.deselectByIndex(index);
                    logger.info(MessageFormat.format("Deselected the element by index ''{0}'' successfully.", index));
                }
            } else {
                logger.error(MessageFormat.format("No option in select tag located by {0} is selected", locator));
            }

        } catch (Exception e) {
            logger.error(MessageFormat.format(
                    "Cannot deselect the element located by ''{0}'' by index {1} or {2}. Root cause: {3}", locator,
                    index, index2, e.getMessage()));
        }
    }

    public void deselectByValue(String locator, String value, String value2) {
        WebElement we = findWebElement(locator);
        logger.info(MessageFormat.format("Deselecting by value ''{0},{1}'' for the element located by ''{2}''", value,
                value2, locator));
        try {
            Select se = new Select(we);
            if (verifyElementSelected(locator)) {
                if (se.isMultiple()) {
                    se.selectByValue(value);
                    se.selectByValue(value2);
                    logger.info(MessageFormat.format("Deselected the element by value ''{0},{1}'' successfully.", value,
                            value2));
                } else {
                    se.selectByValue(value);
                    logger.info(MessageFormat.format("Deselected the element by value ''{0}'' successfully.", value));
                }
            } else {
                logger.error(MessageFormat.format("No option in select tag located by {0} is selected", locator));
            }

        } catch (Exception e) {
            logger.error(MessageFormat.format(
                    "Cannot deselect the element located by ''{0}'' by value {1} or {2}. Root cause: {3}", locator,
                    value, value2, e.getMessage()));
        }
    }

    public void enhancedClick(WebElement we) {
        try {
            WebDriver driver = driverManager.getDriver();
            logger.info(MessageFormat.format("Clicking the element located by {0}", we));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", we);
            logger.info(MessageFormat.format("Clicked the element located by {0} successfully", we));
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot click the web element. Root cause: {0}", e.getMessage()));
        }
    }

    public WebElement findWebElement(String locator) {
        WebDriver driver = driverManager.getDriver();
        logger.info(MessageFormat.format("Finding web element located by ''{0}''", locator));
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
                    .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
            WebElement we = wait.until(new Function<WebDriver, WebElement>() {
                @Override
                public WebElement apply(WebDriver t) {
                    return driver.findElement(By.xpath(locator));
                }
            });
            if (we != null) {
                logger.info(MessageFormat.format("Found {0} web element located by ''{1}''", 1, locator));
                return we;
            } else {
                logger.error(MessageFormat.format("Cannot find web element located by ''{0}''", locator));
            }
        } catch (TimeoutException e) {
            logger.error(MessageFormat.format("Cannot find web element located by ''{0}''. Root cause: {1}", locator,
                    e.getMessage()));
        }
        return null;
    }

    public List<WebElement> findElements(String locator) {
        WebDriver driver = driverManager.getDriver();
        logger.info(MessageFormat.format("Finding the web elements located by {0}", locator));
//        try {
//            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
//                    .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
//            List<WebElement> we = wait.until(new Function<WebDriver, List<WebElement>>() {
//                @Override
//                public List<WebElement> apply(WebDriver input) {
//                    return driver.findElements(By.xpath(locator));
//                }
//            });
        try{
        List<WebElement> we=driver.findElements(By.xpath(locator));
            if (we != null) {
                logger.info(MessageFormat.format("Found {0} web element located by ''{1}''", we.size(), locator));
                return we;
            } else {
                logger.error(MessageFormat.format("Cannot find web element located by ''{0}''", locator));
            }
        } catch (TimeoutException e) {
            logger.error(MessageFormat.format("Cannot find web element located by ''{0}''. Root cause: {1}", locator,
                    e.getMessage()));
        }
        return null;
    }

    public String getAttribute(String locator, String name) {
        WebDriver driver = driverManager.getDriver();
        logger.info(MessageFormat.format("Getting attribute ''{0}'' of the element located by {1}", name, locator));
        WebElement we = driver.findElement(By.xpath(locator));
        String value = null;
        try {
            value = we.getAttribute(name);
            logger.info(
                    MessageFormat.format("Got attribute ''{0}'' of the element located by ''{1}'' is {2} successfully",
                            name, locator, value));
            return value;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot get attribute. Root cause: {0}", e.getMessage()));
            return value;
        }

    }

    // -----------------------WEBDRIVER BROWSER COMMAND------------------------

    public static String getCurrentUrl() {
        WebDriver driver = driverManager.getDriver();
        logger.info("Preparing to get current url");
        String currentUrl = null;
        try {
            currentUrl = driver.getCurrentUrl();
            logger.info(MessageFormat.format("Got current url {0} successfully", currentUrl));
            return currentUrl;
        } catch (Exception e) {
            logger.error(
                    MessageFormat.format("Cannot get current url {0}. Root cause: {1}", currentUrl, e.getMessage()));
            return currentUrl;
        }
    }

    public String getTitle() {
        WebDriver driver = driverManager.getDriver();
        logger.info("Preparing to get title ");
        String pageTitle = null;
        try {
            pageTitle = driver.getTitle();
            logger.info(MessageFormat.format("Got the title: {0} successfully", pageTitle));
            return pageTitle;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot get title. Root cause: {0}", e.getMessage()));
            return pageTitle;
        }
    }

    public String getPageSource() {
        WebDriver driver = driverManager.getDriver();
        logger.info("Preparing to get the page source");
        String pageSource = null;
        try {
            pageSource = driver.getPageSource();
            logger.info(MessageFormat.format("The page source is: {0}", pageSource));
            return pageSource;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot find the page source. Root cause: {0}", e.getMessage()));
            return pageSource;
        }
    }

    public String getCssValue(String locator, String property) {
        WebDriver driver = driverManager.getDriver();
        WebElement we = driver.findElement(By.xpath(locator));
        logger.info(
                MessageFormat.format("Getting the css value ''{0}'' of the element located by {1}", property, locator));
        String value = null;
        try {
            value = we.getCssValue(property);
            logger.info(
                    MessageFormat.format("Got property ''{0}'' of the element located by ''{1}'' is {2} successfully",
                            property, locator, value));
            return value;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot get property. Root cause: {0}", e.getMessage()));
            return value;
        }

    }

    public WebElement getFirstOpt(String locator) {
        WebElement we = findWebElement(locator);
        WebElement firstOpt = null;
        try {
            Select se = new Select(we);
            logger.info(
                    MessageFormat.format("Getting the first selected option of the element located by {0}", locator));
            firstOpt = se.getFirstSelectedOption();
            logger.info(MessageFormat.format("Got the first selected option of the element located by {0} successfully",
                    locator));
            String opt = se.getFirstSelectedOption().getText();
            logger.info(MessageFormat.format("The first selected option is {0}", opt));
            return firstOpt;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot get first selected option. Root cause: {0}", e.getMessage()));
            return firstOpt;
        }
    }

    public int getIndex(String value, String locator) {
        List<WebElement> elements = findElements(locator);
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getText().equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public Point getLocation(String locator) {
        WebDriver driver = driverManager.getDriver();
        logger.info(MessageFormat.format("Getting the point of the element located by ''{0}'' ", locator));
        WebElement we = driver.findElement(By.xpath(locator));
        Point point = null;
        try {
            point = we.getLocation();
            logger.info(MessageFormat.format(
                    "Got the x cordinate ''{0}'' and y cordinate ''{1}'' of the element located by ''{2}''", point.x,
                    point.y, locator));
            return point;
        } catch (Exception e) {
            logger.error(
                    MessageFormat.format("Cannot get the location of the element. Root cause: {0}", e.getMessage()));
            return point;
        }
    }

    public void getTagName(String locator) {
        WebDriver driver = driverManager.getDriver();
        WebElement we = driver.findElement(By.xpath(locator));
        logger.info("Getting the tagname");
        try {
            we.getTagName();
            logger.info(MessageFormat.format("Got tag name ''{0}'' of the element located by {1} successfully",
                    we.getTagName(), locator));
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot get tag name. Root cause: {0}", e.getMessage()));
        }

    }

    public String getText(WebElement we) {
        String text = null;
        logger.info(MessageFormat.format("Getting text of web element located by ''{0}''", we));
        try {
            text = we.getText();
            logger.info(
                    MessageFormat.format("Got text of web element located by ''{0}'' successfully: ''{1}''", we, text));
        } catch (Exception e) {
            logger.info(MessageFormat.format(
                    "Cannot get text of web element located by ''{0}''. Root cause is: ''{1}''", we, e.getMessage()));
        }
        return text;
    }
    public String getText(String locator) {
        WebDriver driver = driverManager.getDriver();
        WebElement we = driver.findElement(By.xpath(locator));
        logger.info("Getting text");
        try {
            String actualText = we.getText();
            logger.info(MessageFormat.format("Got text ''{0}'' of the element located by {1} successfully", actualText,
                    locator));
            return actualText;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot get text. Root cause: {0}", e.getMessage()));
        }
        return null;
    }

    public void getUrl(String url) {
        WebDriver driver = driverManager.getDriver();
        logger.info(MessageFormat.format("Getting ''{0}''", url));
        try {
            driver.get(url);
            logger.info(MessageFormat.format("Got ''{0}'' successfully", url));
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot get ''{0}''. Root cause: {1}", url, e.getMessage()));
        }
    }

    public List<WebElement> getOpt(String locator) {
        WebElement we = findWebElement(locator);
        List<WebElement> elements = null;
        try {
            Select se = new Select(we);
            logger.info(MessageFormat.format("Getting options of the element located by {0}", locator));
            elements = se.getOptions();
            logger.info(MessageFormat.format("Got options of the element located by {0} successfully. The options: ",
                    locator));
            String value = null;
            for (int i = 0; i < elements.size(); i++) {
                value = elements.get(i).getText();
                logger.info(value);
            }
            return elements;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot get options. Root cause: {0}", e.getMessage()));
            return elements;
        }
    }

    public Dimension getSize(String locator) {
        WebDriver driver = driverManager.getDriver();
        logger.info(MessageFormat.format("Getting the size of the element located by {0} ", locator));
        WebElement we = driver.findElement(By.xpath(locator));
        Dimension dimension = null;
        try {
            dimension = we.getSize();
            logger.info(MessageFormat.format("Got the size of the element located by {0} is {1}", locator, dimension));
            return dimension;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot get the size of the element. Root cause: {0}", e.getMessage()));
            return dimension;
        }

    }

    public void maximizeWindow() {
        logger.info("Maximizing the window");
        try {
            WebDriver driver = driverManager.getDriver();
            driver.manage().window().maximize();
            logger.info("Maximized the window successfully");
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot maximize the window. Root cause: {0}", e.getMessage()));
        }

    }

    /// ----------------------------------------------------------------------/////
    /// ------------------NAVIGATION COMMAND----------------------------------/////

    public void navigateToUrl(String url) {
        WebDriver driver = driverManager.getDriver();
        logger.info(MessageFormat.format("Navigating to {0}", url));
        try {
            driver.navigate().to(url);
            logger.info(MessageFormat.format("Navigated to {0} successfully", url));
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot navigate to {0}. Root cause: {1}", url, e.getMessage()));
        }

    }

    public void back(String url) {
        WebDriver driver = driverManager.getDriver();
        logger.info(MessageFormat.format("Navigating back to {0}", url));
        try {
            driver.navigate().back();
            logger.info(MessageFormat.format("Navigated back to {0} successfully", url));
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot navigate back to {0}. Root cause: {1}", url, e.getMessage()));
        }

    }

    public void forward(String url) {
        WebDriver driver = driverManager.getDriver();
        logger.info(MessageFormat.format("Navigating forward to {0}", url));
        try {
            driver.navigate().forward();
            logger.info(MessageFormat.format("Navigated forward to {0} successfully", url));
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot navigate forward to {0}. Root cause: {1}", url, e.getMessage()));
        }

    }

    public void refresh() {
        WebDriver driver = driverManager.getDriver();
        logger.info("Refreshing the page");
        try {
            driver.navigate().refresh();
            logger.info("Refreshed the page successfully");
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot refresh. Root cause: {0}", e.getMessage()));
        }
    }

    public void open(String browser, String... url) {

        driverManager = DriverManagerFactory.getManager(DriverType.valueOf(browser.toUpperCase()));
        logger.info(MessageFormat.format("Launching ''{0}'' browser", browser));
        try {
            driverManager.getDriver();
            logger.info(MessageFormat.format("Launched ''{0}'' browser successfully", browser));
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot launch {0} browser. Root cause: {1} ", browser, e.getMessage()));
        }

        String rawUrl=url.length >0 ? url[0]:"";

        try {
            if(rawUrl !=null && !rawUrl.isEmpty()) {
                WebDriver driver = driverManager.getDriver();
                logger.info(MessageFormat.format("Navigating to ''{0}''", rawUrl));
                driver.get(rawUrl);
            }
            logger.info(MessageFormat.format("Navigated to ''{0}'' successfully", rawUrl));
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot navigate to {0}. Root cause: {1}", rawUrl, e.getMessage()));
        }
    }

    public void open(String browser, String url) {
        driverManager = DriverManagerFactory.getManager(DriverType.valueOf(browser.toUpperCase()));
        logger.info(MessageFormat.format("Launching ''{0}'' browser", browser));
        try {
            driverManager.getDriver();
            logger.info(MessageFormat.format("Launched ''{0}'' browser successfully", browser));
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot launch {0} browser. Root cause: {1} ", browser, e.getMessage()));
        }
        logger.info(MessageFormat.format("Navigating to ''{0}''", url));
        try {
            WebDriver driver = driverManager.getDriver();
            driver.get(url);
            logger.info(MessageFormat.format("Navigated to ''{0}'' successfully", url));
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot navigate to {0}. Root cause: {1}", url, e.getMessage()));
        }
    }

    ////// ---------------------------DROPDOWN------------------------------------///////

    public void selectByIndex(String locator, int index) {
        WebElement we = findWebElement(locator);
        logger.info(MessageFormat.format("Selecting by index ''{0}'' for the element located by ''{1}''", index, locator));
        try {
            Select se = new Select(we);
            if (se.isMultiple()) {
                se.selectByIndex(index);
                se.selectByIndex(index);
                logger.info(MessageFormat.format("Selected the element by index {0} successfully.", index));
            } else {
                se.selectByIndex(index);
                logger.info(MessageFormat.format("Selected the element by index {0} successfully.", index));
            }
        } catch (Exception e) {
            logger.error(MessageFormat.format(
                    "Cannot select the element located by ''{0}'' by index {1}. Root cause: {2}", locator, index, e.getMessage()));
        }
    }

    public void selectByValue(String locator, String value) {
        WebElement we = findWebElement(locator);
        logger.info(MessageFormat.format("Selecting by value ''{0}' for the element located by ''{1}''", value,locator));
        try {
            Select se = new Select(we);
            if (se.isMultiple()) {
                se.selectByValue(value);
                se.selectByValue(value);
                logger.info(
                        MessageFormat.format("Selected the element by value ''{0}' successfully.", value));
            } else {
                // logger.error(MessageFormat.format("Cannot select the element multiple located
                // by ''{0}''", locator));
                se.selectByValue(value);
                logger.info(MessageFormat.format("Selected the element by value ''{0}'' successfully.", value));
            }
        } catch (Exception e) {
            logger.error(MessageFormat.format(
                    "Cannot select the element located by ''{0}'' by value {1}. Root cause: {2}", locator, value, e.getMessage()));
        }
    }

    public void selectByText(String locator, String text) {
        WebElement we = findWebElement(locator);
        logger.info(MessageFormat.format("Selecting by text ''{0}'' for the element located by ''{1}''", text, locator));
        try {
            Select se = new Select(we);
            if (se.isMultiple()) {
                se.selectByVisibleText(text);
                se.selectByVisibleText(text);
                logger.info(
                        MessageFormat.format("Selected the element by text ''{0}'' successfully.", text));
            } else {
                // logger.error(MessageFormat.format("Cannot select the element multiple located
                // by ''{0}''", locator));
                se.selectByVisibleText(text);
                logger.info(MessageFormat.format("Selected the element by visible text ''{0}'' successfully.", text));
            }
        } catch (Exception e) {
            logger.error(MessageFormat.format(
                    "Cannot select the element located by ''{0}'' by text {1}. Root cause: {2}", locator, text,e.getMessage()));
        }
    }

    public void sendKey(String locator, String value) {
        try {
            WebElement we = findWebElement(locator);
            logger.info(MessageFormat.format("Sending keys to the web element located by {0}", locator));
            we.sendKeys(value);
            logger.info(MessageFormat.format("Sent keys to the web element located by {0} successfully", locator));
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot send key to the web element located by {0}. Root cause: {1}",
                    locator, e.getMessage()));
        }
    }

    public void sendKey(String locator, Keys value) {
        try {
            WebElement we = findWebElement(locator);
            logger.info(MessageFormat.format("Sending keys to the web element located by {0}", locator));
            we.sendKeys(value);
            logger.info(MessageFormat.format("Sent keys to the web element located by {0} successfully", locator));
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot send key to the web element located by {0}. Root cause: {1}",
                    locator, e.getMessage()));
        }
    }

    public void setText(String locator, String text) {
        WebElement we = findWebElement(locator);
        try {
            logger.info(MessageFormat.format("Setting text to the element located by {0}", locator));
            if (we.isEnabled()) {
                clear(locator);
                sendKey(locator, text);
                logger.info(MessageFormat.format("Set text to the element located by {0} successfully", locator));
            } else {
                logger.error(MessageFormat.format("Cannot set text to the element located by {0}", locator));
            }
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot set text to the element located by {0}. Root cause: {1}", locator,
                    e.getMessage()));
        }
    }

    public void setEnhancedText(String locator, String value) {
        WebElement element = findWebElement(locator);
        try {
            WebDriver driver = driverManager.getDriver();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            logger.info(MessageFormat.format("Setting text to the element located by {0}", locator));
            js.executeScript("arguments[0].value='"+value+"'", element);
            logger.info(MessageFormat.format("Set text to the element located by {0} successfully", locator));
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot set text to the element located by {0}. Root cause: {1}", locator,
                    e.getMessage()));
        }

    }

    public void scrollToElement(String locator) {
        WebElement element = findWebElement(locator);
        try {
            WebDriver driver = driverManager.getDriver();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            logger.info(MessageFormat.format("Scrolling to the element located by {0}", locator));
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            logger.info(MessageFormat.format("Scrolled to the element located by {0} successfully", locator));
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot scroll to element. Root cause: {0}", e.getMessage()));
        }

    }

    public static void scrollToPosition(int x, int y) {
        try {
            WebDriver driver = driverManager.getDriver();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            logger.info(MessageFormat.format("Scrolling to position ({0},{1})", x, y));
            js.executeScript("window.scrollBy(" + x + "," + y + ");");
            logger.info(MessageFormat.format("Scrolled to position ({0},{1}) successfully", x, y));
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot scroll to position. Root cause: {0}", e.getMessage()));
        }

    }

    public void submit(String locator) {
        WebDriver driver = driverManager.getDriver();
        try {
            WebElement we = driver.findElement(By.xpath(locator));
            logger.info("Preparing to submit info");
            we.submit();
            logger.info("Subimtted successfully ", locator);
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot submit. Root cause: {0}", e.getMessage()));
        }
    }

    public void switchToFrame(String locator) {
        logger.info(MessageFormat.format("Switching to frame located by {0}", locator));
        try {
            WebElement el = findWebElement(locator);
            WebDriver driver = driverManager.getDriver();
            driver.switchTo().frame(el);
            logger.info(MessageFormat.format("Switched to frame located by {0} successfully", locator));
        } catch (Exception e) {
            logger.info(MessageFormat.format("Cannot switch to frame. Root cause: {0}", e.getMessage()));
        }
    }

    public boolean verifyElementClickable(String locator) {
        WebElement we = findWebElement(locator);
        try {
            if (we.isEnabled()) {
                logger.info(MessageFormat.format("The element located by {0} which is clickable. Return true", locator));
                click(locator);
                return true;
                // sendKey(locator,value);
            }
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot verify the clickable element located by {0}. Root cause: {1}", locator,e.getMessage()));
            return false;
        }
        return false;
    }

    public boolean verifyElementNotClickable(String locator) {
        WebElement we = findWebElement(locator);
        try {
            if (!we.isEnabled()) {
                logger.info(MessageFormat.format("The element located by {0} which not clickable. Return true", locator));
                return true;
            }
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot verify the clickable of the web element located by {0}. Root cause: {1}", locator,e.getMessage()));
            return false;
        }
        return false;
    }

    public boolean verifyElementSelected(String locator) {
        WebElement we = findWebElement(locator);
        logger.info(MessageFormat.format("Verifying element located by {0} is selected", locator));
        // boolean status=false;
        try {
            if (we.isSelected()) {
                logger.info("The web element is selected");
                return true;
            }
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot verify the web element located by {0}. Root cause: {1}", locator,
                    e.getMessage()));
            return false;
        }
        return false;
    }

    public boolean verifyElementNotSelected(String locator) {
        WebElement we = findWebElement(locator);
        logger.info(MessageFormat.format("Verifying element located by {0} is not selected", locator));
        // boolean status=false;
        try {
            if (!we.isSelected()) {
                logger.info("The web element is not selected");
                return true;
            }
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot verify the web element located by {0}. Root cause: {1}", locator,
                    e.getMessage()));
            return false;
        }
        return false;
    }

    public boolean verifyElementText(String locator, String exceptedText) {
        WebElement we = findWebElement(locator);
        try {
            logger.info(MessageFormat.format("Verifying text of web element located by {0}", locator));
            String actualText = we.getText();
            if (actualText.equals(exceptedText)) {
                logger.info(MessageFormat.format("Actual text ''{0}'' and excepted text ''{1}'' are the same",
                        actualText, exceptedText));
                return true;
            } else {
                logger.info(MessageFormat.format("Actual text ''{0}'' and excepted text ''{1}'' are not the same",
                        actualText, exceptedText));
                return false;
            }
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot verify text of web element located by {0}. Root cause: {1}",
                    locator, e.getMessage()));
        }
        return false;
    }

    public boolean waitElementPresent(String locator) {
        WebDriver driver = driverManager.getDriver();
        logger.info(MessageFormat.format("Waiting for present element located by ''{0}''", locator));
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
                    .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
            WebElement we = wait.until(new Function<WebDriver, WebElement>() {
                @Override
                public WebElement apply(WebDriver t) {
                    return driver.findElement(By.xpath(locator));
                }
            });
            if (we != null) {
                logger.info(MessageFormat.format("Waited for present element located by ''{0}'' successfully", locator));
                return true;
            }else {
                logger.error(MessageFormat.format("Cannot wait for present element located by {0}", locator));
                return false;
            }
        } catch (TimeoutException e) {
            logger.error(MessageFormat.format("Cannot wait for present element located by ''{0}''. Root cause: {1}", locator,
                    e.getMessage()));
        }
        return false;
    }

    public boolean waitElementNotPresent(String locator) {
        WebDriver driver = driverManager.getDriver();
        logger.info(MessageFormat.format("Wait for present element located by ''{0}''", locator));
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
                    .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
            WebElement we = wait.until(new Function<WebDriver, WebElement>() {
                @Override
                public WebElement apply(WebDriver t) {
                    return driver.findElement(By.xpath(locator));
                }
            });
            if (we == null) {
                logger.info(MessageFormat.format("Waited for not present element located by ''{0}'' successfully", locator));
                return true;
            }else {
                logger.error(MessageFormat.format("Cannot wait for not present element located by {0}", locator));
                return false;
            }
        } catch (TimeoutException e) {
            logger.error(MessageFormat.format("Cannot wait for not present element located by ''{0}''. Root cause: {1}", locator,
                    e.getMessage()));
        }
        return false;
    }

    public boolean verifyElementDisplay(String locator){
        WebDriver driver = driverManager.getDriver();
        WebElement we=driver.findElement(By.xpath(locator));
        logger.info(MessageFormat.format("Verifying the element located by ''{0}''", locator));
        if (we.isDisplayed()) {
            logger.info(MessageFormat.format("Verified display element located by ''{0}'' successfully", locator));
            return true;
        }else {
            logger.error(MessageFormat.format("Cannot verify for display element located by {0}", locator));
        }
        return false;
    }

    public boolean waitElementVisible(String locator) {
        WebDriver driver = driverManager.getDriver();

        logger.info(MessageFormat.format("The visible element located by ''{0}'' is waiting", locator));
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
                    .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
            WebElement we=wait.until(new Function<WebDriver, WebElement>() {
                @Override
                public WebElement apply(WebDriver webDriver) {
                    return driver.findElement(By.xpath(locator));
                }
            });
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
//            driver.findElement(By.xpath(locator)).click();
            if (we.isDisplayed()) {
                logger.info(MessageFormat.format("Waited for visible element located by ''{0}'' successfully", locator));
                return true;
            }else {
                logger.error(MessageFormat.format("Cannot wait for visible element located by {0}", locator));
                return false;
            }
        } catch (TimeoutException e) {
            logger.error(MessageFormat.format("Cannot wait for visible element located by ''{0}''. Root cause: {1}",
                    locator, e.getMessage()));
        }
        return false;
    }


    public boolean waitElementNotVisible(String locator) {
        WebDriver driver = driverManager.getDriver();
        logger.info(MessageFormat.format("Waiting for not visible element located by ''{0}''", locator));
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
                    .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
            WebElement we = wait.until(new Function<WebDriver, WebElement>() {
                @Override
                public WebElement apply(WebDriver t) {
                    return driver.findElement(By.xpath(locator));
                }
            });
            if (!we.isDisplayed()) {
                logger.info(MessageFormat.format("Waited for not visible element located by ''{0}'' successfully", locator));
                return true;
            }else {
                logger.error(MessageFormat.format("Cannot wait for not visible element located by {0}", locator));
                return false;
            }
        } catch (TimeoutException e) {
            logger.error(MessageFormat.format("Cannot wait for not visible element located by ''{0}''. Root cause: {1}",
                    locator, e.getMessage()));
        }
        return false;
    }

    public int convert(String value) {
        int p = 0;
        if (!value.equals("")) {
            return p = Integer.parseInt(value);
        } else {
            return p;
        }

    }
}
