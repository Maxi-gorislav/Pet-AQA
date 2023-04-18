package ui.pages.home;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage<T> {
    private final By logo = By.id("logo");
    public T isLoaded(){
        $(logo).shouldBe(Condition.visible);
        return (T) this;
    }

    @Step("verify breadcrumbs contain {keyword}")
    public T breadcrumbShouldContain(String keyword) {
        $("h1").shouldHave(text(keyword));
        return (T) this;
    }

}
