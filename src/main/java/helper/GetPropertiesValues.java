package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetPropertiesValues {

    private GetPropertiesValues(){
        throw new IllegalStateException("Utility class");
    }


    public static String getTestExecutionPropertyValue(String sPropertyFileName, String sKey)
            throws IOException {
        String sValue;
        Properties properties = new Properties();
        File propertyFile = new File(
                System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                        + File.separator + "resources" + File.separator + sPropertyFileName);
        try (InputStream inputStream = new FileInputStream(propertyFile)) {
            properties.load(inputStream);
            sValue = properties.getProperty(sKey);
        }
        return sValue;
    }

}
