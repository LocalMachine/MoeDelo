package Settings;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.*;

public class Configurations {

    public void browserConfiguration(String browser, boolean headless) {
        Configuration.browser = browser;
        Configuration.headless = headless;
        Configuration.startMaximized = true;
        Configuration.timeout = 8000;
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
