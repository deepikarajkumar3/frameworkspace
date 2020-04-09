package iqe;


import baseclass.APITestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@Test
public class Test_CreateGroup extends APITestBase {

    public void getrespnse(ITestContext testContext) {
       testContext.getAttribute("Cookie-ID");
        String contentType = "application/json";
        //String cookieid = "";

        String sHost = "";


        Map<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", contentType);
        headerMap.put("Cookie", getSuiteContextValue("Cookie-ID", testContext));
        //headerMap.put("Cookie", Cookie-ID);

        RequestSpecification request = RestAssured.given().headers(headerMap);
        Response response = request.get(sHost);

        //System.out.println(response.asString());

        int statuscode = response.getStatusCode();
        System.out.println(statuscode);
        System.out.println(response);
    }

}
