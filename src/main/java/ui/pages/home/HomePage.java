package ui.pages.home;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class HomePage extends BasePage<HomePage> {

    private final By currencyButton = By.xpath("//*[@id='form-currency']//span");
    private final By searchInput = By.xpath("//*[@name='search']");

    @Step("Click form currency button")
    public HomePage clickFormCurrency() {
        $(currencyButton).click();
        return this;
    }

    @Step("Choose currency")
    public HomePage clickCurrencyList(String currency) {
        $(By.xpath("//*[@id='form-currency']//button[@name='" + currency + "']")).click();
        return this;
    }

    @Step("Click search button")
    public HomePage clickSearch() {
        $(searchInput).click();
        $(searchInput).clear();
        return this;
    }

    @Step("Search value")
    public HomePage searchText(String mac) {
        $(searchInput).sendKeys(mac + Keys.ENTER);
        return this;
    }
}
