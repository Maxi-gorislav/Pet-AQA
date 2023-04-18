package ui.pages.home;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchPage extends BasePage<SearchPage> {
    private final By productThumb = By.cssSelector(".product-thumb");

    @Step("Scroll browser to item")
    public SearchPage scrollToItem() {
        WebElement firstProductThumb = getWebDriver().findElement(productThumb);
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView();", firstProductThumb);
        return this;
    }

    public SearchPage verifyMacBookCaption() {
        $(By.xpath("//a[text() = 'MacBook']/ancestor::div[contains(@class, 'caption')]"))
                .shouldHave(text("MacBook"));
        return this;
    }

    private List<WebElement> getPrices() {
        return getWebDriver().findElements(By.xpath(("//div[@class='product-thumb']//p[contains(@class, 'price')]")));
    }

    private String getPrice() {
        SelenideElement priceElement = $(By.xpath("//a[text() = 'MacBook Pro']/ancestor::div[contains(@class, 'caption')]//p[contains(@class,'price')]"));
        return priceElement.getText().split("\n")[0];
    }

    @Step("verify that {expectedPrice} is present")
    public void verifyCorrectPrice(String expectedPrice) {
        assertEquals(expectedPrice, getPrice());
    }

    @Step("verify proper price in item")
    public SearchPage verifyProperPriceInItem(String priceItem) {
        var matchingPricesCount = getPrices().stream()
                .filter(price -> price.getText().split("\n")[0].trim().contains(priceItem))
                .count();
        assertEquals(matchingPricesCount, 1);

        return this;
    }

    @Step("Select item by {name}")
    public SearchPage selectItem(String name) {
        $(By.xpath("//*[@id='content']//a[text()='" + name + "']")).click();
        return this;
    }
}
