package helper;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyHelper {
    public static String getHostStringFromPropertiesFile(String sPropertyFileName, Class clazz)
            throws IOException {
        String sClassName = clazz.getSimpleName();
        return GetPropertiesValues
                .getTestExecutionPropertyValue(sPropertyFileName, "host_" + sClassName);
    }

    public static String getValuePropertiesFile(String sPropertyFileName, String sKey)
            throws IOException {
        return GetPropertiesValues.getTestExecutionPropertyValue(sPropertyFileName, sKey);
    }
}

