package baseclass;

import org.testng.ITestContext;

public class Base {
    protected void setSuiteContext(String sKey, String sValue,  ITestContext testContext) {
        testContext.getSuite().setAttribute(sKey, sValue);
    }

        protected String getSuiteContextValue(String sKey, ITestContext testContext) {

            String sValue = "";
            Object storedObject = testContext.getSuite().getAttribute(sKey);
            if (storedObject != null) {
                sValue = testContext.getSuite().getAttribute(sKey).toString();
            }
            return sValue;
        }
    }

