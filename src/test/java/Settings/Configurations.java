package Settings;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

public class Configurations {

    /*
    public void browserConfiguration(String browser, boolean headless) {
        Configuration.browser = browser;
        Configuration.headless = headless;
        Configuration.startMaximized = true;
        Configuration.timeout = 8000;
    }

     */

    public void browserConfiguration(String browser, boolean headless) {
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = browser;
        Configuration.browserSize = "1920x1080";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeClass
    @Parameters("browser")
    protected void setUp(@Optional("browser") String browser) {
        new Configurations().browserConfiguration(browser, true);
    }

    @AfterMethod
    protected void tearDown() {
        new Helpers().closeDriver();
    }

}
