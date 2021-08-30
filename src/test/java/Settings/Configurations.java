package Settings;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.MalformedURLException;

import java.util.HashMap;
import java.util.Map;

public class Configurations{

    public void browserConfiguration(String browser) {
        Configuration.remote = "http://192.168.0.136:4444/wd/hub";
        //capabilities.setCapability("browserName", browser);

        Map<String, Boolean> options = new HashMap<>();
        options.put("enableVNC", true);
        options.put("enableVideo", false);
        options.put("enableLog", true);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        //capabilities.setBrowserName("chrome");
        //capabilities.setVersion("92.0");
        capabilities.setCapability("selenoid:options", options);
        Configuration.browserCapabilities = capabilities;

    }

    @BeforeClass
    @Parameters("browser")
    protected void setUp(@Optional("browser") String browser) {
        new Configurations().browserConfiguration(browser);
    }

    @AfterMethod
    protected void tearDown() {
        new Helpers().closeDriver();
    }

}
