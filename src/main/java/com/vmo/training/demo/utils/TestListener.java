package com.vmo.training.demo.utils;

import com.vmo.training.demo.utils.keywords.WebUI;
import com.vmo.training.demo.utils.log.LogHelper;
import io.qameta.allure.Allure;
import org.slf4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class TestListener implements ITestListener {
    WebUI action = new WebUI();
    private static final Logger logger = LogHelper.getLogger();

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("\nTestcase is successful: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.info("\nTestcase is fail: " + result.getName());
        try {
            action.takeScreenShot(result.getName());
            action.attachScreenshotToReport();
//            Allure.addAttachment(,new ByteArrayInputStream(action.takeScreenShot()));
        } catch (Exception e) {
            logger.error("\nException while taking screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("\nTestcase is skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
