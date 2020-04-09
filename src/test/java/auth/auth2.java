package auth;

import baseclass.APITestBase;
import helper.PropertyHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class auth2 extends APITestBase {

        String sHost;
        String sPropertyFileName;
        String sEndpoint;
        String contentType;

        @BeforeClass
        public void getEnvironment() throws IOException {
            sHost = PropertyHelper.getHostStringFromPropertiesFile(sPropertyFileName, auth2.class);
            sEndpoint = "";
            contentType = "application/x-www-form-urlencoded";
        }

        @Test
        public void getToken() {

            Map<String, String> headerMap = new HashMap<String, String>();
            headerMap.put("Content-Type", contentType);

            Map<String, String> queryparam = new HashMap<String, String>();
            queryparam.put("username","");
            queryparam.put("password", "");
            queryparam.put("event", "login");
            queryparam.put("downloadlink", "null");
            queryparam.put("redirectURI", "null");
            queryparam.put("switchUrl", "null");



            RequestSpecification request = RestAssured.given().headers(headerMap).queryParams(queryparam);
            Response response = request.post(sHost);

            // System.out.println(response.asString());

            int statuscode = response.getStatusCode();
            System.out.println(statuscode);

            String cookie = response.getCookies().get("JSESSIONID");
            String cookie2 = response.getCookies().get("Set-Cookie");

            System.out.println(cookie);



        }

    }




