package ru.netology.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.DataWizard;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashBoardPage {
//
//    private ElementsCollection cards = $$(".list__item div");
//    private final String balanceStart = "баланс: ";
//    private final String balanceFinish = " р.";
//    private final String cardOne = "[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"]";
//    private final String cardTwo = "[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]";
//
    private SelenideElement heading = $("[data-test-id=dashboard]");
//
//    private SelenideElement transferOne = $(cardOne + " [data-test-id=\"action-deposit\"]");
//    private SelenideElement transferTwo = $(cardTwo + " [data-test-id=\"action-deposit\"]");
//
    public DashBoardPage() {
        heading.shouldBe(visible);
    }
//
//
//    public int getCardBalance(int id) {
//        String text = cards.get(id).getText();
//
//        return extractBalance(text);
//    }
//
//    private int extractBalance(String text) {
//        val start = text.indexOf(balanceStart);
//        val finish = text.indexOf(balanceFinish);
//        val value = text.substring(start + balanceStart.length(), finish);
//        return Integer.parseInt(value);
//    }
//
//    public CashTransfer cardsTransferFromFirst() {
//        transferOne.click();
//        return new CashTransfer(DataWizard.getCardsInfo().getSecondCard());
//    }
//
//    public CashTransfer cardsTransferFromSecond() {
//        transferTwo.click();
//        return new CashTransfer(DataWizard.getCardsInfo().getFirstCard());
//    }
}
