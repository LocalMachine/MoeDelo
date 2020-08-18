package Settings;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class Helpers {
    private final SelenideElement title = $(byTagName("title"));

    @Step("Запуск страницы")
    public void openPage(String pageUrl) {
        open(pageUrl);
    }

    public void closeDriver() {
        if (WebDriverRunner.hasWebDriverStarted()) WebDriverRunner.closeWebDriver();
    }

    @Step("Проверить текущий title")
    public void checkCurrentTitle(String titleText) {
        title.should(exist);
        Assert.assertEquals(Selenide.title(), titleText);
    }


}
