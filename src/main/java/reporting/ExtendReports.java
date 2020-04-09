package reporting;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.util.Arrays;

public class ExtendReports {
    public void attachReporter(ExtentReporter... reporter) {
        Arrays.stream(reporter).forEach(this::register);
    }

    private void register(ExtentReporter extentReporter) {
    }

    public void flush() {
    }
}
