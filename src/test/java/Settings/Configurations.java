package Settings;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

public class Configurations {

    /*
    public void browserConfiguration(String browser, boolean headless) {
        Configuration.browser = browser;
        Configuration.headless = headless;
        Configuration.startMaximized = true;
        Configuration.timeout = 8000;
    }

     */

    public void browserConfiguration(String browser, boolean headless) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "92.0");
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        RemoteWebDriver driver = new RemoteWebDriver(
                URI.create("http://192.168.0.136:9090:4444/wd/hub").toURL(),
                capabilities
        );
    }

    @BeforeClass
    @Parameters("browser")
    protected void setUp(@Optional("browser") String browser) throws MalformedURLException {
        new Configurations().browserConfiguration(browser, true);
    }

    @AfterMethod
    protected void tearDown() {
        new Helpers().closeDriver();
    }

}
