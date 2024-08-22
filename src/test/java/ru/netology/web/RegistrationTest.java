package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class RegistrationTest {
    @Test
    void shouldRegisterByAccountNumberDOMModification() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");

        String date = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement dateElement = $("[data-test-id=date] input");
        dateElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        dateElement.setValue(date);

        $("[data-test-id=name] input").setValue("Иван Петров");
        $("[data-test-id=phone] input").setValue("+71111111111");
        $("[data-test-id=agreement]").click();
        $("form button.button").click();

        $("[data-test-id=notification]")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("Встреча успешно забронирована на " + date));
    }
}
