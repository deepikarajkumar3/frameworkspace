package auth;

import baseclass.APITestBase;
import helper.PropertyHelper;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class authToken extends APITestBase    {
    String sHost;
    String sEndpoint;
    String contentType;

    String username;
    String password;
    String event;
    String downloadlink;
    String redirectURI;
    String switchUrl;




    @BeforeClass
       public void getEnvironment(ITestContext testContext) throws IOException {
        sHost = PropertyHelper.getHostStringFromPropertiesFile(sPropertyFileName, authToken.class);
        String clientcode = PropertyHelper.getValuePropertiesFile(sPropertyFileName, "clientcode");
        sEndpoint = "handler=AUTH000&clientcode=svtsys1&automation=null&ord=null";
       // handler=AUTH000&clientcode=svtsys1&automation=null&ord=null
        contentType = "application/x-www-form-urlencoded";
        username = PropertyHelper.getValuePropertiesFile(sPropertyFileName, "username");
        password = PropertyHelper.getValuePropertiesFile(sPropertyFileName, "password");
        event = PropertyHelper.getValuePropertiesFile(sPropertyFileName, "event");
        downloadlink = PropertyHelper.getValuePropertiesFile(sPropertyFileName, "downloadlink");
        redirectURI = PropertyHelper.getValuePropertiesFile(sPropertyFileName, "redirectURI");
        switchUrl = PropertyHelper.getValuePropertiesFile(sPropertyFileName, "switchUrl");
        //sHost = "https://test.myvisualiq.com/iq/IQWFrontController?handler=AUTH000&clientcode=svtsys1&automation=null&ord=null";

    }

    @Test
        public void getToken(ITestContext testContext) {

        Map<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", contentType);

        Map<String, String> queryparam = new HashMap<String, String>();
        queryparam.put("username",username);
        queryparam.put("password",password);
        queryparam.put("event", event);
        queryparam.put("downloadlink", null);
        queryparam.put("redirectURI", null);
        queryparam.put("switchUrl", null);



            RequestSpecification request = RestAssured.given().headers(headerMap).queryParams(queryparam);
            Response response = request.post(sHost+sEndpoint);
            logInfo("Endpoint :"+sHost+sEndpoint);
            logResponse(response);


           // System.out.println(response.asString());

            int statuscode = response.getStatusCode();
            System.out.println(statuscode);

        String cookie = response.getCookies().get("JSESSIONID");

        String cookie2 = "JSESSIONID="+cookie;

        setSuiteContext("Cookie-ID", "JSESSIONID="+cookie, testContext);

        System.out.println(cookie);
        System.out.println(cookie2);
      logInfo("CookieId"+cookie);
    }

    }


