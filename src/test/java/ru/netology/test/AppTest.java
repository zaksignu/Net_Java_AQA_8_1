package ru.netology.test;

//import lombok.var;


import lombok.SneakyThrows;
        import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.DashBoardPage;
import ru.netology.pages.LoginPage;
import ru.netology.web.DataWizard;

import static com.codeborne.selenide.Selenide.open;

public class AppTest {

    private static DataWizard.FellowOne ghostOne = DataWizard.Registr.generateUser();

    @BeforeEach
    @SneakyThrows
    public void setUp() {
        DataWizard.userRegister(ghostOne);
        DataWizard.makeMeClean();

        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataWizard.getAuthInfo(ghostOne);
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataWizard.getVerificationCodeFor(ghostOne);
        verificationPage.validVerify(verificationCode);
        }


    @Test
    @SneakyThrows
    void happyPath() {
        var dashBoard = new DashBoardPage();
    }


}
