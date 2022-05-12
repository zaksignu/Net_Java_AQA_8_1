package ru.netology.test;

//import lombok.var;


import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.DashBoardPage;
import ru.netology.pages.LoginPage;
import ru.netology.web.DataWizard;

import static com.codeborne.selenide.Selenide.open;

public class AppTest {

    @BeforeAll
    static public void setUp() {
        DataWizard.tailsCleaning();

    }

    @Test
    void happyPath() {
        DataWizard.FellowOne ghostOne = DataWizard.Registr.generateUser();
        DataWizard.userRegister(ghostOne);
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataWizard.getAuthInfo(ghostOne);
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataWizard.getVerificationCodeFor(ghostOne);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldBlockAfterThreeTimeWrong() {
        DataWizard.FellowOne ghostOne = DataWizard.Registr.generateUser();
        DataWizard.userRegister(ghostOne);
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfoCorrect = DataWizard.getAuthInfo(ghostOne);
        loginPage.invalidLogin(authInfoCorrect);
        loginPage.invalidLogin(authInfoCorrect);
        loginPage.invalidLogin(authInfoCorrect);
        loginPage.invalidLogin(authInfoCorrect);
        loginPage.validLogin(authInfoCorrect);
    }

}
