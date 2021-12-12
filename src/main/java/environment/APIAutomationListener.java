package environment;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import config.FrameworkConfig;
import constants.LogConstants;
import extentreport.ExtentManager;
import extentreport.ExtentTestManager;
import io.restassured.RestAssured;
import org.apache.log4j.Logger;
import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class APIAutomationListener implements ITestListener, IExecutionListener {

    private static final Logger LOGGER = Logger.getLogger(APIAutomationListener.class);

    private static final String MESSAGE = "Hello <users/all> - Latest test report for API tests is generated! - %s \n"
            + "Test report https://karrosautomationreports.s3-us-west-2.amazonaws.com/"
            + FrameworkConfig.getInstance().getProperty("test.jenkins.job") + "/%s";

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        ExtentTestManager.getTest().log(Status.PASS, MarkupHelper.createLabel(
                String.format(LogConstants.THE_TEST_STATUS_LOG, iTestResult.getInstanceName(), iTestResult.getName()), ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        ExtentTestManager.getTest().log(Status.FAIL, MarkupHelper.createLabel(
                String.format(LogConstants.THE_TEST_STATUS_LOG, iTestResult.getInstanceName(), iTestResult.getName()), ExtentColor.RED));
        ExtentTestManager.getTest().error(MarkupHelper.createCodeBlock(iTestResult.getThrowable().toString()));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        ExtentTestManager.startTest(iTestContext.getCurrentXmlTest().getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    @Override
    public void onExecutionStart() {
        restAssuredSetupInGlobal();
    }

    @Override
    public void onExecutionFinish() {
        ExtentManager.getReporter().flush();
//        uploadReport2AmazonS3Bucket();
//        notifyOnHangoutsChannel();
    }

    private void restAssuredSetupInGlobal() {
        RestAssured.reset();
        RestAssured.urlEncodingEnabled = false;
    }

    /* --------------- Is used for Hangout notify ---------------
    private void notifyOnHangoutsChannel() {
        if (!Boolean.parseBoolean(System.getProperty("isSendToHangoutsChannel"))) {
            return;
        }
        Auth auth = new Auth();
        auth.setWebHookUrl(FrameworkConfig.getInstance().getProperty("test.hangouts.webhook"));
        auth.setKey(FrameworkConfig.getInstance().getProperty("test.hangouts.key"));
        auth.setToken(FrameworkConfig.getInstance().getProperty("test.hangouts.token"));

        SimpleMessage simpleMessage = new SimpleMessage();
        String message = String.format(MESSAGE, DateUtil.getTodayByString(DateUtil.DATE_TIME_PATTERN_YYYY_MM_DD_HH_MM_SS),
                ExtentManager.fileName);
        simpleMessage.setText(message);

        HangoutsChat.getInstance().send(auth, simpleMessage);
    }*/

    /* --------------- Is used for upload to AWS S3 ---------------
    private void uploadReport2AmazonS3Bucket() {
        if (!Boolean.parseBoolean(System.getProperty("isSendToHangoutsChannel"))) {
            return;
        }

        AmazonS3Bucket s3 = new AmazonS3Bucket(
                FrameworkConfig.getInstance().getProperty("test.AWSCredentials.accessKey"),
                FrameworkConfig.getInstance().getProperty("test.AWSCredentials.secrectKey"));
        s3.putObjectS3("karrosautomationreports/" + FrameworkConfig.getInstance().getProperty("test.jenkins.job"),
                ExtentManager.fileName);
    }*/

}
