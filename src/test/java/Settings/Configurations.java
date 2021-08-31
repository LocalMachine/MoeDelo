package Settings;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;


import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class Configurations{

    @BeforeClass
    @Parameters({"browser", "version"})
    protected void setUp(@Optional("browser") String browser, @Optional("version") String version) throws MalformedURLException {
        new Configurations().browserConfiguration(browser, version);
    }


    public RemoteWebDriver browserConfiguration(String browser, String version) throws MalformedURLException {
        //Configuration.remote = "http://192.168.0.136:4444/wd/hub";
        Map<String, Boolean> options = new HashMap<>();
        options.put("enableVNC", true);
        options.put("enableVideo", false);
        options.put("enableLog", true);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", version);
        capabilities.setCapability("selenoid:options", options);
        return new RemoteWebDriver(
                URI.create("http://192.168.0.136:4444/wd/hub").toURL(),
                capabilities);

    }

    @AfterMethod
    protected void tearDown() {
        new Helpers().closeDriver();
    }

}
