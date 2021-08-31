package Settings;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;


import java.util.HashMap;
import java.util.Map;

public class Configurations{

    @BeforeTest
    @Parameters({"browser", "version"})
    protected void setUp(@Optional("browser") String browser, @Optional("version") String version) {
        new Configurations().browserConfiguration(browser, version);
    }


    public void browserConfiguration(String browser, String version) {
        Configuration.remote = "http://192.168.0.136:4444/wd/hub";
        //Configuration.browser = browser;
        //Configuration.browserVersion = version;

        Map<String, Boolean> options = new HashMap<>();
        options.put("enableVNC", true);
        options.put("enableVideo", false);
        options.put("enableLog", true);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        capabilities.setCapability(CapabilityType.BROWSER_VERSION, version);
        capabilities.setCapability("selenoid:options", options);
        Configuration.browserCapabilities = capabilities;

    }

    @AfterTest
    protected void tearDown() {
        new Helpers().closeDriver();
    }

}
