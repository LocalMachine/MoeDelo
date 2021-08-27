package Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;


public class SignInPage {
    public static final String CURRENT_URL = "https://oauth.moedelo.org/Authorize";
    public static final String titleValue = "Главная − интернет-бухгалтерия Моё дело";

    private final SelenideElement authorizationForm = $(byAttribute("role", "presentation"));
    private final ElementsCollection inputCollection = authorizationForm.findAll(byTagName("input"));
    private final ElementsCollection buttonCollection = authorizationForm.findAll(byTagName("button"));

    private final SelenideElement emailInput = inputCollection.findBy(attribute("type", "email"));
    private final SelenideElement passwordInput = inputCollection.findBy(attribute("type", "password"));

    private final SelenideElement signInButton = buttonCollection.findBy(text("Войти в сервис"));
                                                                                     //h1[@class="produnctName"]//following::div/img/@src
    private final SelenideElement errorMessageDiv = authorizationForm.find(byXpath(".//div[text()='Пользователь не найден.']"));
    private final SelenideElement forgotPasswordLink = authorizationForm.find(byAttribute("href", "https://www.moedelo.org/ForgotPassword"));
    private final SelenideElement registrationLink = authorizationForm.find(byAttribute("href", "https://www.moedelo.org/BizRegistration"));


    @Step("Авторизация: ввод email-а, пароля и нажатие на кнопку 'Войти в сервис'")
    public void authorizeLogin(String login, String password) {
        emailInput.setValue(login);
        passwordInput.setValue(password);
        signInButton.click();
    }

    @Step("Проверка сообщения об ошибке при некорректнтной авторизации")
    public void checkErrorMessageDiv(String returnedErrorMessage) {
        errorMessageDiv.should(exist)
                .shouldBe(visible)
                .shouldHave(text(returnedErrorMessage));
    }

    @Step("Нажать на кнопку восстановления пароля")
    public void clickForgotPassword() {
        forgotPasswordLink.should(exist)
                .shouldBe(visible)
                .shouldHave(text("Забыли пароль?"))
                .click();
    }

    @Step("Нажать на кнопку регистрации")
    public void clickRegistration() {
        registrationLink.should(exist)
                .shouldBe(visible)
                .shouldHave(text("Еще не зарегистрированы?"))
                .click();
        ;
    }

}