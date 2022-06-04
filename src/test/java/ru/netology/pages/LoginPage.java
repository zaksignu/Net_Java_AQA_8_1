package ru.netology.pages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.DataWizard;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement errorBubble = $("[data-test-id=error-notification] .notification__title");

    private void changeTxtInTheField(SelenideElement field, String text){
        field.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        field.setValue(text);
    }

    public VerificationPage validLogin(DataWizard.AuthInfo info) {
        changeMe( info.getLogin(),info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void invalidLogin(DataWizard.AuthInfo correctAuth) {
        changeMe( correctAuth.getLogin(),correctAuth.getInvalidPassword());
        loginButton.click();
        errorBubble.shouldHave(Condition.text("Ошибка"), Duration.ofSeconds(4));
    }

    private void changeMe(String login, String password) {
        changeTxtInTheField(loginField, login);
        changeTxtInTheField(passwordField, password);
    }


}
