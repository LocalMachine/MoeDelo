package Settings;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;


import java.util.HashMap;
import java.util.Map;

public class Configurations{

    public void browserConfiguration(String browser, String version) {
        Configuration.remote = "http://192.168.0.136:4444/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();

        Map<String, Boolean> options = new HashMap<>();
        options.put("enableVNC", true);
        options.put("enableVideo", false);
        options.put("enableLog", true);


        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", version);
        capabilities.setCapability("selenoid:options", options);
        Configuration.browserCapabilities = capabilities;

    }

    @BeforeClass
    @Parameters({"browser", "version"})
    protected void setUp(@Optional("browser") String browser, @Optional("version") String version) {
        new Configurations().browserConfiguration(browser, version);
    }

    @AfterMethod
    protected void tearDown() {
        new Helpers().closeDriver();
    }

}
