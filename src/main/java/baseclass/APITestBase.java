package baseclass;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporting.ExtendReports;
import reporting.ExtentReportManager;
import reporting.ExtentTestManager;

import java.io.IOException;

public class APITestBase extends Base{
    protected static String sPropertyFileName;

    private static ExtendReports extentReports;
    private static ExtendReports extent;

    static ExtentHtmlReporter htmlReporter;

    @BeforeSuite
    public static void beforeSuite(@Optional("config") String sProperties, ITestContext testContext) {
        String suiteName = testContext.getSuite().getName();
        extentReports = ExtentReportManager.createReportInstance(suiteName);

        sPropertyFileName = System.getProperty("properties", sProperties + ".properties");
        //set the propertiesFileName to testContext to be retrieved in subclasses and data-provider
        //set the propertiesFileName to ISuite so that data can be shared between two tests
        testContext.getSuite().setAttribute("propertyFileName", sPropertyFileName);

        System.out.println("reading properties file :"+sPropertyFileName);

    }

    protected void logInfo(String sInfo) {
        ExtentTest extentTest = ExtentTestManager.getExtentTest();
        extentTest.log(Status.INFO, sInfo);
    }


    protected void logResponse(Response response) {
        String sPrettyJSONResponse ;
        try {
            JSONObject jsonObject = new JSONObject(response.body().asString());
            sPrettyJSONResponse = jsonObject.toString(4);
        } catch (org.json.JSONException jsonException) {
            sPrettyJSONResponse = response.body().asString();
        }
        ExtentTest extentTest = ExtentTestManager.getExtentTest();
        extentTest
                .log(Status.INFO, "API Response: \n" + wrapToPreserveFormat(sPrettyJSONResponse) + "\n");
    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult testResult) throws IOException {
        ExtentTest extentTest = ExtentTestManager.getExtentTest();
        if (testResult.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, testResult.getMethod().getMethodName() + " is a pass");
        } else if (testResult.getStatus() == ITestResult.FAILURE) {
            extentTest.log(Status.FAIL, testResult.getThrowable().getMessage());
        } else if (testResult.getStatus() == ITestResult.SKIP) {
            extentTest.log(Status.SKIP, testResult.getThrowable().getMessage());
        }
    }


    @AfterSuite
    public static void afterSuite(ITestContext testContext) {
        extentReports.flush();
    }

    protected void assertTrue(boolean bool, String passMessage, String failMessage) {
        ExtentTest extentTest = ExtentTestManager.getExtentTest();
        if (bool) {
            extentTest.log(Status.PASS, "passed: " +wrapToPreserveFormat(passMessage));
        } else {
            extentTest.log(Status.FAIL, "failed: " +wrapToPreserveFormat(failMessage));
        }
        Assert.assertTrue(bool, failMessage);
    }

    private String wrapToPreserveFormat(String sMessage){
        return "<pre>"+sMessage+"</pre>";
    }






}
