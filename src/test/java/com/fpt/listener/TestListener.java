package com.fpt.listener;

import com.fpt.utils.MailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);
    @Override
    public void onTestFailure(ITestResult result){
        logger.error("Test fail: "+result.getTestName());
        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            logger.error("Reason: ", throwable);
        }
    }
    @Override
    public void onTestSuccess(ITestResult result){

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
