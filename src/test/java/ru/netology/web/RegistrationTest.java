package ru.netology.web;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.*;

public class RegistrationTest {

    @Test
    void shouldRegisterByAccountNumberDOMModification() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue("Иван Петров");
        $("[data-test-id=phone] input").setValue("+71111111111");
        $("[data-test-id=agreement]").click();
        $("form button.button").click();


        $(withText("Встреча успешно забронирована")).shouldBe(visible, Duration.ofSeconds(15));



    }
}
