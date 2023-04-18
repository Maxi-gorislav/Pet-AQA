package ui.pages.home;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class CartPage extends BasePage<CartPage> {
    private final By quantity = By.cssSelector("input[name='quantity']");
    private final By buyButton = By.cssSelector("button#button-cart");
    private final By cartExpanded = By.xpath("//span[@id='cart-total']/..");

    @Step("Take {count} item")
    public CartPage takeSomeItem(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Count should be a positive integer.");
        }
        $(quantity).setValue(String.valueOf(count));
        return this;
    }

    @Step("Click Update button")
    public CartPage clickUpdateButton() {
        $(buyButton).click();
        return this;
    }

    @Step("Update Cart")
    public CartPage cartExpend() {
        $(cartExpanded).click();
        return this;
    }

    @Step("verify cart Total")
    public CartPage verifyCartHasProperItem(String count) {
        $(By.xpath("//span[@id='cart-total']/..")).shouldHave(text(count));
        return this;
    }
}
