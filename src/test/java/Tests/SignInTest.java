package Tests;

import Pages.ForgotPasswordPage;
import Pages.RegistrationPage;
import Pages.SignInPage;
import Pages.UserMainPage;
import Settings.Configurations;
import Settings.Helpers;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.*;
import org.testng.annotations.*;


@Epic("Тестирование авторизации")
public class SignInTest extends Configurations {

    @Test(description = "Авторизация с корректными данными", priority = 1)
    public void succeedAuthorization() {
        new Helpers().openPage(SignInPage.CURRENT_URL);
        new SignInPage().authorizeLogin("test_acc_ip@testemail.org", "890890");
        Selenide.sleep(3000);
        new Helpers().checkCurrentTitle(UserMainPage.titleValue);
    }

    @Test(description = "Авторизация с некорректными данными", priority = 2)
    public void wrongAuthorization() {
        new Helpers().openPage(SignInPage.CURRENT_URL);
        new SignInPage().authorizeLogin("incorrect@mail.ru", "123456");
        new SignInPage().checkErrorMessageDiv("Пользователь не найден.");
    }

    @Test(description = "Проверка кнопки 'Еще не зарегистрированы'", priority = 3)
    public void goToRegistrationPage() {
        new Helpers().openPage(SignInPage.CURRENT_URL);
        new SignInPage().clickRegistration();
        Selenide.sleep(3000);
        new Helpers().checkCurrentTitle(RegistrationPage.titleValue);

    }

    @Test(description = "Проверка кнопки 'Забыли пароль?'", priority = 4)
    public void goToForgotPasswordPage() {
        new Helpers().openPage(SignInPage.CURRENT_URL);
        new SignInPage().clickForgotPassword();
        Selenide.sleep(3000);
        new Helpers().checkCurrentTitle(ForgotPasswordPage.titleValue);

    }
}
