package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


public class DashBoardPage {

    private SelenideElement heading = $("[data-test-id=dashboard]");

    public DashBoardPage() {
        heading.shouldBe(visible);
    }

}
