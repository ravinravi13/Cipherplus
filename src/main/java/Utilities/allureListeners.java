package Utilities;

import Cipherplus.Base.BaseClass;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class allureListeners extends BaseClass implements ITestListener {


    public static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] saveFailureScreenShots(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public String saveTextingLog(String message) {
        return message;
    }


    public void onStart(ITestContext iTestContext) {
        System.out.println("In onStart method " + iTestContext.getName());
//         iTestContext.setAttribute("WebDriver", Base.driver);
    }


    public void onFinish(ITestContext iTestContext) {
        System.out.println("In onFinish method " + iTestContext.getName());
    }


    public void onTestStart(ITestResult iTestResult) {
        System.out.println("In onTestStart method " + getTestMethodName(iTestResult) + " start");
    }


    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("In onTestSuccess method " + getTestMethodName(iTestResult) + " success");
    }


    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("In onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");

    }

    public void onTestFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            String screenshotPath = screenShot.captureScreenshot(driver, result.getName());
            attachScreenshotToAllure(screenshotPath);
        }

    }

    @Attachment(value = "Screenshot on Failure", type = "image/png")
    public byte[] attachScreenshotToAllure(String path) {
        try {
            return FileUtils.readFileToByteArray(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }











}
