package com.vmo.training.demo.utils;

import com.vmo.training.demo.utils.keywords.WebUI;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    WebUI action=new WebUI();
    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("\nTestcase is successful: "+result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("\nTestcase is fail: "+result.getName());
        try{
            action.takeScreenShot(result.getName());
        }catch (Exception e){
            System.out.println("\nException while taking screenshot: "+e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("\nTestcase is skipped: "+result.getName());
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
